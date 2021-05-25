package com.javatraining.jpaexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import com.javatraining.jpaexample.jpa.entity.Department;
import com.javatraining.jpaexample.jpa.entity.Employee;
import com.javatraining.jpaexample.model.EmployeeDetails;

public class EmployeeControllerTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(EmployeeControllerTest.class);

    private String getRootUrl() {
        return "http://localhost:8080";
    }

    @Test
    @Order(1)
    public void contextLoads() {

    }

    @Test
    @Order(2)
    public void testJPQLQuery() {
        EmployeeDetails result = testRestTemplate.getForObject(getRootUrl() + "/joinQuery", EmployeeDetails.class);
        logger.info(result.getEmployeeName());
        assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl() + "/getEmployees", HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    @Order(4)
    public void testGetEmployeeById() {
        Employee employee = testRestTemplate.getForObject(getRootUrl() + "/getEmployeeByID?id=1", Employee.class);
        logger.info(employee.getName());
        assertNotNull(employee);
    }

    @Test
    @Order(5)
    public void testGetEmployeeCountByDept() {
        List employee = testRestTemplate.getForObject(getRootUrl() + "/getEmpCountsByDept", List.class);

        logger.info(employee);
        assertNotNull(employee);

    }

    @Test
    @Order(6)
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmail("admin@gmail.com");
        employee.setName("admin");
        ResponseEntity<String> postResponse = testRestTemplate.postForEntity(getRootUrl() + "/createEmployee", employee, String.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    @Order(7)
    public void testUpdateEmployee() {
        int id = 1;
        Employee employee = testRestTemplate.getForObject(getRootUrl() + "/getEmployeeByID?id=1" + id, Employee.class);
        employee.setName("admin1");
        testRestTemplate.put(getRootUrl() + "/updateEmployee?id=1" + id, employee);
        Employee updatedEmployee = testRestTemplate.getForObject(getRootUrl() + "/getEmployeeByID?id=1" + id, Employee.class);
        assertNotNull(updatedEmployee);
    }

    @Test
    @Order(8)
    public void testDeleteEmployee() {
        int id = 2;
        Employee employee = testRestTemplate.getForObject(getRootUrl() + "/getEmployeeByID?id=2" + id, Employee.class);
        assertNotNull(employee);
        testRestTemplate.delete(getRootUrl() + "/deleteEmployee?id=2" + id);
        try {
            employee = testRestTemplate.getForObject(getRootUrl() + "/getEmployeeByID?id=2" + id, Employee.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    @Order(9)
    public void testGetDepartmentById() {
        Department department = testRestTemplate.getForObject(getRootUrl() + "/getDepartmentByID?id=169", Department.class);
        logger.info(department.getdName());
        assertNotNull(department);
    }

    @Test
    @Order(10)
    public void testGetAllDepartments() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl() + "/getDepartments", HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    @Order(11)
    public void testCreateDepartment() {
        Department department = new Department();
        department.setdName("IT");
        department.setDescription("Information Technology");
        logger.info(department);
        ResponseEntity<Department> postResponse = testRestTemplate.postForEntity(getRootUrl() + "/createDepartment", department, Department.class);
        assertTrue(postResponse.getStatusCode()
                .is2xxSuccessful());
        logger.info("testCreateDepartment" + postResponse);
    }

    @Test
    @Order(12)
    public void testUpdateDepartment() {
        int id = 169;
        Department department = testRestTemplate.getForObject(getRootUrl() + "/getDepartmentByID?id=169" + id, Department.class);
        department.setdName("IT");
        testRestTemplate.put(getRootUrl() + "/updateDepartment?id=169" + id, department);

        Department updatedDepartment = testRestTemplate.getForObject(getRootUrl() + "/getDepartmentByID?id=169" + id, Department.class);
        assertNotNull(updatedDepartment);
    }


    @org.junit.Test
    @Order(13)
    public void testDeleteDepartment() {
        int id = 169;
        Department department = testRestTemplate.getForObject(getRootUrl() + "/getDepartmentByID?id=169" + id, Department.class);
        assertNotNull(department);
        testRestTemplate.delete(getRootUrl() + "/deleteDepartment?id=169" + id);
        try {
            department = testRestTemplate.getForObject(getRootUrl() + "/getDepartmentByID?id=169" + id, Department.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}

