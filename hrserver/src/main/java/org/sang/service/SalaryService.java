package org.sang.service;

import org.sang.bean.Salary;
import org.sang.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sang on 2018/1/24.
 */
@Service
@Transactional
public class SalaryService {
    @Autowired
    SalaryMapper salaryMapper;
    public int addSalary(Salary salary) {
        return salaryMapper.addSalary(salary);
    }

    public List<Salary> getAllSalary() {
        return salaryMapper.getAllSalary();
    }

    public int updateSalary(Salary salary) {
        return salaryMapper.updateSalary(salary);
    }

    public int deleteSalary(String ids) {
        String[] split = ids.split(",");
        return salaryMapper.deleteSalary(split);
    }

    public int updateEmpSalary(Integer sid, Long eid) {
        salaryMapper.deleteSalaryByEid(eid);
        return salaryMapper.addSidAndEid(sid,eid);
    }


    public int getLeaveCount(Long eid){
        return salaryMapper.getLeaveCount(eid);
    }

    public int getLateCount(Long eid){
        return salaryMapper.getLateCount(eid);
    }

    public int getOvertime(Long eid){
        return salaryMapper.getOvertime(eid);
    }

    public Salary getSalaryById(Long id){
        return salaryMapper.getSalaryById(id);
    }

}
