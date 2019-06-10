package com.ygy.vhr.service;

import com.ygy.vhr.bean.Hr;
import com.ygy.vhr.mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Hr hr = hrMapper.loadUserByUsername(s);
        if(hr==null)
            throw new UsernameNotFoundException("用户名不对");
        return hr;
    }
}
