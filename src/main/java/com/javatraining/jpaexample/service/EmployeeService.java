package com.javatraining.jpaexample.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.javatraining.jpaexample.dao.DepartmentDao;
import com.javatraining.jpaexample.dao.EmployeeDao;
import com.javatraining.jpaexample.jpa.entity.Employee;
import com.javatraining.jpaexample.model.EmployeeCount;
import com.javatraining.jpaexample.model.EmployeeDetails;
import com.javatraining.jpaexample.model.EmployeeDto;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    QueryService queryService;

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date date = new Date();

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);

    public List<Employee> getAllEmployees() {

        return employeeDao.findAll();

    }

    public Employee getEmployeeById(Integer id) {

        Employee emp = null;
        Optional<Employee> empOptional = employeeDao.findById(id);
        if (empOptional.isPresent()) {
            emp = empOptional.get();
        }
        return emp;
    }

    public HashMap<String, Long> getEmployeeCount() {
        HashMap<String, Long> m = new HashMap<String, Long>();
        m.put("count", employeeDao.count());
        return m;
    }

    public List<EmployeeCount> getEmployeeCountByDepartment() {
        return queryService.getEmployeeCountByDepartment();
    }


    public EmployeeDetails getEmployeeDetails(Integer id) {

        Employee e = employeeDao.findById(id)
                .get();
        EmployeeDetails ed = new EmployeeDetails();
        ed.setEmployeeName(e.getName());
        ed.setDepartmentName(e.getDepartment()
                .getdName());
        ed.setDateOfJoining(e.getDateOfJoining());
        return ed;

    }

    public void deleteEmployee(Integer id) {

        employeeDao.deleteById(id);

    }

    @org.springframework.transaction.annotation.Transactional
    public Boolean createEmployee(EmployeeDto employeeDto) {
        Employee emp = new Employee();
        if (employeeDao.existsByEmailIgnoreCase(employeeDto.getEmail())) {
            logger.info("Employee with employee email %s and employee id %s already exists", employeeDto.getEmail(), employeeDto.getEmpId());
            return false;
        }
        if (!departmentDao.existsByDeptId(employeeDto.getDeptId())) {
            logger.info("Department doesn't exists");
            return true;
        }
        String fullName = employeeDto.getFirstName()
                .toUpperCase() + " "
                + employeeDto.getLastName()
                        .toUpperCase();
        emp.setEmpId(employeeDto.getEmpId());
        emp.setName(fullName.toLowerCase());
        emp.setDepartment(departmentDao.findById(employeeDto.getDeptId())
                .get());
        emp.setPhone(employeeDto.getPhone());
        emp.setEmail(employeeDto.getEmail());
        emp.setCreatedBy("sri");
        emp.setCreatedOn(date);
        emp.setDateOfJoining(employeeDto.getDateOfJoining());
        emp.setUpdatedBy(" ");
        emp.setUpdatedOn(null);
        employeeDao.save(emp);
        return true;
    }

    public Employee updateEmployee(EmployeeDto employeeDto) {

        if (!departmentDao.existsByDeptId(employeeDto.getDeptId())) {
            logger.info("Department doesn't exists");
        }
        Employee e1 = employeeDao.findById(employeeDto.getEmpId())
                .get();
        e1.setDepartment(departmentDao.findById(employeeDto.getDeptId())
                .get());
        e1.setEmail(employeeDto.getEmail());
        String fullName = employeeDto.getFirstName() + " " + employeeDto.getLastName();
        e1.setName(fullName.toLowerCase());
        e1.setPhone(employeeDto.getPhone());
        e1.setCreatedOn(date);
        e1.setCreatedBy("sri");
        e1.setEmpId(employeeDto.getEmpId());
        e1.setDateOfJoining(employeeDto.getDateOfJoining());
        e1.setUpdatedBy(employeeDto.getUpdatedBy());
        e1.setUpdatedOn(date);
        return employeeDao.save(e1);
    }

}
