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

    List<EmpEc> getAllEmpEc();



    int addEmpTrain(EmpTrain empTrain);

    int updateEmpTrain(EmpTrain empTrain);

    int deleteEmpTrain(@Param("ids") String[] ids);

    List<EmpTrain> getAllEmpTrains();



    int addAdjustSalary(@Param("adjustSalary") AdjustSalary adjustSalary);

    int updateAdjustSalary(@Param("adjustSalary") AdjustSalary adjustSalary);

    int deleteAdjustSalary(@Param("ids") String[] ids);

    List<AdjustSalary> getAllAdjustSalary();




    int addEmpMove(@Param("empMove") EmpMove empMove);

    int updateEmpMove(@Param("empMove") EmpMove empMove);

    int deleteEmpMove(@Param("ids") String[] ids);

    List<EmpMove> getAllEmpMove();

    int updateDepIdAndJobId(@Param("empMove") EmpMove empMove);

}
