package com.javatraining.jpaexample.model;

import java.math.BigInteger;

public class EmployeeTotalCount {
    private BigInteger count;

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "EmployeeTotalCount [count=" + count + "]";
    }
}
