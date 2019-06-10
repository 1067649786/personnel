package com.ygy.vhr.bean;

import java.io.Serializable;

/**
 * 角色表，name字段表示角色的英文名称，按照SpringSecurity的规范
 * 将以ROLE_开始，nameZh字段表示角色的中文名称
 */
public class Role implements Serializable {
    private Long id;
    private String name;
    private String nameZh;

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
