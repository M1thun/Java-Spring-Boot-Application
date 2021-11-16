package com.project.pma.Controllers;

import com.project.pma.Entities.Employee;
import com.project.pma.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository emp;

    @GetMapping
    public String displayEmployees(Model model){
        List<Employee> emps=emp.findAll();
        model.addAttribute("emp",emps);
        return "Employee/list-employees";
    }


    @RequestMapping("/new")
    public String newEmp(Model model, RedirectAttributes atr){
        //atr.addFlashAttribute("employee",new Employee());
        model.addAttribute("employeeData",new Employee());
        return "Employee/new-Employee";
    }

    @PostMapping("/save")
    public String saveEmp(@ModelAttribute("employeeData") @Valid Employee employeeData, Errors errors){

        if(errors.hasErrors()) {
            //model.addAttribute("employeeData", employeeData);
            return "Employee/new-Employee";
        }
        emp.save(employeeData);
        return "redirect:/employee";
    }

    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model){
        Employee theEmp=emp.findByEmpId(theId);
        model.addAttribute("employeeData",theEmp);
        return "Employee/new-Employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") long theId, Model model){
        Employee theEmp=emp.findByEmpId(theId);
        emp.delete(theEmp);
        return "redirect:/employee";
    }
}
