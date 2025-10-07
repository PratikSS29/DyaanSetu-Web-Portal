package com.boot.DyaanSetu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSummaryDto {

    private String prnNo;      // Unique identifier for student
    private String firstName;
    private String lastName;
    private String email;      // Basic contact
}
