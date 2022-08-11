package learn.basic.learnbasic.role.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import learn.basic.learnbasic.role.config.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ltter
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_role")
public class SystemRole extends Entity<String> {

    private String roleCode;

    private String roleName;

    private String menuCodes;

    private String remark;

}
