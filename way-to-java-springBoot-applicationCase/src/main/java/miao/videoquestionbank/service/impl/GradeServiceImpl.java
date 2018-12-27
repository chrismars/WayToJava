package miao.videoquestionbank.service.impl;

import miao.videoquestionbank.Repository.GradeRepository;
import miao.videoquestionbank.entity.Grade;
import miao.videoquestionbank.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    GradeRepository gradeRepository;
    @Override
    public List<Grade> getGradeList() {
        return gradeRepository.findAll();
    }
}
