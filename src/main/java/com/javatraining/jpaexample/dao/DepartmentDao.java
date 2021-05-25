package com.javatraining.jpaexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javatraining.jpaexample.jpa.entity.Department;

public interface DepartmentDao extends JpaRepository<Department, Integer> {

    boolean existsByDeptId(Integer deptId);

}


