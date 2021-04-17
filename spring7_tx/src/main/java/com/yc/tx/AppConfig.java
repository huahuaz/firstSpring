package com.yc.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration  //表示当前的类是一个配置类
@ComponentScan(basePackages = "com.yc")  //将来要托管的bean要扫描的包及子包
public class AppConfig {    //java的容器配置

    //bean容器
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        DataSource ds = new ComboPooledDataSource();
        ((ComboPooledDataSource) ds).setDriverClass("com.mysql.cj.jdbc.Driver");
        ((ComboPooledDataSource) ds).setJdbcUrl("jdbc:mysql://localhost:3306/testbank?serverTimezone=GMT%2B8");
        ((ComboPooledDataSource) ds).setUser("root");
        ((ComboPooledDataSource) ds).setPassword("a");
        return ds;
    }
}
