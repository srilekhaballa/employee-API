package com.javatraining.jpaexample.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeDetails {

    private String employeeName;

    private String departmentName;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date dateOfJoining;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {

        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return "EmployeeDetails [employeeName=" + employeeName + ", departmentName=" + departmentName + ", dateOfJoining=" + dateOfJoining + "]";
    }

}
