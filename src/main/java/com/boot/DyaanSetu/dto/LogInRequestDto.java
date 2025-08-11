package com.boot.DyaanSetu.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LogInRequestDto {

	private String email;
	private String password;
	private String userType;
}
