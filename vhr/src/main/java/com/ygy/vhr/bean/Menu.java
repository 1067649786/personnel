package com.ygy.vhr.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * menu表是一个资源表，该表涉及到的字段有点多，由于我们前端采用了Vue来做，因此当用户登陆成功之后，系统将根据用户的角色动态加载需要的模块，
 * 所有模块的信息将保存在menu表中，menu表中的path、component、iconCls、keepAlive、requireAuth等字段都是Vue-Router中需要的字段，
 * 也就是说menu中的数据到时候会以json的形式返回给前端，再由vue动态更新router，menu中还有一个字段url，表示一个url pattern，即路径匹配规则，
 * 假设有一个路径匹配规则为/admin/**，那么当用户在客户端发起一个/admin/user的请求，将被/admin/**拦截到，系统再去查看这个规则对应的角色是哪些，
 * 然后再去查看该用户是否具备相应的角色，进而判断请求是否合法。
 */
public class Menu implements Serializable {
    private Long id;
    private String url;
    private String path;
    private Object component;
    private String name;
    private String iconCls;
    private Long parentId;
    private List<Role> roles;
    private List<Menu> children;
    private MenuMeta meta;

    public MenuMeta getMeta() {
        return meta;
    }

    public void setMeta(MenuMeta meta) {
        this.meta = meta;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public Object getComponent() {
        return component;
    }

    public void setComponent(Object component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    @JsonIgnore
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @JsonIgnore
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
