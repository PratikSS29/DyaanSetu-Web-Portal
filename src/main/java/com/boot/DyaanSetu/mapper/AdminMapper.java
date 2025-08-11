package com.boot.DyaanSetu.mapper;

import com.boot.DyaanSetu.dto.AdminDto;
import com.boot.DyaanSetu.entity.Admin;

public class AdminMapper {

    public static AdminDto toDto(Admin admin) {
        AdminDto dto = new AdminDto();
        dto.setFirstName(admin.getFirstName());
        dto.setLastName(admin.getLastName());
        dto.setEmail(admin.getEmail());
        dto.setDepartmentName(admin.getDepartmentName());
        return dto;
    }

    public static Admin toEntity(AdminDto dto) {
        Admin admin = new Admin();
        admin.setFirstName(dto.getFirstName());
        admin.setLastName(dto.getLastName());
        admin.setEmail(dto.getEmail());
        admin.setDepartmentName(dto.getDepartmentName());
        return admin;
    }
}
