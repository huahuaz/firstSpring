package com.yc.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity   //实体类
@Data
public class Resadmin implements Serializable {
    private static final long serialVersionUID = -7293575608047826378L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer raid;   //用引用类型
    private String raname;
    private String rapwd;
}
