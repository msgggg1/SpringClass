package org.doit.ik.di2;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// a-c.xml 처럼 스프링 설정(DI) 자바 파일이다. 
@Configuration
//@Import(Config.class) 자바 설정 파일을 조합 가능
//@Import({Config.class, Config3.class}) 자바 설정 파일을 조합 가능
// p.95 자바코드 설정에 XML 설정을 조합
/* @ImportResource("classpath:org/doit/ik/di/application-context.xml"); */
public class Config {
	// 자바로 설정
	//<bean id="record" class="org.doit.ik.di.RecordImpl\"></bean>
	@Bean
	public RecordImpl record() { // 메서드이름 == 빈 객체의 이름(id)
		return new RecordImpl();
	}
	
	/*
	<bean id="rvi" class="org.doit.ik.di.RecordViewImpl">
	<property name="record">
		<ref bean="record"> 참조하는 빈객체가 여러개면 추가
	</property>   
	</bean>
	*/
	@Bean(name="rvi")
	public RecordViewImpl getRecordViewImpl() {
	//public RecordViewImpl rvi() {
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record());
		return rvi;
	}

}
