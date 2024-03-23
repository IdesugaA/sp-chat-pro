package com.chat.chatpro.Util.JwtUtil;


//封装成工具类，使用的是jjwt-root库
public class JwtUtil{

	@Autowired
	private JwtProperties jwtProperties;

	public static String createJWT(String secretKey , long ttlMillis , Map<String, Object> claims){
		//指定签名算法,位于JWT的头部
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		//过期时间
		long expMillis = System.currentTimeMillis() + ttlMillis;
		Date exp = new Date(expMillis);

		//另外，claims保存的是payload，
		//这部分是明文的，只不过经过了Base64URL编码
		//claims的数据格式和泛型格式也是固定的

		JwtBuilder builder = Jwts.builder()
			.setClaims(claims)
			.signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
			.setExpiration(exp);
		return builder.compact();
	}
	
	public static Claims parseJWT(String secretKey, String token){
		Claims claims = Jwts.parser()
			.setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
			.parseClaimsJws(token).getBody();
		return claims;
		//parseClaimsJws本身就有验证token是否有效的功能
		//如果无效，会抛出异常
		//得到并返回载荷
	}

}
