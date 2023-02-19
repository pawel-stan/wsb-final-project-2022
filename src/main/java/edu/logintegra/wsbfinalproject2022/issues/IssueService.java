package edu.logintegra.wsbfinalproject2022.issues;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

    @Cacheable(value = "issue-maps", key = "#issue.id")
    public IssueMap asIssueMap(Issue issue) {
        System.out.println("Generujemy mapę dla zgłoszenia " + issue.getCode() + "...");
        return new IssueMap(issue);
    }
}
