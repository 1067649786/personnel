package org.sang.controller.salary;

import org.sang.bean.Department;
import org.sang.bean.Employee;
import org.sang.bean.SalaryMan;
import org.sang.service.DepartmentService;
import org.sang.service.EmpService;
import org.sang.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by sang on 2018/1/26.
 */
@RestController
@RequestMapping("/salary/table")
public class SalaryTableController {

    @Autowired
    SalaryService salaryService;
    @Autowired
    EmpService empService;

    @RequestMapping("/man")
    public Map<String, Object> getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){

        Map<String, Object> map = new HashMap<>();
        List<Employee> employeeByPage = empService.getEmployeeByPageShort(page, size);
        Long count = empService.getCountByKeywords("", null, null, null, null, null, null, null);

        List<SalaryMan> salaryManList=new LinkedList<>();
        for (Employee employee:employeeByPage){

            SalaryMan salaryMan=new SalaryMan();
            salaryMan.setName(employee.getName());
            salaryMan.setWorkID(employee.getWorkID());
            salaryMan.setLateCount(salaryService.getLateCount(employee.getId()));
            salaryMan.setLeaveCount(salaryService.getLeaveCount(employee.getId()));
            salaryMan.setOvertime(salaryService.getOvertime(employee.getId()));
            salaryManList.add(salaryMan);
        }


        map.put("salaries",salaryManList);
        map.put("count",count);
        return map;
    }
}
