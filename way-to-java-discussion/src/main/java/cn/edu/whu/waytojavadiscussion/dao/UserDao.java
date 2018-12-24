package cn.edu.whu.waytojavadiscussion.dao;

import cn.edu.whu.waytojavadiscussion.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jimmy
 * @version 2018/12/21
 * 用户Mapper
 */
@Repository
public interface UserDao {
    /**
     * 插入用户
     * @param user 用户
     * @return 影响条数
     */
    int insert(User user);

    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<User> getUsers();

    /**
     * 根据ID获取用户
     * @param userId 用户ID
     * @return 用户
     */
    User getUserByUserId(int userId);
}
