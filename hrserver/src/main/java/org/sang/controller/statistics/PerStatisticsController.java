package org.sang.controller.statistics;


import org.sang.bean.*;
import org.sang.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/statistics/recored")
public class PerStatisticsController {

    @Autowired
    PersonnelService personnelService;

    @RequestMapping(value = "/personnel",method = RequestMethod.GET)
    public List<Statistics> getAllPer(){

        List<Statistics> statisticsList=new LinkedList<>();

        if(personnelService.getAllEmpEc()!=null){
            List<EmpEc> empEcs=new ArrayList<>();
            empEcs=personnelService.getAllEmpEc();
            for(EmpEc empEc:empEcs){
                Statistics statistics=new Statistics();
                statistics.setType("奖惩");
                statistics.setEid(empEc.getEid());
                statistics.setRemark(empEc.getRemark());
                statistics.settDate(empEc.getEcDate());
                statisticsList.add(statistics);
            }
        }

        if(personnelService.getAllAdjustSalary()!=null){
            List<AdjustSalary> adjustSalaries=new ArrayList<>();
            adjustSalaries=personnelService.getAllAdjustSalary();
            for (AdjustSalary adjustSalary:adjustSalaries){
                Statistics statistics=new Statistics();
                statistics.setType("调薪");
                statistics.setEid(adjustSalary.getEid());
                statistics.settDate(adjustSalary.getAsDate());
                statistics.setRemark(adjustSalary.getRemark());
                statisticsList.add(statistics);
            }
        }

        if(personnelService.getAllEmpMove()!=null){
            List<EmpMove> empMoves=new ArrayList<>();
            empMoves=personnelService.getAllEmpMove();
            for(EmpMove empMove:empMoves){
                Statistics statistics=new Statistics();
                statistics.setType("调动");
                statistics.setEid(empMove.getEid());
                statistics.setRemark(empMove.getRemark());
                statistics.settDate(empMove.getRemoveDate());
                statisticsList.add(statistics);
            }
        }

        if(personnelService.getAllEmpTrains()!=null){
            List<EmpTrain> empTrains=new ArrayList<>();
            empTrains=personnelService.getAllEmpTrains();
            for(EmpTrain empTrain:empTrains){
                Statistics statistics=new Statistics();
                statistics.setType("培训");
                statistics.settDate(empTrain.getTrainDate());
                statistics.setRemark(empTrain.getRemark());
                statistics.setEid(empTrain.getEid());
                statisticsList.add(statistics);
            }
        }

        return statisticsList;
    }
}
