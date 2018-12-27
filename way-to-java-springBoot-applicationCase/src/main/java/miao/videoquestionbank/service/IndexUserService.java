package miao.videoquestionbank.service;

import miao.videoquestionbank.Result.Result;
import miao.videoquestionbank.entity.User;

public interface IndexUserService {
    Boolean signUp(User user);

    Result loginByEmailAndPassword(String email, String password);
}
