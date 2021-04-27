package com.yc.starter.tx.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -1061008712748603102L;

    private Integer code;
    private T data;
    private String msg;
}
