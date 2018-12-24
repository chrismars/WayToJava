package cn.edu.whu.waytojavadiscussion.service.impl;

import cn.edu.whu.waytojavadiscussion.dao.UserDao;
import cn.edu.whu.waytojavadiscussion.pojo.User;
import cn.edu.whu.waytojavadiscussion.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jimmy
 * @version 2018/12/21
 * 用户服务接口实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public PageInfo<User> getUsers(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<User> userDomains = userDao.getUsers();
        return new PageInfo(userDomains);
    }


    @Override
    public User getUserByUserId(int userId) {
        return null;
    }
}
