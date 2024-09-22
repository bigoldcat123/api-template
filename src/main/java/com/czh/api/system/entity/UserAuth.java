package com.czh.api.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.czh.api.common.CurrentUser;
import lombok.Data;

/**
 * <p>
 * 验证用户表
 * </p>
 *
 * @author czh
 * @since 2024-04-22
 */
//TODO 这里是需要修改的
@Data
@TableName("_user")
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码 默认root
     */
    @TableField("psw")
    private String password;

    /**
     * 邮箱
     */
    @TableField("_name")
    private String _name;

    @TableField("sex")
    private String sex;
    /**
     * 更新事件
     */
    @TableField("companyID")
    private String companyID;

    @TableField("_position")
    private String position;

    @TableField("phone")
    private String phone;


    @TableField("priv")
    private String priv;

    public CurrentUser toCurrentUser() {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(id);
        currentUser.setUsername(username);
        currentUser.setEmail("email");
        return currentUser;
    }

}
