package com.zjh.mapper;

import com.zjh.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    public List<User> selectUser() {
        UserMapper mapper = getSqlSessionTemplate().getMapper(UserMapper.class);

        mapper.addUser(new User(10,"test111","123123"));
        mapper.deleteUser(4);

        return mapper.selectUser();
    }

    public int addUser(User user) {
        return getSqlSessionTemplate().getMapper(UserMapper.class).addUser(user);
    }

    public int deleteUser(int id) {
        return getSqlSessionTemplate().getMapper(UserMapper.class).deleteUser(id);
    }
}
