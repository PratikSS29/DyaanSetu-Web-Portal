package com.boot.DyaanSetu.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Long id;
    private String groupName;

    // Leader details
    private String leaderPrn;
    private String leaderName;

    // Mentor details (summary only)
    private MentorSummaryDto mentor;

    // Students (summary list)
    private List<StudentSummaryDto> students;
}
