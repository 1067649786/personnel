package org.sang.service;

import org.sang.bean.AdjustSalary;
import org.sang.bean.EmpEc;
import org.sang.bean.EmpMove;
import org.sang.bean.EmpTrain;
import org.sang.mapper.PersonnelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonnelService {

    @Autowired
    PersonnelMapper personnelMapper;


    public int addEc(EmpEc empEc){
        return personnelMapper.addEc(empEc);
    }

    public List<EmpEc> getAllEmpEc(){
        return personnelMapper.getAllEmpEc();
    }


    public int addEmpTrain(EmpTrain empTrain){
        return personnelMapper.addEmpTrain(empTrain);
    }

    public int updateEmpTrain(EmpTrain empTrain){
        return personnelMapper.updateEmpTrain(empTrain);
    }

    public int deleteEmpTrain(String ids){
        String[] split=ids.split(",");
        return personnelMapper.deleteEmpTrain(split);
    }

    public List<EmpTrain> getAllEmpTrains(){
        return personnelMapper.getAllEmpTrains();
    }


    public int addAdjustSalary(AdjustSalary adjustSalary){
        return personnelMapper.addAdjustSalary(adjustSalary);
    }

    public int updateAdjustSalary(AdjustSalary adjustSalary){
        return personnelMapper.updateAdjustSalary(adjustSalary);
    }

    public int deleteAdjustSalary(String ids){
        String[] split = ids.split(",");
        return personnelMapper.deleteAdjustSalary(split);
    }

    public List<AdjustSalary> getAllAdjustSalary(){
        return personnelMapper.getAllAdjustSalary();
    }


    public int addEmpMove(EmpMove empMove){
        return personnelMapper.addEmpMove(empMove);
    }

    public int updateEmpMove(EmpMove empMove){
        return personnelMapper.updateEmpMove(empMove);
    }

    public int deleteEmpMove(String ids){
        String[] split = ids.split(",");
        return personnelMapper.deleteEmpMove(split);
    }

    public List<EmpMove> getAllEmpMove(){
        return personnelMapper.getAllEmpMove();
    }

    public int updateDepIdAndJobId(EmpMove empMove){
        return personnelMapper.updateDepIdAndJobId(empMove);
    }
}
