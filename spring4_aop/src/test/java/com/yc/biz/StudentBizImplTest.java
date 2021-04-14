package com.yc.biz;

import com.yc.MyAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyAppConfig.class)
public class StudentBizImplTest {
    @Autowired      //按类型注入，如有多个同类托管bean，则报错，要使用@Qualifier
    //@Resource(name = "studentBizImpl")  //先按名字注入，如不行再按类型注入，此时，也不能存在多个同类托管bean
    private StudentBiz sbi;

    @Test
    public void add() {
        sbi.add("张三");
    }

    @Test
    public void update() {
        sbi.update("李四");
    }

    @Test
    public void find() {
        sbi.find("王五");
    }
}