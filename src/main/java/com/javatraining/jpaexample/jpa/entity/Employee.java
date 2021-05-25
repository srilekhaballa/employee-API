package com.javatraining.jpaexample.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "employee", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Employee implements Serializable {

    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // auto-increment
    private Integer empId;
    private String name;
    @Column(unique = true)
    private String email;
    private String phone;
    private String createdBy;// cant change
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date createdOn;// cant change
    private String updatedBy;// updatedBy
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date updatedOn;// updatedOn
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date dateOfJoining;// add doj=(pass from api)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    @JsonBackReference
    private Department department;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Date getDateOfJoining() {

        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {

        this.dateOfJoining = dateOfJoining;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
                + updatedBy + ", updatedOn=" + updatedOn + ", dateOfJoining=" + dateOfJoining + ", department=" + department + "]";
    }

    public Employee(Integer empId, String name, String email, String phone, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Date dateOfJoining,
            Department department) {
        super();
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.dateOfJoining = dateOfJoining;
        this.department = department;
    }

    public Employee() {

    }

}

