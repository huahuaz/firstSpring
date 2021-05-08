package com.yc.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class Resorder implements Serializable {
    private static final long serialVersionUID = 7826956444166766038L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roid;
    private Integer userid;
    private String address;
    private String tel;
    private Timestamp ordertime;
    private Timestamp deliverytime;   //Po中用的却是Timestamp
    private String ps;
    private Integer status;

    @Transient
    private String deliverytimeString;  //Vo中界面的参数类型

    public Timestamp getDeliveryTime() {
        Date d = null;
        if (deliverytimeString != null) {
            DateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm");
            try {
                d = df.parse(deliverytimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            deliverytime = new Timestamp(d.getTime());
        } else {
            d = new Date();
        }
        deliverytime = new Timestamp(d.getTime());
        return this.deliverytime;
    }
}
