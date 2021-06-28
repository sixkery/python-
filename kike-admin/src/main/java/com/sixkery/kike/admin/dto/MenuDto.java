package com.sixkery.kike.admin.dto;

import com.sixkery.kike.admin.entity.system.MenuDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sixkery
 * @date 2020/10/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto extends MenuDo {

    /**
     * 子级菜单
     */
    private List<MenuDto> children;

}
