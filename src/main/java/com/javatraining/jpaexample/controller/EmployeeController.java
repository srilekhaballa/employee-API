package com.javatraining.jpaexample.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javatraining.jpaexample.dao.DepartmentDao;
import com.javatraining.jpaexample.jpa.entity.Department;
import com.javatraining.jpaexample.jpa.entity.Employee;
import com.javatraining.jpaexample.messaging.CustomMessage;
import com.javatraining.jpaexample.messaging.MQUtil;
import com.javatraining.jpaexample.model.EmployeeCount;
import com.javatraining.jpaexample.model.EmployeeDetails;
import com.javatraining.jpaexample.model.EmployeeDto;
import com.javatraining.jpaexample.service.DepartmentService;
import com.javatraining.jpaexample.service.EmployeeService;
import com.javatraining.jpaexample.service.QueryService;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    MQUtil mqUtil;

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        logger.info("getAllEmployees" + employeeService.getAllEmployees());
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployeeByID")
    public ResponseEntity<Employee> getEmployee(@RequestParam("id") Integer id) {
        return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/getDepartments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/getDepartmentByID")
    public ResponseEntity<Department> getDepartment(@RequestParam("id") Integer id) {
        return new ResponseEntity(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping("/getEmployeeDetails")
    public ResponseEntity<EmployeeDetails> getEmployeeDetails(@RequestParam("id") Integer id) {
        return new ResponseEntity(employeeService.getEmployeeDetails(id), HttpStatus.OK);
    }

    @Autowired(required = true)
    QueryService queryService;

    @GetMapping("/joinQuery")
    public ResponseEntity<EmployeeDetails> getQuery(@RequestParam("id") Integer id) {
        return new ResponseEntity(queryService.JPQLQuery(id), HttpStatus.OK);
    }

    @GetMapping("/getEmpCount")
    public ResponseEntity<HashMap<String, Long>> getEmployeeCount() {
        return new ResponseEntity(employeeService.getEmployeeCount(), HttpStatus.OK);
    }

    @GetMapping("/getEmpCountsByDept")
    public ResponseEntity<List<EmployeeCount>> getEmployeeCountByDepartment() {
        return new ResponseEntity(employeeService.getEmployeeCountByDepartment(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") Integer id) {
        Department department = departmentService.getDepartmentById(id);
        departmentService.deleteDepartment(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto) {
        CustomMessage msg = new CustomMessage();
        msg.setMessageId(UUID.randomUUID()
                .toString());
        msg.setMessageBody("new Employee created" + employeeDto.getEmail());
        msg.setMessageDate(new Date());
        Boolean createEmployee = employeeService.createEmployee(employeeDto);
        Boolean exist = employeeService.createEmployee(employeeDto);
        if (!createEmployee) {
            return new ResponseEntity<String>("Employee already exists", HttpStatus.BAD_REQUEST);
        }
        if (!departmentDao.existsByDeptId(employeeDto.getDeptId())) {
            return new ResponseEntity<String>("Department doesn't exists", HttpStatus.NOT_FOUND);
        }
        mqUtil.publishMessage(msg);
        return new ResponseEntity(employeeService.createEmployee(employeeDto), HttpStatus.OK);
    }

    @PostMapping("/createDepartment")
    public void createDepartment(@RequestBody Department department) {

        departmentService.createDepartment(department);

    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody EmployeeDto employeeDto) {
        Boolean exist = employeeService.createEmployee(employeeDto);
        if (!departmentDao.existsByDeptId(employeeDto.getDeptId())) {
            return new ResponseEntity<String>("Department doesn't exists", HttpStatus.NOT_FOUND);
        }
        final Employee updatedEmployee = employeeService.updateEmployee(employeeDto);
        return new ResponseEntity(updatedEmployee, HttpStatus.OK);
    }

    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") Integer id, @RequestBody Department department) {

        final Department updatedDepartment = departmentService.updateDepartment(department);
        return ResponseEntity.ok(updatedDepartment);

    }

}
