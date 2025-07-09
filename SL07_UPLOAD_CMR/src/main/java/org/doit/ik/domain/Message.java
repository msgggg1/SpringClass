package org.doit.ik.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import lombok.Data;

@Data
public class Message {
	
	private String output;
	
	/*<div><input type="file" name="attach" ></div> input태그 name 속성과 같아야 함*/
	//private MultipartFile attach;
	/* F3
	@see CommonsMultipartResolver
	@SuppressWarnings("serial")
	public class CommonsMultipartFile implements MultipartFile, Serializable {*/
	private CommonsMultipartFile attach;
	
}
