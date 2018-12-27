package miao.videoquestionbank.Repository;

import miao.videoquestionbank.entity.TestQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionsRepository extends JpaRepository<TestQuestions,Long> {
    List<TestQuestions> findAll();

    TestQuestions save(TestQuestions testQuestions);

    TestQuestions saveAndFlush(TestQuestions testQuestions);

    void deleteById(Integer id);

    TestQuestions findById(Integer id);
}
