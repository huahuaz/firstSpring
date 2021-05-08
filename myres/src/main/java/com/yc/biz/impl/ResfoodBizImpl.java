package com.yc.biz.impl;

import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBiz;
import com.yc.dao.ResfoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResfoodBizImpl implements ResfoodBiz {

    @Autowired
    private ResfoodDao resfoodDao;

    @Override
    @Transactional(readOnly = true)
    public List<Resfood> findAll() {
        return resfoodDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Resfood findByFid(Integer fid) {
        Resfood rf = new Resfood();
        rf.setFid(fid);
        Example<Resfood> example = Example.of(rf);
        Optional<Resfood> opt = resfoodDao.findOne(example);
        return opt.get();
    }

    @Override
    public List<Resfood> findAll(int page, int count) {
        Sort sort = Sort.by(Sort.Direction.ASC, "fid");
        Pageable pageable = PageRequest.of(page - 1, count, sort);
        Page<Resfood> list = resfoodDao.findAll(pageable);
        System.out.println(list.getTotalPages());
        return list.getContent();
    }
}
