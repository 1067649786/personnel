package org.sang.service;

import org.sang.bean.Department;
import org.sang.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatisticsService {

    @Autowired
    StatisticsMapper statisticsMapper;

    public int getJoinCount(Long depId){
        return statisticsMapper.getJoinCount(depId);
    }

    public int getAllPeoplebyDepId(Long depId){
        return statisticsMapper.getAllPeoplebyDepId(depId);
    }

    public List<Department> getAllDeps(){
        return statisticsMapper.getAllDeps();
    }
}
