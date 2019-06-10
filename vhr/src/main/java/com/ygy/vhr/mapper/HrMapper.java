package com.ygy.vhr.mapper;

import com.ygy.vhr.bean.Hr;
import com.ygy.vhr.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrMapper {

    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Long id);
}
