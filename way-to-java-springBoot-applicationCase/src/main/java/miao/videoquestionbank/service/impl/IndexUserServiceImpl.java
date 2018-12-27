package miao.videoquestionbank.service.impl;

import miao.videoquestionbank.Repository.UserRepository;
import miao.videoquestionbank.Result.Result;
import miao.videoquestionbank.Result.Status;
import miao.videoquestionbank.entity.User;
import miao.videoquestionbank.service.IndexUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexUserServiceImpl implements IndexUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Result loginByEmailAndPassword(String email, String password) {
        User user=userRepository.findByEmail(email);
        if(user==null)
            return new Result(Status.NOT_FOUND,"用户不存在");
        else if(!user.getPassword().equals(password))
            return new Result(Status.ERROR,"密码错误");
        else {

            return new Result(Status.SUCCESS,null,user);
        }
    }

    @Override
    public Boolean signUp(User registerUser) {
        User user = userRepository.findByEmail(registerUser.getEmail());
        if (user != null)
            return false;
        else {
            userRepository.save(registerUser);
            return true;
        }

    }


}
