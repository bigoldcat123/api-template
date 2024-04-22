package com.example.demo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.system.entity.UserAuth;
import com.example.demo.system.mapper.UserAuthMapper;
import com.example.demo.system.service.IUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 验证用户表 服务实现类
 * </p>
 *
 * @author czh
 * @since 2024-04-22
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

    @Override
    public UserAuth getUserAuthByUsername(String username) {
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public UserAuth getUserAuthByEmail(String email) {
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return baseMapper.selectOne(queryWrapper);
    }
}
