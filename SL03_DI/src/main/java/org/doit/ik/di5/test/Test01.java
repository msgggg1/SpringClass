package org.doit.ik.di5.test;

import org.doit.ik.di5.AuthException;
import org.doit.ik.di5.AuthenticationService;
import org.doit.ik.di5.PasswordChangeService;
import org.doit.ik.di5.UserNotFoundException;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test01 {

   public static void main(String[] args) {
      
      /* [복습문제] p62 ~ p74 예제 클래스들로 구성하여 
       * 각각의 인터페이스, 클래스를 선언하고  
       * application-context.xml 로  빈 생성 DI 후 사용 예제
       * p73 자바 -> Test01.java
       * 
       * Config.java 자바 파일과 application-context.xml 설정 파일 함께 사용하기. 
       * */ 
      
      
      
      String [] resourceLocations = {  "classpath:org/doit/ik/di5/application-context.xml" };
      GenericXmlApplicationContext ctx= new GenericXmlApplicationContext(resourceLocations);
      
      AuthenticationService authSvc = ctx.getBean("authenticationService", AuthenticationService.class);
      
      runAuthAndCatchAuthEx(authSvc, "bkchoi", "1111");
      runAuthAndCatchAuthEx(authSvc, "bkchoi", "11111");
      runAuthAndCatchAuthEx(authSvc, "bkchoi", "111111");
      
      try { 
         authSvc.authenticate("bkchoi2", "1111");
      } catch (UserNotFoundException ex) {
      }
      authSvc.authenticate("bkchoi", "1234");
      PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
      pwChgSvc.changePassword("bkchoi", "1234", "5678");
      runAuthAndCatchAuthEx(authSvc, "bkchoi", "1234");
      authSvc.authenticate("bkchoi", "5678");
      ctx.close();
   }

   private static void runAuthAndCatchAuthEx(
         AuthenticationService authSvc, String userId, String password) {
      try {
         authSvc.authenticate(userId, password);
      } catch (AuthException ex) {
      }
   }
}
