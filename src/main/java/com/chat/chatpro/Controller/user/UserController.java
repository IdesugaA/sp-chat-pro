
package com.chat.chatpro.Controller.user;

@RestController
@RequestMapping("/user")
public class UserController{

	@Autowired
	UserService userService;

	@Autowired
	JwtProperties jwtProerties;

	@PostMapping("/login")
	public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
		UserService.login(userLoginDTO);
		return rtResult(userLoginDTO.getUserid());
	}

	@PostMapping("/register")
	public Result<UserLoginVO> register(UserRegisterDTO userRegisterDTO){
		Integer userid = userService.register(userRegisterDTO);
		return rtResult(userid);
	}

	private Result<UserLoginVO> rtResult(Integer userid){
		String secretKey = jwtProperties.getUserSecretKey();
		Long ttl = jwtProperties.getUserttl();
		Map<String, Object> claims = new HashMap();
		claims.put("userid",userid);
		String token = JwtUtil.createJWT(secretKey,ttl,claims);
		UserLoginVO userLoginVO = new UserLoginVO();
		userLoginVO.setUserid(userLoginDTO.getUserid());
		userLoginVO.setToken(token);
		return Result.success(userLoginVO);	

	}

}
