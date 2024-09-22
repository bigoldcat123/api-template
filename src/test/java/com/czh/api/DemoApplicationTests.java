package com.czh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	@Value("${spring.data.redis.host}")
	String host;
	// @Test
	// void contextLoads() {
	// 	System.out.println(host);
	// 	redisTemplate.opsForValue().set("hello", new TestValifacationEntity(1,"2","2","4"));
	// 	System.out.println(redisTemplate.opsForValue().get("hello"));
	// 	System.out.println(redisTemplate.opsForValue().get(MailService.REDIS_CODE_MAIL + "22147779904@qq.com"));
	// }

//	public static void main(String[] args) {
//		SecretKey key = Jwts.SIG.HS256.key().build();
//		System.out.println(key.getFormat());
//		String jws = Jwts.builder().subject("Joe").signWith(key).compact();
//		System.out.println(jws);
//		System.out.println(Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject());
//		System.out.println( Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject().equals("Joe"));
//	}

	// @Autowired
	// MailService mailService;
	// @Test
	// public void mailTest() throws MessagingException {
	// }
	// @Autowired
	// IUserAuthService userAuthService;

	// @Test
	// public void  t()
	// {
	// 	List<UserAuth> list = userAuthService.list();
	// 	System.out.println(list);
	// }
}
