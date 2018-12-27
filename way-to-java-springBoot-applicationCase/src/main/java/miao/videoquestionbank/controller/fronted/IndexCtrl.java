package miao.videoquestionbank.controller.fronted;

import miao.videoquestionbank.Repository.UserRepository;
import miao.videoquestionbank.Util.SessionAttribute;
import miao.videoquestionbank.Util.SessionUtil;
import miao.videoquestionbank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/front")
public class IndexCtrl extends BaseCtrl {
    @Autowired
    private UserRepository userRepository;


    //index页面
    @RequestMapping("/index")
    public String index() {
        return "fronted/index";
    }

    //注册方法
    @RequestMapping("/register")
    @ResponseBody
    public Integer register(HttpServletRequest request){
        String nickname=request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password.equals(password2)){
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setRole(0);
            userRepository.save(user);
            SessionUtil.setSessionAttribute(request,SessionAttribute.USER_SESSION,user);
            return SUCCESS;
        }else {
            return ERROR;
        }
    }

    //登录方法
    @RequestMapping("/login")
    @ResponseBody
    public Integer login(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userRepository.findByEmailAndPassword(email,password);
        SessionUtil.setSessionAttribute(request,SessionAttribute.USER_SESSION,user);
        if (user !=null){
            return SUCCESS;
        }else {
            return ERROR;
        }

    }

    @RequestMapping("/logout")
    @ResponseBody
    public Integer logout(HttpServletRequest request) {
        SessionUtil.removeSessionAttribute(request, SessionAttribute.USER_SESSION);
        return 200;
    }

}
