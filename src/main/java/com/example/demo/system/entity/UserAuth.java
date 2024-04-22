package com.example.demo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.demo.common.CurrentUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 验证用户表
 * </p>
 *
 * @author czh
 * @since 2024-04-22
 */
@Data
@TableName("user_auth")
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码 默认root
     */
    @TableField("password")
    private String password;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 更新事件
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


    public CurrentUser toCurrentUser() {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(id);
        currentUser.setUsername(username);
        currentUser.setEmail(email);
        return currentUser;
    }

}
