package learn.basic.learnbasic.role.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 基础实体
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
public class Entity<T> extends SuperEntity<T> {

    public static final String UPDATE_TIME = "updateTime";
    public static final String UPDATE_USER = "updateUser";
    public static final String DELETED = "deleted";

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    protected String updateUser;


    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

    public Entity() {
    }

    public Entity(T id, Date createTime, String createUser, Date updateTime, String updateUser, Integer deleted) {
        super(id, createTime, createUser);
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.deleted = deleted;
    }

}
