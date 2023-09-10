package com.anhbui.bookstore.controller.admin;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/roles_management")
public class AdminRoleController extends BaseController {
    private final RoleService roleService;
}

