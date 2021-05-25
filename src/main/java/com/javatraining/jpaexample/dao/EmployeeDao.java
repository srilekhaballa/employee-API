package com.javatraining.jpaexample.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.javatraining.jpaexample.jpa.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer> {

    public Optional<Employee> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);


}
