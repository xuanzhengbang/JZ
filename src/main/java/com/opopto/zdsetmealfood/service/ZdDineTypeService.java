package com.opopto.zdsetmealfood.service;

import com.opopto.zdsetmealfood.dao.ZdDineTypeMapper;
import com.opopto.zdsetmealfood.entity.ZdDineType;
import com.opopto.zdsetmealfood.entity.ZdDineTypeExample;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  订餐类型
 */
@Service
@CommonsLog
public class ZdDineTypeService {

    @Autowired
    private ZdDineTypeMapper dineTypeMapper;

    public List<ZdDineType> list() {
        return dineTypeMapper.listAll();
    }

    public void delete(Integer id){
        ZdDineType dineType = new ZdDineType();
        dineType.setId(id);
        dineType.setDelFlag(1);
        dineTypeMapper.updateByPrimaryKeySelective(dineType);
    }

    public ZdDineType create(ZdDineType dineType){
        dineType.setDelFlag(0);
        dineTypeMapper.insertSelective(dineType);
        log.info("insert record to zd_dine_type by " + dineType);
        return dineType;
    }

}
