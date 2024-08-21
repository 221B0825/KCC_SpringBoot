package com.kcc.restaurant.service;

import com.kcc.restaurant.bean.Menu;
import com.kcc.restaurant.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public Menu createMenu(Menu menu){
        menuMapper.createMenu(menu);
        return menu;
    }

    public void deleteMenu(int id){
        menuMapper.deleteMenu(id);
    }
}
