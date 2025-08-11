package com.boot.DyaanSetu.Config;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.boot.DyaanSetu.ServiceLayer.AlumniService;
import com.boot.DyaanSetu.dto.AlumniDto;import com.boot.DyaanSetu.dto.StudentDto;

@Component
public class AlumniTempRegistrationCache {

	private final Map<String, AlumniDto> tempStorage=new ConcurrentHashMap<>();
	private final Duration EXPIRY=Duration.ofMinutes(30);
	
	public String storeTempRegistration(AlumniDto dto) {
		String tempId=UUID.randomUUID().toString();
		tempStorage.put(tempId, dto);
		scheduleCleanup(tempId);
		return tempId;
	}
	
	public AlumniDto getTempRegistration(String tempId) {
		return Optional.ofNullable(tempStorage.get(tempId))
						.orElseThrow(()-> new RuntimeException("Registration Session Expired"));
	}
	
	public void clearTempRegistration(String tempId) {
		tempStorage.remove(tempId);
	}
	
	public void scheduleCleanup(String tempId) {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				tempStorage.remove(tempId);
			}
		},EXPIRY.toMillis());
	}
}
