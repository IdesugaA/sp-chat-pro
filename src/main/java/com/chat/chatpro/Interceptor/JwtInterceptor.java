package com.chat.chatpro.Interceptor;


@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor{

	@Autowired
	private JwtProperties jwtProperties;

	public boolean preHandle(HttpServletRequest request , HttpServletResponse response ,
			Object handler) throws Exception{
			
		//判断拦截到的是Controller方法还是其他资源
		//因为有可能是别的静态资源
		//当然在springboot里，静态资源的返回其实可以不经过controller，
		//这样性能也会提升
		if(!(handler of HandlerMethod)){
			return true; //返回true就直接进入下一个阶段，相当于拦截通过了
		}

		//token在HTTP请求的请求头部，在HTTP请求的请求头部中，信息是以
		//键值对的形式存在的
		String token = request.getHeader(jwtProperties.getUserTokenName());


		//校验令牌
		try{
			log.info("jwt校验：{}",token);
			Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(),token);
			Long userId = Long.valueOf(claims.get("userid"));
			//这里就用userid作为jwt载荷的仅有部分

			log.info("用户"+userId+"登录");
			return true;
		} catch(Exception e){
			response.setStatus(401);
			return false;
		}

	}	


}
