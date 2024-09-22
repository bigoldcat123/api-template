package com.czh.api.system.service;

import com.czh.api.system.entity.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 验证用户表 服务类
 * </p>
 *
 * @author czh
 * @since 2024-04-22
 */
public interface IUserAuthService extends IService<UserAuth> {
    UserAuth getUserAuthByUsername(String username);

    UserAuth getUserAuthByEmail(String email);
}
