package com.yc.starter.tx.dao;

import com.yc.starter.tx.bean.OpRecord;

import java.util.List;

public interface OpRecordDao {
    public void saveOpRecord(OpRecord opRecord);

    public List<OpRecord> findAll();

    public List<OpRecord> findByAccountid(int accountid);
}
