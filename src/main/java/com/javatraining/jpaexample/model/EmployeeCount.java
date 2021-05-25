package com.javatraining.jpaexample.model;

import java.math.BigInteger;

public class EmployeeCount {
    private String deptName;

    private BigInteger count;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "EmployeeCount [deptName=" + deptName + ", count=" + count + "]";
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

}
