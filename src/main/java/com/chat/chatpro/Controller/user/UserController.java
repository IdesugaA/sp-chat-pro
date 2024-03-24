
package com.chat.chatpro.Controller.user;

import com.chat.chatpro.Pojo.DTO.UserLoginDTO;
import com.chat.chatpro.Pojo.DTO.UserRegisterDTO;
import com.chat.chatpro.Properties.JwtProperties;
import com.chat.chatpro.Result.Result;
import com.chat.chatpro.Service.UserService;
import com.chat.chatpro.Util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.chat.chatpro.Pojo.VO.UserLoginVO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户登录注册接口")
@Slf4j
public class UserController{

	@Autowired
	UserService userService;

	@Autowired
	JwtProperties jwtProperties;

	@PostMapping("/login")
	@ApiOperation("用户登录")
	public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
		log.info("开始登录");
		userService.login(userLoginDTO);
		return rtResult(userLoginDTO.getUserid());
	}

	@PostMapping("/register")
	@ApiOperation("用户注册")
	public Result<UserLoginVO> register(UserRegisterDTO userRegisterDTO){
		String userid = userService.register(userRegisterDTO);
		return rtResult(userid);
	}

	@GetMapping("/test")
	public String test(){
		System.out.println("nihao");
		return "test";
	}

	private Result<UserLoginVO> rtResult(String userid){
		String secretKey = jwtProperties.getUserSecretKey();
		Long ttl = jwtProperties.getUserTtl();
		Map<String, Object> claims = new HashMap();
		claims.put("userid",userid);
		String token = JwtUtil.createJWT(secretKey,ttl,claims);
		UserLoginVO userLoginVO = new UserLoginVO();
		userLoginVO.setUserid(userid);
		userLoginVO.setToken(token);
		return Result.success(userLoginVO);	

	}

}
