package org.sang.controller.salary;


import org.sang.bean.Employee;
import org.sang.bean.SalSearch;
import org.sang.bean.Salary;
import org.sang.service.EmpService;
import org.sang.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salary/search")
public class SalarySearchController {

    @Autowired
    SalaryService salaryService;

    @Autowired
    EmpService empService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Map<String, Object> getSalaryByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){

        Map<String, Object> map = new HashMap<>();
        List<Employee> employeeByPage = empService.getEmployeeByPageShort(page, size);
        Long count = empService.getCountByKeywords("", null, null, null, null, null, null, null);

        List<SalSearch> salSearches=new LinkedList<>();

        for(Employee employee:employeeByPage){
            SalSearch salSearch=new SalSearch();
            Salary salary=salaryService.getSalaryById(employee.getId());
            salSearch.setName(employee.getName());
            salSearch.setWorkID(employee.getWorkID());
            salSearch.setBonus(salaryService.getOvertime(employee.getId())*100);
            salSearch.setFine(salaryService.getLateCount(employee.getId())*50);
            salSearch.setPayment(salary.getBasicSalary()+salary.getBonus()+salary.getLunchSalary()+salary.getTrafficSalary()+salaryService.getOvertime(employee.getId())*100-salaryService.getLateCount(employee.getId())*50);
            salSearches.add(salSearch);
        }

        map.put("salaries",salSearches);
        map.put("count",count);
        return map;
    }
}
