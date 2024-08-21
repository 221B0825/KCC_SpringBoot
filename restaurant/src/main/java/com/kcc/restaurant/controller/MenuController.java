package com.kcc.restaurant.controller;

import com.kcc.restaurant.bean.Menu;
import com.kcc.restaurant.service.MenuService;
import oracle.ucp.proxy.annotation.Post;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menu")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu newMenu = menuService.createMenu(menu);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newMenu.getId()).toUri();
        return ResponseEntity.created(location).body(newMenu);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable int id) {
        menuService.deleteMenu(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body("메뉴 삭제 완료");
    }
}
