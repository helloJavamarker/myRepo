package com.test.mark.zhang.test.agency.heima.disign.day5.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: StudentAggregateImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class StudentAggregateImpl implements StudentAggregate {

    private List<Student> list = new ArrayList<Student>();

    @Override
    public void addStudent(Student stu) {
        list.add(stu);
    }

    @Override
    public void removeStudent(Student stu) {
        list.remove(stu);
    }

    //获取迭代器对象
    @Override
    public StudentIterator getStudentIterator() {
        return new StudentIteratorImpl(list);
    }
}
