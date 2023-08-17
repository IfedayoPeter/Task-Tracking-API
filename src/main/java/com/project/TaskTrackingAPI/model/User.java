package com.project.TaskTrackingAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_TABLE")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long Id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL)
    private List<Task> assignedTasks = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Task> createdTasks = new ArrayList<>();

}
