package com.boot.DyaanSetu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorSummaryDto {
    private Long id;
    private String emailId;
    private String firstName;
    private String lastName;
    private String department;
}
