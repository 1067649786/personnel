package org.sang;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.bean.AdjustSalary;
import org.sang.bean.EmpTrain;
import org.sang.common.DateConverter;
import org.sang.mapper.PersonnelMapper;
import org.sang.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonnelServiceTest {

    @Autowired
    PersonnelService personnelService;

    @Autowired
    PersonnelMapper personnelMapper;

    @Test
    public void test(){
        AdjustSalary adjustSalary=new AdjustSalary();
        //adjustSalary.setAfterSalary(100);
        //adjustSalary.setBeforeSalary(500);
        //adjustSalary.setEid(1L);
        //adjustSalary.setAsDate(new DateConverter().convert("1998-08-08"));
        adjustSalary.setReason("321");
        adjustSalary.setId(1L);
        //adjustSalary.setRemark("123");
        System.out.println(adjustSalary);
        personnelMapper.updateAdjustSalary(adjustSalary);
        //personnelService.addEmpTrain(empTrain);
    }
}
