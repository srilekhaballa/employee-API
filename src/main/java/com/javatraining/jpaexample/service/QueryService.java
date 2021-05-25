package com.javatraining.jpaexample.service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatraining.jpaexample.dao.EmployeeRepository;
import com.javatraining.jpaexample.model.EmployeeCount;
import com.javatraining.jpaexample.model.EmployeeDetails;

@Service
public class QueryService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EntityManagerFactory emf;

    private static final Logger logger = LogManager.getLogger("QueryService");

    public List<EmployeeDetails> JPQLQuery(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery("Select e.name,e.date_of_joining, d.d_name from Employee e  join Department d on e.dept_id=d.dept_id where e.emp_id=:id");
        query.setParameter("id", id);
        List<EmployeeDetails> list = (List<EmployeeDetails>) query.getResultList();
        return list;

    }

    public List<EmployeeCount> getEmployeeCountByDepartment() {
        EntityManager em = emf.createEntityManager();
        Query query1 =
                em.createNativeQuery("Select d.d_name as deptName,count(e.emp_id) as count from Employee e right join Department d on e.dept_id=d.dept_id group by d.d_name");
        return (List<EmployeeCount>) query1.getResultList()
                .stream()
                .map(this::convertToEmployeeCount)
                .collect(Collectors.toList());
    }

    private EmployeeCount convertToEmployeeCount(Object o) {
        EmployeeCount ec = new EmployeeCount();
        Object[] ob = (Object[]) o;
        ec.setCount((BigInteger) ob[1]);
        ec.setDeptName((String) ob[0]);
        return ec;
    }



}

