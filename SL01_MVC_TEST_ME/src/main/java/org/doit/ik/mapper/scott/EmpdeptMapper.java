package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.doit.ik.domain.EmpdeptDTO;

public interface EmpdeptMapper {
	ArrayList<EmpdeptDTO> selectEmpdept(int deptno);
}
