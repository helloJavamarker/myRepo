package com.test.mark.zhang.test.agency.wang.guava.cache;

import com.google.common.base.MoreObjects;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/18
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class Employee {
    private final String name;
    private final String dept;
    private final String empID;
    private final byte[] data = new byte[1024 * 1024];

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getEmpID() {
        return empID;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", this.getName()).add("Department", getDept())
                .add("EmployeeID", this.getEmpID()).toString();
    }

    @Override
    protected void finalize() throws Throwable {
        //标记的时候会调用这个方法,真正执行gc的时候并不会被调用
        //可以在这个方法里面进行自救===还能找到roots(root根),---给一次机会  todo
        System.out.println("The name " + getName() + " will be GC.");
    }
}