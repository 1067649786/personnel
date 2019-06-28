package org.sang.bean;

import java.util.Date;

public class EmpMove {

    private Long id;
    private Long eid;
    private Long afterDepId;
    private Long afterJobId;
    private Date removeDate;
    private String reason;
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

    public Long getAfterDepId() {
        return afterDepId;
    }

    public void setAfterDepId(Long afterDepId) {
        this.afterDepId = afterDepId;
    }

    public Long getAfterJobId() {
        return afterJobId;
    }

    public void setAfterJobId(Long afterJobId) {
        this.afterJobId = afterJobId;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "EmpMove{" +
                "id=" + id +
                ", eid=" + eid +
                ", afterDepId=" + afterDepId +
                ", afterJobId=" + afterJobId +
                ", removeDate=" + removeDate +
                ", reason='" + reason + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
