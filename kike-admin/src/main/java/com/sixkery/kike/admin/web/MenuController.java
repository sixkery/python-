package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.service.MenuService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户相关操作
 *
 * @author: sixkery
 * @date:2021/4/5
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 查询菜单 tree
     *
     * @return 角色信息
     */
    @GetMapping("/treeList")
    public ApiResponses<Object> treeList() {

        return ApiResponses.ok(menuService.treeList());
    }


}
