package edu.logintegra.wsbfinalproject2022.issues;

import edu.logintegra.wsbfinalproject2022.projects.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;

    public IssueController(IssueRepository issueRepository, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
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
    ModelAndView save(@ModelAttribute @Valid Issue issue,
                      BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("issues/create");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("issue", issue);
            modelAndView.addObject("projects", projectRepository.findAll());
            return modelAndView;
        }

        issueRepository.save(issue);

        modelAndView.setViewName("redirect:/projects");

        return modelAndView;
    }
}
