package org.sang.controller.personnel;

import org.sang.bean.EmpMove;
import org.sang.bean.RespBean;
import org.sang.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personnel/remove")
public class EmpRemoveController {

    @Autowired
    PersonnelService personnelService;

    @RequestMapping(value = "/move",method = RequestMethod.POST)
    public RespBean addEmpMove(EmpMove empMove){
        if(personnelService.addEmpMove(empMove)==1 && personnelService.updateDepIdAndJobId(empMove)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @RequestMapping(value = "/move",method = RequestMethod.PUT)
    public RespBean updateEmpMove(EmpMove empMove){
        if(personnelService.updateEmpMove(empMove)==1 && personnelService.updateDepIdAndJobId(empMove)==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @RequestMapping(value = "/move",method = RequestMethod.GET)
    public List<EmpMove> getAllEmpMove(){
        return personnelService.getAllEmpMove();
    }

    @RequestMapping(value = "/move/{ids}",method = RequestMethod.DELETE)
    public RespBean deleteEmpMove(@PathVariable String ids){
        if(personnelService.deleteEmpMove(ids)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
