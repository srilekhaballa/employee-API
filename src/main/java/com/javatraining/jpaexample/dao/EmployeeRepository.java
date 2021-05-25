package com.javatraining.jpaexample.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.javatraining.jpaexample.jpa.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Override
    public List<Employee> findAll();



}


