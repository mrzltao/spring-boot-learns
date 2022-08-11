package learn.basic.learnbasic.role.controller;


import com.baomidou.mybatisplus.extension.api.R;
import learn.basic.learnbasic.role.entity.SystemRole;
import learn.basic.learnbasic.role.service.SystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ltter
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/system-role")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @GetMapping("/findAllRole")
    public List<SystemRole> findAllRole(){
        List<SystemRole> list = systemRoleService.list();
        return list;
    }

    @PostMapping("/saveRole")
    public String saveRole(@Validated @RequestBody SystemRole role){
        role.setId(UUID.randomUUID().toString().replace("-",""));
        /*role.setCreateTime(new Date());
        role.setCreateUser("1");
        role.setUpdateTime(new Date());
        role.setUpdateUser("2");
        role.setDeleted(0);*/
        systemRoleService.save(role);
        return "success";
    }
}

