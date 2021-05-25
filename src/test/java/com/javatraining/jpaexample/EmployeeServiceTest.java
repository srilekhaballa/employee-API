package com.javatraining.jpaexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.javatraining.jpaexample.dao.DepartmentDao;
import com.javatraining.jpaexample.dao.EmployeeDao;
import com.javatraining.jpaexample.jpa.entity.Department;
import com.javatraining.jpaexample.jpa.entity.Employee;
import com.javatraining.jpaexample.model.EmployeeCount;
import com.javatraining.jpaexample.model.EmployeeDetails;
import com.javatraining.jpaexample.model.EmployeeDto;
import com.javatraining.jpaexample.service.DepartmentService;
import com.javatraining.jpaexample.service.EmployeeService;

public class EmployeeServiceTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceTest.class);

    @Autowired
    EmployeeService employeeService;

    static int empId = 163;

    static Integer deptId = 130;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @Test
    @Order(1)
    public void getAllEmployees() {
        logger.info("getAllEmployees" + employeeService.getAllEmployees());
        assertEquals(employeeService.getAllEmployees()
                .size(),
                employeeDao.findAll()
                        .size());

    }

    @Test
    @Order(2)
    public void createEmployee() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        EmployeeDto emp = new EmployeeDto();
        emp.setFirstName("sri");
        emp.setLastName("lekha");
        emp.setEmail("srilekha@gmail.com");
        emp.setPhone("9087096765");
        emp.setDeptId(130);
        emp.setDateOfJoining(format.parse("26-04-2021"));
        Boolean savedEmp = employeeService.createEmployee(emp);
        assertEquals(savedEmp, true);
    }

    @Test
    @Order(3)
    public void getEmployeeById() {
        assertEquals(employeeService.getEmployeeById(163), employeeDao.findById(163));
    }

    @Test
    @Order(4)
    public void updateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setPhone("7896547378");
        Boolean savedEmployee = employeeService.createEmployee(employeeDto);
        assertEquals(savedEmployee, true);

    }

    @Test
    @Order(5)
    public void deleteEmployee() {
        employeeService.deleteEmployee(empId);
        assertEquals(employeeService.getEmployeeById(empId), null);
    }

    @SuppressWarnings("deprecation")
    @Test
    @Order(6)
    public void getEmployeeDetails() {
        Employee emp = employeeService.getEmployeeById(empId);
        EmployeeDetails ed = new EmployeeDetails();
        ed.setDateOfJoining(emp.getDateOfJoining());
        ed.setEmployeeName(emp.getName());
        ed.setDepartmentName(emp.getDepartment()
                .getdName());
        assertNotEquals(ed, null);
    }

    @Test
    @Order(7)
    public void getEmployeeCount() {
        HashMap<String, Long> m = employeeService.getEmployeeCount();
        assertEquals(m, 6);
    }

    @Test
    @Order(8)
    public void getEmployeeCountByDepartment() {
        List<EmployeeCount> c = employeeService.getEmployeeCountByDepartment();
        assertNotEquals(c, null);
    }

    @Test
    @Order(9)
    public void getAllDepartments() {
        assertEquals(departmentService.getAllDepartments(), departmentDao.findAll());
    }

    @Test
    @Order(10)
    public void createDepartment() {
        Department dept = new Department();
        dept.setdName("IT");
        dept.setDescription("information technology");
        // dept.setEmployee(null);
        logger.info("create department");
        Department savedDept = departmentService.createDepartment(dept);
        logger.info(savedDept);
        assertNotEquals(savedDept, null);
    }

    @Test
    @Order(11)
    public void getDepartmentById() {
        assertNotEquals(departmentService.getDepartmentById(deptId), null);
    }

    @Test
    @Order(12)
    public void updateDepartment() {
        Department dept = new Department();
        dept.setdName("IT");
        Department savedDepartment = departmentService.createDepartment(dept);
        assertNotEquals(savedDepartment, null);
    }

    @Test
    @Order(13)
    public void deleteDepartment() {
        departmentService.deleteDepartment(deptId);
        assertEquals(departmentService.getDepartmentById(deptId), null);
    }
}
