package com.nagarro.employee.controllers;

import com.nagarro.employee.models.Employee;
import com.nagarro.employee.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is the controller which provides CRUD operations on Employees
 *
 * @author Aniket Gupta
 */

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * This function provides list of employees from the database
     * @return List<Employee>
     */
    @GetMapping
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    /**
     * This function provides employee from the database
     * @param id
     * @return Employee
     */
    @GetMapping
    @RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee get(@PathVariable Long id) {
        return employeeRepository.getOne(id);
    }

    /**
     * This function creates employee and saves it to the database
     * @param employee
     * @return Employee
     */
    @PostMapping
    public Employee create(@RequestBody final Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    /**
     * This function deletes employee from the database
     * @param id
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    /**
     * This function updates existing employee in the database
     * @param id
     * @param employee
     * @return Employee
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        System.out.println(id);
        Employee existingEmployee = employeeRepository.getOne(id);
        BeanUtils.copyProperties(employee, existingEmployee, "emp_code");
        return employeeRepository.saveAndFlush(existingEmployee);
    }

}
