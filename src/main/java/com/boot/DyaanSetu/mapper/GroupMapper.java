package com.boot.DyaanSetu.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.boot.DyaanSetu.dto.GroupDto;
import com.boot.DyaanSetu.dto.MentorSummaryDto;
import com.boot.DyaanSetu.dto.StudentSummaryDto;
import com.boot.DyaanSetu.entity.Group;

public class GroupMapper {

    public static GroupDto toGroupDto(Group group) {
        if (group == null) return null;

        GroupDto dto = new GroupDto();
        dto.setId(group.getId());
        dto.setGroupName(group.getGroupName());

        // leader
        if (group.getLeader() != null) {
            dto.setLeaderPrn(group.getLeader().getPrnNo());
            dto.setLeaderName(group.getLeader().getFirstName() + " " + group.getLeader().getLastName());
        }

        // mentor (single MentorSummaryDto instance)
        if (group.getMentor() != null) {
            MentorSummaryDto mentorDto = new MentorSummaryDto(
                group.getMentor().getId(),
                group.getMentor().getEmailId(),
                group.getMentor().getFirstName(),
                group.getMentor().getLastName(),
                group.getMentor().getDepartment() // use your actual getter name
            );
            dto.setMentor(mentorDto);
        }

        // students -> List<StudentSummaryDto>
        if (group.getStudents() != null) {
            List<StudentSummaryDto> students = group.getStudents().stream()
                .map(s -> new StudentSummaryDto(
                    s.getPrnNo(),
                    s.getFirstName(),
                    s.getLastName(),
                    s.getEmail()
                ))
                .collect(Collectors.toList());
            dto.setStudents(students);
        }

        return dto;
    }
}
