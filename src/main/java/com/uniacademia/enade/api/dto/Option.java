package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Option implements Serializable {
	private static final long serialVersionUID = 4375746005702849636L;

	private Long value;
	private String text;
	
	@Override
	public String toString() {
		return "Option [text=" + text + ", value=" + value + "]";
	}
}
