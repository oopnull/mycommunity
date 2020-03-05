package my.pro.acommunity.service;

import my.pro.acommunity.mapper.UserMapper;
import my.pro.acommunity.model.User;
import my.pro.acommunity.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==0) {
          //插入
          user.setGmtCreate(System.currentTimeMillis());//当前时间
          user.getGmtModified(user.getGmtCreate());
          userMapper.insert(user);
      }else{
            //更新
            User dbUser = users.get(0);
            User updateUser=new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());

            UserExample example=new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser,example);
      }
    }
}
