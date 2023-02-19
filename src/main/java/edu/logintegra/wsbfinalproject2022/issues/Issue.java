package edu.logintegra.wsbfinalproject2022.issues;

import edu.logintegra.wsbfinalproject2022.projects.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Issue {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @NotNull
    @Size(min = 3)
    private String code;

    @NotNull
    @Size(min = 5)
    private String title;

    private String content;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueType type = IssueType.TASK;

    @Id
    @GeneratedValue
    private Long id;
}
