package com.project.pma.restAPI;

import com.project.pma.Entities.Employee;
import com.project.pma.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public Iterable<Employee> getEmployees(){
        return empRepo.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id){
        return empRepo.findById(id).get();
    }

    @PostMapping( consumes = "application/json")
    @ResponseStatus(value=HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee){
        return empRepo.save(employee);
    }

    @PutMapping() //---> Updates all records and deletes updated record in related tables if cascade delete is set
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody Employee emp){
        return empRepo.save(emp);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updatePartial(@PathVariable long id, @RequestBody @Valid Employee empUpdated){
        Employee emp=empRepo.findById(id).get();
        if(empUpdated.getEmail() !=null){
            emp.setEmail(empUpdated.getEmail());
        }
        if(empUpdated.getFname() !=null){
            emp.setFname(empUpdated.getFname());
        }
        if(empUpdated.getLname() !=null){
            emp.setLname(empUpdated.getLname());
        }
        return empRepo.save(emp);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable long id){
        try{
            empRepo.deleteById(id);
        }
        catch (Exception e){ // --> EmptyResultDataAccessException

        }
    }

    @GetMapping(params={"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size){
        PageRequest pageAndSize = PageRequest.of(page,size);
        return empRepo.findAll(pageAndSize);
    }


}
