package org.doit.ik.di;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecordImpl implements Record{
	
	private int kor;
	private int eng;
	private int mat;

	@Override
	public int total() {	
		return this.kor + this.eng + this.mat;
	}

	@Override
	public double avg() {	
		return this.total()/3.0;
	}

}
