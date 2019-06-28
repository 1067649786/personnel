package org.sang.bean;

import java.util.Date;

public class AdjustSalary {

    private Long id;
    private Long eid;
    private Date asDate;
    private Integer beforeSalary;
    private Integer afterSalary;
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

    public Date getAsDate() {
        return asDate;
    }

    public void setAsDate(Date asDate) {
        this.asDate = asDate;
    }

    public Integer getBeforeSalary() {
        return beforeSalary;
    }

    public void setBeforeSalary(Integer beforeSalary) {
        this.beforeSalary = beforeSalary;
    }

    public Integer getAfterSalary() {
        return afterSalary;
    }

    public void setAfterSalary(Integer afterSalary) {
        this.afterSalary = afterSalary;
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
        return "AdjustSalary{" +
                "id=" + id +
                ", eid=" + eid +
                ", asDate=" + asDate +
                ", beforeSalary=" + beforeSalary +
                ", afterSalary=" + afterSalary +
                ", reason='" + reason + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
