package org.sang.bean;

public class InfoStatistics {

    private Integer id;
    private String depName;
    private Integer peopleCount;
    private Integer joinCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }


    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    @Override
    public String toString() {
        return "InfoStatistics{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                ", peopleCount=" + peopleCount +
                ", joinCount=" + joinCount +
                '}';
    }
}
