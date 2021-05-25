package com.javatraining.jpaexample.jpa.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // auto-increment
    private Integer deptId;

    @Column(name = "d_name")
    private String dName;
    private String description;
    private String createdBy;
    private String createdOn;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Employee> employee;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        /*
         * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); String date =
         * formatter.format(createdOn);
         */
        this.createdOn = createdOn;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", dName=" + dName + ", description=" + description + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", employee="
                + employee + "]";
    }



}
