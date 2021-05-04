package com.yc.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Resfood implements Serializable {
    private static final long serialVersionUID = 1274678680760308733L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;
    private String fname;
    private BigDecimal normprice;
    private BigDecimal realprice;
    private String detail;
    private String fphoto;
}
