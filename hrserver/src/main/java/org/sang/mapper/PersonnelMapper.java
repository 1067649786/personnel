package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.AdjustSalary;
import org.sang.bean.EmpEc;
import org.sang.bean.EmpMove;
import org.sang.bean.EmpTrain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelMapper {


    int addEc(EmpEc empEc);

    int addEmpTrain(EmpTrain empTrain);

    int updateEmpTrain(EmpTrain empTrain);

    int deleteEmpTrain(@Param("ids") String[] ids);

    List<EmpTrain> getAllEmpTrains();

    int addAdjustSalary(AdjustSalary adjustSalary);

    int updateAdjustSalary(AdjustSalary adjustSalary);

    int deleteAdjustSalary(@Param("ids") String[] ids);

    List<AdjustSalary> getAllAdjustSalary();

    int addEmpMove(EmpMove empMove);

    int updateEmpMove(EmpMove empMove);

    List<EmpMove> getAllEmpMove();

}
