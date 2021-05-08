package com.yc.biz;

import com.yc.bean.Resfood;

import java.util.List;

public interface ResfoodBiz {

    /**
     * 查找所有的菜
     */
    public List<Resfood> findAll();

    /**
     * 根据fid查找某个菜
     *
     * @param fid
     */
    public Resfood findByFid(Integer fid);

    /**
     * 分页查询
     * @param page  页数
     * @param count   页面大小
     * @return
     */
    public List<Resfood> findAll(int page, int count);
}
