package com.project.pma.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pma.Entities.Employee;
import com.project.pma.Entities.Project;
import com.project.pma.Repository.EmployeeRepository;
import com.project.pma.Repository.ProjectRepository;
import com.project.pma.dto.EmployeeProject;
import com.project.pma.dto.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${version}")
    String version;

    @Autowired
    ProjectRepository proRep;
    @Autowired
    EmployeeRepository empRep;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
        Map<String,Object> map=new HashMap<>();
        List<Project> projects=proRep.findAll();
        //List<Employee> employee=empRep.findAll();
        List<EmployeeProject> employeeProjects=empRep.employeeProjects();
        List<ProjectStatus> projectSts=proRep.projectStatus();

        //Convert ProjectStatus to json for chart
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonString=objectMapper.writeValueAsString(projectSts);


        model.addAttribute("project",projects);
        model.addAttribute("employee",employeeProjects);
        model.addAttribute("projectStatus",projectSts);
        model.addAttribute("projectStatusChart",jsonString);
        model.addAttribute("version",version);
        return "Home";
    }


}
