package org.doit.ik.di5;

import org.doit.ik.di.RecordViewImpl;
import org.doit.ik.di5.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// a-c.xml 처럼 스프링 설정(DI) 자바 파일이다. 
@Configuration
//@Import(Config.class) 자바 설정 파일을 조합 가능
//@Import({Config.class, Config3.class}) 자바 설정 파일을 조합 가능
// p.95 자바코드 설정에 XML 설정을 조합
/* @ImportResource("classpath:org/doit/ik/di/application-context.xml"); */
public class Config {
	
    /*  Config.java 로 설정 빼기. 
    <bean id="user1" class="org.doit.ik.di5.User">
      <constructor-arg value="bkchoi"></constructor-arg>
      <constructor-arg value="1234"></constructor-arg>
    </bean>
    */     
	@Bean
	public User user1() { // 메서드이름 == 빈 객체의 이름(id)
		return new User("bkchoi", "1234");
	}

}
