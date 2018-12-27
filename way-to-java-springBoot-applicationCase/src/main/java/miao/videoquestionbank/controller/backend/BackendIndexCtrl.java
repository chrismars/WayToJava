package miao.videoquestionbank.controller.backend;

import com.alibaba.fastjson.JSONObject;
import miao.videoquestionbank.Result.Result;
import miao.videoquestionbank.Util.EmptyCheckUtil;
import miao.videoquestionbank.Util.SessionAttribute;
import miao.videoquestionbank.Util.SessionUtil;
import miao.videoquestionbank.entity.User;
import miao.videoquestionbank.service.IndexUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/backend")
public class BackendIndexCtrl extends BackendBaseCtrl {

    @Autowired
    IndexUserService indexUserService;

    @RequestMapping("/index")
    public String index() {
        return "backend/index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Integer login(HttpServletRequest request, String email, String password) {
        if (EmptyCheckUtil.isEmpty(email) || EmptyCheckUtil.isEmpty(password))
            return PARAM_EMPTY;
        Result result = this.indexUserService.loginByEmailAndPassword(email, password);
        if (result.getStatus() == 200) {
            User user = (User) result.getData();
            if (user.getRole() == 1)
            {
                SessionUtil.setSessionAttribute(request,SessionAttribute.USER_SESSION,user);
                return SUCCESS;
            }

            else return PERSSION_DENIED;
        }
        else return result.getStatus();

    }
    @RequestMapping("/logout")
    @ResponseBody
    public Integer logout(HttpServletRequest request) {
        SessionUtil.removeSessionAttribute(request, SessionAttribute.USER_SESSION);
        return 200;
    }

}
