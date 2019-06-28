package org.sang.controller.personnel;

import org.sang.bean.EmpTrain;
import org.sang.bean.Employee;
import org.sang.bean.RespBean;
import org.sang.common.DateConverter;
import org.sang.service.EmpService;
import org.sang.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/personnel/train")
public class EmpTrainController {

    @Autowired
    PersonnelService personnelService;

    @Autowired
    EmpService empService;

    @RequestMapping(value = "/trains", method = RequestMethod.POST)
    public RespBean addEmpTrain(EmpTrain empTrain){
        System.out.println("========"+empTrain.getTrainDate().toString());
        //Employee employee =empService.getEmpByIdAndName(empTrain.getWorkID(),empTrain.getName());
        //if(employee==null) return RespBean.error("员工信息错误");
        if(personnelService.addEmpTrain(empTrain)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @RequestMapping(value = "/trains", method = RequestMethod.GET)
    public List<EmpTrain> getAllTrains(){
        return personnelService.getAllEmpTrains();
    }

    @RequestMapping(value = "/trains", method = RequestMethod.PUT)
    public RespBean updateEmpTrain(EmpTrain empTrain){
        System.out.println("========"+empTrain.getTrainDate().toString());
        //Employee employee=empService.getEmpByIdAndName(empTrain.getWorkID(),empTrain.getName());
        //if(employee==null) return RespBean.error("员工信息错误");
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String trainDate=simpleDateFormat.format(empTrain.getTrainDate());
        //DateConverter dateConverter=new DateConverter();
        //Date date=dateConverter.convert(trainDate);
        //System.out.println("=========="+date);
        //empTrain.setTrainDate(date);
        if(personnelService.updateEmpTrain(empTrain)==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }
    @RequestMapping(value = "/trains/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteEmpTrain(@PathVariable String ids){
        if(personnelService.deleteEmpTrain(ids)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
