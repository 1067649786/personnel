package org.sang.bean;

import java.util.Date;

public class EmpTrain {

    private Long id;
    private Long eid;
    private Date trainDate;
    private String trainContent;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Date getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(Date trainDate) {
        this.trainDate = trainDate;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "EmpTrain{" +
                "id=" + id +
                ", eid=" + eid +
                ", trainDate=" + trainDate +
                ", trainContent='" + trainContent + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
