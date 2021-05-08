package com.yc.biz.impl;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBiz;
import com.yc.dao.ResuserDao;
import com.yc.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class ResuserBizImpl implements ResuserBiz {

    @Autowired
    private ResuserDao resuserDao;

    @Override
    @Transactional(readOnly = true)
    public Resuser login(Resuser user) {
        user.setPwd(Encrypt.md5(user.getPwd()));  //业务层完成原始密码的加密
        Example<Resuser> example = Example.of(user);
        Optional<Resuser> opt = resuserDao.findOne(example);
        return opt.orElseGet(new Supplier<Resuser>() {
            @Override
            public Resuser get() {
                return null;
            }
        });
    }
}
