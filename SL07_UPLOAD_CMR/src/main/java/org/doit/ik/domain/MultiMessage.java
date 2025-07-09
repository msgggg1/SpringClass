package org.doit.ik.domain;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class MultiMessage {
	
	private String output;
	
	/* <div><input type="file" name="attach" multiple="multiple" ></div> input태그 name 속성과 같아야 함 */
	//private MultipartFile attach;
	// private CommonsMultipartFile [] attach;
	 private List<CommonsMultipartFile> attach;
	
}
