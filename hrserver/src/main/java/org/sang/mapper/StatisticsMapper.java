package org.sang.mapper;

import org.sang.bean.Department;

import java.util.List;

public interface StatisticsMapper {

    int getJoinCount(Long depId);

    int getAllPeoplebyDepId(Long depId);

    List<Department> getAllDeps();
}
