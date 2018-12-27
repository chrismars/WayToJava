package miao.videoquestionbank.service;

import miao.videoquestionbank.entity.TestQuestions;

import java.util.List;

public interface TestQuestionService {
     List<TestQuestions> getTestQuestionsList(TestQuestions testQuestions);

     Integer addTestQuestions(TestQuestions testQuestions);

     void deleteTestQuestionsById(Integer id);

     /**
      * 通过ID获得题目对象
      * @param id
      * @return TestQuestions
      */
     TestQuestions getTestQuestionById(Integer id);
}
