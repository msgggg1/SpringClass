package org.doit.ik.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptVO {

	private Integer deptno;
	private String dname;
	private String loc;

} 