package com.project.pma.Repository;

import com.project.pma.Entities.Employee;
import com.project.pma.dto.EmployeeProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
    @Override
    public List<Employee> findAll();

    @Query(nativeQuery=true, value="select e.fname as firstName, e.lname as lastName, Count(pe.employee_id) as projectCount from employee e " +
            "left join project_employee pe on e.emp_id=pe.employee_id " +
            "group by e.fname,e.lname order by 3 desc")
    public List<EmployeeProject> employeeProjects();

    public Employee findByEmail(String value);

    public  Employee findByEmpId(long theId);
}
