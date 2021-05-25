package com.javatraining.jpaexample.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeDto {

    private Integer empId;
    // private String name;
    private String firstName;
    private String lastName;
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
    private Integer deptId;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
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
        /*
         * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); String date =
         * formatter.format(updatedOn);
         */
        this.updatedOn = updatedOn;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {

        this.dateOfJoining = dateOfJoining;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
