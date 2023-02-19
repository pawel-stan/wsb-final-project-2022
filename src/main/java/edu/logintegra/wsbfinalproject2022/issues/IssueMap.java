package edu.logintegra.wsbfinalproject2022.issues;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IssueMap implements Serializable {

    Long id;
    String code;
    String title;
    String projectCode;

    IssueMap(Issue issue) {
        this.id = issue.getId();
        this.code = issue.getCode();
        this.title = issue.getTitle();
        this.projectCode = issue.getProject().getCode();
    }
}
