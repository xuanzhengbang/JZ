package com.opopto.zdsetmealfood.service;

import com.opopto.zdsetmealfood.dao.ZdUserMapper;
import com.opopto.zdsetmealfood.entity.ZdUser;
import com.opopto.zdsetmealfood.entity.ZdUserExample;
import com.opopto.zdsetmealfood.helper.EncryptHelper;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CommonsLog
public class ZdUserService {

    @Autowired
    private ZdUserMapper userMapper;

    public ZdUser login(String name, String pass){
        String encryPass = EncryptHelper.MD5(pass);

        ZdUserExample example = new ZdUserExample();
        example.createCriteria()
                .andDelFlagEqualTo(0)
                .andUserNameEqualTo(name)
                .andPasswordEqualTo(encryPass);
        List<ZdUser> list = userMapper.selectByExample(example);
        if(list.isEmpty()){
            log.error("login fail with " + name);
            return null;
        }
        return list.get(0);
    }

}
