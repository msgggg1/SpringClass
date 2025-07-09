package org.doit.ik.domain;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
   
   private String seq;
   private String title;
   private String writer;
   private String filesrc;
   private Date regdate;
   private int hit;
   private String content;
  
   private List<CommonsMultipartFile> file;
   
} // class