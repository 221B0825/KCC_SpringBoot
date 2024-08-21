package com.kcc.restaurant.mapper;

import com.kcc.restaurant.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
    public void createMenu(Menu menu);
    public void deleteMenu(int id);
}
