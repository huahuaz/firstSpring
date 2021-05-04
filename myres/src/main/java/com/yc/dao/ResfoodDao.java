package com.yc.dao;

import com.yc.bean.Resfood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResfoodDao extends JpaRepository<Resfood, Integer> {
}