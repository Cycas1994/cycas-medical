package com.cycas.analyse.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cycas.analyse.biz.entity.User;
import com.cycas.analyse.biz.mapper.UserMapper;
import com.cycas.analyse.biz.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xin.na
 * @since 2024/7/18 17:44
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
