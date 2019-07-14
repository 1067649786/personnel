package org.sang;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.bean.AdjustSalary;
import org.sang.bean.Department;
import org.sang.bean.EmpTrain;
import org.sang.bean.InfoStatistics;
import org.sang.common.DateConverter;
import org.sang.mapper.PersonnelMapper;
import org.sang.service.PersonnelService;
import org.sang.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonnelServiceTest {

    @Autowired
    StatisticsService statisticsService;

    @Test
    public void test(){

        List<InfoStatistics> infoStatisticsList=new LinkedList<>();

        List<Department> departments=new ArrayList<>();
        departments=statisticsService.getAllDeps();

        for (Department department:departments){
            InfoStatistics infoStatistics=new InfoStatistics();
            infoStatistics.setDepName(department.getName());
            infoStatistics.setPeopleCount(statisticsService.getAllPeoplebyDepId(department.getId()));
            infoStatistics.setJoinCount(statisticsService.getJoinCount(department.getId()));
            infoStatisticsList.add(infoStatistics);
        }

        System.out.println(infoStatisticsList);
    }
}
