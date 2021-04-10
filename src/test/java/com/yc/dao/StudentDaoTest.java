package com.yc.dao;

import com.yc.biz.StudentBizImpl;
import org.junit.Before;
import org.junit.Test;

public class StudentDaoTest {
    private StudentDao studentDao;
    private StudentBizImpl studentBizImpl;

    @Before
    public void setUp() throws Exception {
        //1. 能否自动完成  实例化对象 -> IOC控制反转 -> 由容器实例化对象，由容器完成
        studentDao = new StudentDaoJpaImpl();

        //studentBizImpl = new StudentBizImpl(studentDao); // IOC

        studentBizImpl = new StudentBizImpl();
        //2. 能否自动完成 装配过程 -> DI 依赖注入 -> 由容器装配对象
        studentBizImpl.setStudentDao(studentDao);
    }

    @Test
    public void testAdd() {
        studentDao.add("张三");
    }

    @Test
    public void testUpdate() {
        studentDao.update("张三");
    }

    @Test
    public void testBizAdd() {
        studentDao.add("张三");
    }
}