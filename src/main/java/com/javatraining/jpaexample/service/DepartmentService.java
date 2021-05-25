package com.javatraining.jpaexample.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatraining.jpaexample.dao.DepartmentDao;
import com.javatraining.jpaexample.jpa.entity.Department;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    public String getDepartmentName(Integer id) {
        Department d1 = departmentDao.findById(id)
                .get();
        return d1.getdName();
    }

    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }

    public Department getDepartmentById(Integer id) {
        return departmentDao.findById(id)
                .get();
    }

    public void deleteDepartment(Integer id) {
        departmentDao.deleteById(id);
    }

    public Department createDepartment(Department department) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = formatter.format(new Date());
        Department d1 = new Department();
        d1.setCreatedOn(date);
        d1.setCreatedBy("sri");
        d1.setDeptId(department.getDeptId());
        d1.setDescription(department.getDescription());
        d1.setdName(department.getdName());
        return departmentDao.save(d1);
    }

    public Department updateDepartment(Department department) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = formatter.format(new Date());
        Department d1 = departmentDao.findById(department.getDeptId())
                .get();
        d1.setdName(department.getdName());
        d1.setDescription(department.getDescription());
        d1.setCreatedOn(date);
        d1.setCreatedBy("sri");
        return departmentDao.save(d1);
    }

}
