package miao.videoquestionbank.controller.fronted;

import miao.videoquestionbank.Util.SessionAttribute;
import miao.videoquestionbank.Util.SessionUtil;
import miao.videoquestionbank.entity.Grade;
import miao.videoquestionbank.entity.Subject;
import miao.videoquestionbank.entity.TestQuestions;
import miao.videoquestionbank.entity.User;
import miao.videoquestionbank.service.GradeService;
import miao.videoquestionbank.service.SubjectService;
import miao.videoquestionbank.service.TestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/front/question")
@Controller
public class QuestionBankCtrl {
    @Autowired
    SubjectService subjectService;

    @Autowired
    GradeService gradeService;

    @Autowired
    TestQuestionService testQuestionService;

    @RequestMapping("/lookQuestion")
    public String lookQuestion(HttpServletRequest request,Model model){
        List<Subject> subjectList=this.subjectService.getSubjectList();
        List<Grade> gradeList=this.gradeService.getGradeList();
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("gradeList",gradeList);
        model.addAttribute("user",SessionUtil.getSession(request,SessionAttribute.USER_SESSION,User.class));
        return "fronted/lookQuestionBank";
    }

    @RequestMapping("/detail")
    public String getQuestionDetail(HttpServletRequest request,Model model){
        return "fronted/questionDetail";
    }

    @RequestMapping("/searchList")
    public String getSearchList(HttpServletRequest request,Model model,TestQuestions testQuestions){
        List<TestQuestions> testQuestionsList=this.testQuestionService.getTestQuestionsList(testQuestions);
        model.addAttribute("testQuestionsList",testQuestionsList);
        return "fronted/searchList";
    }

}
