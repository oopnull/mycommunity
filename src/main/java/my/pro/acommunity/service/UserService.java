package my.pro.acommunity.service;

import my.pro.acommunity.mapper.UserMapper;
import my.pro.acommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
      User dbUser = userMapper.findByAccountId(user.getAccountId());
      if(dbUser==null) {
          //插入
          user.setGmtCreate(System.currentTimeMillis());//当前时间
          user.getGmtModified(user.getGmtCreate());
          userMapper.insert(user);
      }else{
          dbUser.setGmtModified(System.currentTimeMillis());
          dbUser.setAvatarUrl(user.getAvatarUrl());
          dbUser.setName(user.getName());
          dbUser.setToken(user.getToken());
          userMapper.update(dbUser);
      }
    }
}
