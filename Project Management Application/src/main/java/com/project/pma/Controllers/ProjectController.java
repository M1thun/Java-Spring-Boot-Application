package com.project.pma.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pma.Entities.Employee;
import com.project.pma.Entities.Project;
import com.project.pma.Repository.EmployeeRepository;
import com.project.pma.Repository.ProjectRepository;
import com.project.pma.dto.TimeChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectRepository proRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public String displayEmployees(Model model){
        List<Project> projs=proRepo.findAll();
        model.addAttribute("projs",projs);
        return "Project/list-projects";
    }

    @RequestMapping("/new")
    public String displayProjectForm(Model model){
        Project prj=new Project();
        List<Employee> emps=empRepo.findAll();
        model.addAttribute("project",prj);
        model.addAttribute("employees",emps);
        return "Project/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project,
                                //@RequestParam List<Long> emps
                                Model model){
        proRepo.save(project);
//        Iterable<Employee> selectEmployees=empRepo.findAllById(emps);
//
//        for(Employee emp:selectEmployees) {
//            emp.setProject(project);
//            empRepo.save(emp);
//        }
        return "redirect:/project";
    }

    @GetMapping("/timelines")
    public String displayProjectTimelines(Model model) throws JsonProcessingException {
        List<TimeChartData> tcd=proRepo.getTimeData();

        ObjectMapper objectMapper=new ObjectMapper();
        String jsonString=objectMapper.writeValueAsString(tcd);

        model.addAttribute("projectTimeList",jsonString);

        return "Project/project-timelines";
    }


}
