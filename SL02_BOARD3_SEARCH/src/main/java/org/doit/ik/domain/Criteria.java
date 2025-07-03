package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum; // 현재 페이지 번호
	private int amount;  // 한 페이지에 출력할 게시글 수
	
	private String type; // 검색조건 T C W TC TW TWC
	private String keyword; // 검색어
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public Criteria() {
		this(1,10);
	}
	
	// ?pageNum=2?amount=10&type=T&keyword=홍길동&.. 자동 생성 메서드
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
							.queryParam("pageNum", this.pageNum)
							.queryParam("amount", this.amount)
							.queryParam("type", this.type)
							.queryParam("keyword", this.keyword);
		return builder.toUriString();
	}
	
	// 검색조건 T C W TC TW TCW 을 배열로 반환하는 메서드
	// Mybatis -> WHERE 조건절 반복해서 추가
	
	public String [] getTypeArr() {
		return this.type == null ? new String [] {}: this.type.split("");
	}
	
}// class
