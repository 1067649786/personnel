package org.sang.controller.personnel;

import org.sang.bean.AdjustSalary;
import org.sang.bean.RespBean;
import org.sang.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personnel/adjust")
public class EmpSalaryController {

    @Autowired
    PersonnelService personnelService;

    @RequestMapping(value = "/salary",method = RequestMethod.POST)
    public RespBean addAdjustSalary(AdjustSalary adjustSalary){
        if(personnelService.addAdjustSalary(adjustSalary)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @RequestMapping(value = "/salary",method = RequestMethod.GET)
    public List<AdjustSalary> getAllAdjustSalary(){
        return personnelService.getAllAdjustSalary();
    }

    @RequestMapping(value = "/salary",method = RequestMethod.PUT)
    public RespBean updateAdjustSalary(AdjustSalary adjustSalary){
        if(personnelService.updateAdjustSalary(adjustSalary)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @RequestMapping(value = "/salary/{ids}",method = RequestMethod.DELETE)
    public RespBean deleteAdjustSalary(@PathVariable String ids){
        if(personnelService.deleteAdjustSalary(ids)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
