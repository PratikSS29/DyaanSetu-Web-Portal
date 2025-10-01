package com.boot.DyaanSetu.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Project_Group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groupName;

    // Leader joined by PRN number
    @OneToOne
    @JoinColumn(name = "leader_prn", referencedColumnName = "prn_number")
    private Student leader;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    // If you want files later
    // @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    // private List<ProjectFile> files;
}
