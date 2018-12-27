package miao.videoquestionbank.controller.backend;

import miao.videoquestionbank.Util.FileUtil;
import miao.videoquestionbank.Util.SessionAttribute;
import miao.videoquestionbank.Util.SessionUtil;
import miao.videoquestionbank.entity.Grade;
import miao.videoquestionbank.entity.Subject;
import miao.videoquestionbank.entity.TestQuestions;
import miao.videoquestionbank.entity.User;
import miao.videoquestionbank.service.GradeService;
import miao.videoquestionbank.service.OSSService;
import miao.videoquestionbank.service.SubjectService;
import miao.videoquestionbank.service.TestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/backend/manage")
public class BackendManageCtrl extends BackendBaseCtrl{
    @Autowired
    OSSService ossService;

    @Autowired
    TestQuestionService testQuestionService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    GradeService gradeService;

    @Value("${OSSClient.endpoint}")
    String endpoint;

    @Value("${OSSClient.bucketName}")
    String bucketName;

    /**
     *查看题库接口
     * @param model
     * @param testQuestions
     * @return
     */
    @RequestMapping("/lookQuestionBank")
    public String lookQuesstionBank(Model model,TestQuestions testQuestions){
        List<TestQuestions> testQuestionsList=this.testQuestionService.getTestQuestionsList(testQuestions);
        model.addAttribute("testQuestionsList",testQuestionsList);
        return "backend/lookQuestionBank";
    }

    /**
     * 创建题库 接口
     * @param model
     * @param testQuestions
     * @return
     */
    @RequestMapping("/editQuestionBank")
    public String editQuesstionBank(Model model,TestQuestions testQuestions,HttpServletRequest request){
        List<TestQuestions> testQuestionsList=this.testQuestionService.getTestQuestionsList(testQuestions);
        List<Subject> subjectList=this.subjectService.getSubjectList();
        List<Grade> gradeList=this.gradeService.getGradeList();
        model.addAttribute("testQuestionsList",testQuestionsList);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("gradeList",gradeList);
        return "backend/editQuestionBank";
    }

    /**
     * 更新题库
     * @param request
     * @param testQuestions
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/updateQuestionBank")
    @ResponseBody
    public Integer updataQuesstionBank(HttpServletRequest request,TestQuestions testQuestions) throws UnsupportedEncodingException {
        testQuestions.setVideoUrl(URLDecoder.decode(testQuestions.getVideoUrl(),"utf-8"));
        if(this.testQuestionService.addTestQuestions(testQuestions)==1)
            return 200;
        else return ERROR;
    }


    /**
     * 根据id获得题目
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/updateQuestionBankPage")
    public String getQuesstionBank(Model model, Integer id){
        if(id != null){
            TestQuestions testQuestions = this.testQuestionService.getTestQuestionById(id);
            List<Subject> subjectList=this.subjectService.getSubjectList();
            List<Grade> gradeList=this.gradeService.getGradeList();
            model.addAttribute("subjectList",subjectList);
            model.addAttribute("gradeList",gradeList);
            model.addAttribute("testQuestions",testQuestions);
            return "backend/updateQuestionBank";
        }else{
            return "backend/lookQuestionBank";
        }
    }

    @RequestMapping("/deleteQuestionBank")
    public String deleteQuestionBank(HttpServletRequest request,Integer id,Model model){
        if(id != null){
            this.testQuestionService.deleteTestQuestionsById(id);
        }
        return getMenu(request,model);
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile file)throws IOException{
        if(file==null)
            return null;
        UUID uuid=UUID.randomUUID();
        String key=uuid.toString().replace("-","")+"."+ FileUtil.getSuffix(file.getOriginalFilename());
        this.ossService.saveDocument(file.getInputStream(),key);
        String url= "https://"+bucketName+"."+endpoint+"/"+key;
        return URLEncoder.encode(url,"utf-8");
    }

    @RequestMapping("/uploadQuestions")
    @ResponseBody
    public Integer uploadQuestion(HttpServletRequest request,TestQuestions testQuestions) throws UnsupportedEncodingException {
        testQuestions.setVideoUrl(URLDecoder.decode(testQuestions.getVideoUrl(),"utf-8"));
        if(this.testQuestionService.addTestQuestions(testQuestions)==1)
            return 200;
        else return ERROR;
    }

    @RequestMapping("/menu")
    public String getMenu(HttpServletRequest request,Model model){
        model.addAttribute("user", SessionUtil.getSession(request,SessionAttribute.USER_SESSION,User.class));
        return "backend/menu";
    }

}
