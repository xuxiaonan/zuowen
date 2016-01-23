package com.zuowen.magic.db.service;

import com.zuowen.magic.db.model.MagicList;

import de.greenrobot.dao.AbstractDao;

/**
 * Created by worm on 2016/1/17.
 */
public class MagicListService extends BaseService<MagicList,Long> {


    public MagicListService(AbstractDao dao) {
        super(dao);
    }



}
