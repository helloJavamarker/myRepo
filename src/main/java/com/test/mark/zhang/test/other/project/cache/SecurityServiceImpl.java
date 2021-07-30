package com.test.mark.zhang.test.other.project.cache;

import com.test.mark.zhang.test.other.project.cache.mapper.SecurityZoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author by mark
 * @Classname SecurityService
 * @Description TODO
 * @Date 2021/7/21 2:34 下午
 */
@Component
public class SecurityServiceImpl implements SecurityZoneService{

    @Autowired
    private SecurityZoneDao securityZoneDao;
    @Override
    public List<Role> getAll() {
        return securityZoneDao.getAll();
    }

    @Override
    public int count() {
        return securityZoneDao.count();
    }
}
