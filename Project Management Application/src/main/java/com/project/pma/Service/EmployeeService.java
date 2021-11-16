package com.project.pma.Service;

import com.project.pma.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired //This is field injection
    EmployeeRepository empRepo;
    //Three types of dependency injection
    //1) Constructor injection

//    public EmployeeService(EmployeeRepository er){
//        this.empRepo=er;
//    }


    //2)Setter Injection
//    @Autowired
//    public void setEmpRepo(EmployeeRepository empRepo) {
//        this.empRepo = empRepo;
//    }

}


