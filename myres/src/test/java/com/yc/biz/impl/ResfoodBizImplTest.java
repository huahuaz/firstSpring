package com.yc.biz.impl;

import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBiz;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ResfoodBizImplTest {

    @Autowired
    private ResfoodBiz resfoodBiz;

    @Test
    public void findAll() {
        //List<Resfood> list = resfoodBiz.findAll();
        int page = 1;
        int count = 5;
        List<Resfood> list = resfoodBiz.findAll(page,count);
        System.out.println(list);
    }

    @Test
    public void findByFid() {
        Resfood rf = resfoodBiz.findByFid(1);
        Assert.assertNotNull(rf);
    }
}