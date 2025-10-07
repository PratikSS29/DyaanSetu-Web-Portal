package com.boot.DyaanSetu.mapper;

import com.boot.DyaanSetu.dto.GroupDto;
import com.boot.DyaanSetu.dto.MentorDto;
import com.boot.DyaanSetu.dto.MentorSummaryDto;
import com.boot.DyaanSetu.dto.StudentDto;
import com.boot.DyaanSetu.dto.StudentSummaryDto;
import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.Mentor;
import com.boot.DyaanSetu.entity.Student;

public class MentorMapper {

    public static MentorDto toDto(Mentor mentor) {
        MentorDto dto = new MentorDto();
        dto.setId(mentor.getId());
        dto.setFirstName(mentor.getFirstName());
        dto.setLastName(mentor.getLastName());
        dto.setEmailId(mentor.getEmailId());
        dto.setDepartment(mentor.getDepartment() );

        if (mentor.getGroups() != null) {
            dto.setGroups(
                mentor.getGroups().stream()
                        .map(MentorMapper::toGroupDto)
                        .toList()
            );
        }
        return dto;
    }

    private static GroupDto toGroupDto(Group group) {
        GroupDto dto = new GroupDto();
        dto.setId(group.getId());
        dto.setGroupName(group.getGroupName());

        if (group.getStudents() != null) {
            dto.setStudents(
                group.getStudents().stream()
                     .map(student -> new StudentSummaryDto(
                         student.getPrnNo(),
                         student.getFirstName(),
                         student.getLastName(),
                         student.getEmail()
                     ))
                     .toList()
            );
        }
     // Optional: leader info
        if (group.getLeader() != null) {
            dto.setLeaderPrn(group.getLeader().getPrnNo());
            dto.setLeaderName(group.getLeader().getFirstName() + " " + group.getLeader().getLastName());
        }

        // Optional: mentor info
        if (group.getMentor() != null) {
            dto.setMentor(new MentorSummaryDto(
                group.getMentor().getId(),
                group.getMentor().getEmailId(),
                group.getMentor().getFirstName(),
                group.getMentor().getLastName(),
                group.getMentor().getDepartment()
            ));
        }

        return dto;
    }

    private static StudentDto toStudentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setRollNo(student.getRollNo());
        dto.setPrnNo(student.getPrnNo());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setCurrentYearofStudy(student.getCurrentYearofStudy());
        dto.setBranch(student.getBranch());
        dto.setDivision(student.getDivision());
        dto.setYearofPassing(student.getYearOfPassing());
        dto.setContactNo(student.getContactNo());
        dto.setGender(student.getGender());
        dto.setBio(student.getBio());
        return dto;
    }

}
