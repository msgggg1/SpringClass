package org.doit.ik.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDTO {

   private int deptno;
   private String dname;
   private String loc;   
   
}