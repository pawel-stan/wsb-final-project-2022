package edu.logintegra.wsbfinalproject2022.issues;

import edu.logintegra.wsbfinalproject2022.projects.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueRepository issueRepository;
    private final IssueService issueService;
    private final ProjectRepository projectRepository;

    public IssueController(IssueRepository issueRepository, IssueService issueService, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.issueService = issueService;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("issues/index");

        // Chciałbym przekształcić ją na listę IssueMap
        // [1, 2, 3] --> map(x * x) --> [1, 4, 9]

        List<IssueMap> issueMaps = issueRepository.findAll()
                .stream()
                .map(issueService::asIssueMap)
                .collect(Collectors.toList());

        modelAndView.addObject("issues", issueMaps);

        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("issues/create");

        Issue issue = new Issue();
        modelAndView.addObject("issue", issue);
        modelAndView.addObject("projects", projectRepository.findAll());

        return modelAndView;
    }

    @PostMapping("/save")
    String save(@ModelAttribute Issue issue) {
        issueRepository.save(issue);
        return "redirect:/projects";
    }
}
