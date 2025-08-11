package com.boot.DyaanSetu.Config;

import com.boot.DyaanSetu.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private String message;
	private StudentDto studentDto;
}
