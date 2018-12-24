package cn.edu.whu.waytojavadiscussion.service;

import cn.edu.whu.waytojavadiscussion.pojo.User;
import com.github.pagehelper.PageInfo;

/**
 * @author Jimmy
 * @version 2018/12/21
 * 用户服务接口
 */

public interface UserService {
    /**
     * 插入用户
     * @param user 用户
     * @return 影响条数
     */
    int addUser(User user);

    /**
     * 获取所有用户
     * @param pageSize 开始页数
     * @param pageNum 每页条数
     * @return 用户列表
     */
    PageInfo<User> getUsers(int pageNum, int pageSize);

    /**
     * 根据ID获取用户
     * @param userId 用户ID
     * @return 用户
     */
    User getUserByUserId(int userId);
}
