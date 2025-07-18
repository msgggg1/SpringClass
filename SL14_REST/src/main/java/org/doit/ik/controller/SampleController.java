package org.doit.ik.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.doit.ik.domain.SampleVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController // == @Controller + @ResponseBody
@RequestMapping("/sample") // /* 생략해도 상관없음
@Log4j
public class SampleController { // 문자열 응답 테스트

	// URI + 전송방식 = 작업 지시
	// http://localhost/sample/getText + GET
	/* [1]
	@GetMapping("getText")
	public String getText() {
		log.info("> MIME TYPE :" + MediaType.TEXT_PLAIN_VALUE);
		return "Hello World - 안녕하세요";
	}
	 */
	//[2]
	@GetMapping(value="getText", produces="text/plain; charset=UTF-8")
	public String getText() {
		log.info("> MIME TYPE :" + MediaType.TEXT_PLAIN_VALUE);
		return "Hello World - 안녕하세요";
	}

	// 자바 객체 응답 컨트롤러 메서드
	// 자바 객체 -> JSON, XML 응답 컨트롤러 메서드
	// produce = {} 속성은 반드시 지정해야 할 필요는 없다.
	/*
	@GetMapping(value="getSample"
			, produces={
					MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE
					})
	public SampleVO getSample() {
		return new SampleVO(123, "스타", "로드");
	}
	*/
	
	@GetMapping(value="getSample")
	public SampleVO getSample() {
		return new SampleVO(123, "스타2", "로드2");
	}
	
	@GetMapping(value="getList")
	public List<SampleVO> getList() {
		// select emp -> List<EmpVO> 응답
		return IntStream.range(1, 11) // 1~10 정수 범위의 스트림
					.mapToObj(i->new SampleVO(i,"first"+i, "last")) // Stream<SampleVO>
					.collect(Collectors.toList()); // List<SampleVO>
	}
}
