package miao.videoquestionbank.service.impl;

import miao.videoquestionbank.Repository.TestQuestionsRepository;
import miao.videoquestionbank.entity.TestQuestions;
import miao.videoquestionbank.service.TestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestQuestionServiceImpl implements TestQuestionService{

    @Autowired
    TestQuestionsRepository testQuestionsRepository;

    /**
     * 保存和更新testQuestions
     * @param testQuestions
     * @return
     */
    @Override
    @Transactional
    public Integer addTestQuestions(TestQuestions testQuestions) {
        if(testQuestions.getIsDeleted()==null)
            testQuestions.setIsDeleted(0);
        TestQuestions newTestQuestions;
        if(testQuestions.getId() == null)
        {
            newTestQuestions=this.testQuestionsRepository.save(testQuestions);
        }
       else{
            newTestQuestions=this.testQuestionsRepository.saveAndFlush(testQuestions);
        }
        if(newTestQuestions!=null)
            return 1;
        else
            return 0;
    }

    @Override
    public List<TestQuestions> getTestQuestionsList(TestQuestions testQuestions) {
        return this.testQuestionsRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteTestQuestionsById(Integer id) {
        this.testQuestionsRepository.deleteById(id);
    }

    @Override
    public TestQuestions getTestQuestionById(Integer id) {
        return this.testQuestionsRepository.findById(id);
    }
}
