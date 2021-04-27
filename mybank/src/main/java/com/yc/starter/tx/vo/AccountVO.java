package com.yc.starter.tx.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountVO implements Serializable {
    private static final long serialVersionUID = 2508968053483234916L;

    private Integer accountId;
    private Double money;
    private Integer inAccountId;
}
