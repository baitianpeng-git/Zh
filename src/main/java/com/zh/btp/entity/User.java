package com.zh.btp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author btp
 * @since 2022-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("User")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String remark;

    // 创建时间  意思是新建的时候插入
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    // 创建人  意思是新建的时候插入
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    // 修改时间  意思是新建和修改的时候插入
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

    // 修改人  意思是新建和修改的时候插入
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
