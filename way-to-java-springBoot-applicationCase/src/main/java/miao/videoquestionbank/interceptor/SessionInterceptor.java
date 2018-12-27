package miao.videoquestionbank.interceptor;

import miao.videoquestionbank.Util.SessionAttribute;
import miao.videoquestionbank.Util.SessionUtil;
import miao.videoquestionbank.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user=SessionUtil.getSession(request,SessionAttribute.USER_SESSION,User.class);
        if(user!=null)
            return true;
        else
            return false;
//        return true;
    }
}
