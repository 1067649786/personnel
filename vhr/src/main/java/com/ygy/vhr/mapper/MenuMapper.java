package com.ygy.vhr.mapper;

import com.ygy.vhr.bean.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单栏管理
 */
@Repository
public interface MenuMapper {

    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
