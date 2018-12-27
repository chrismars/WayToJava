package miao.videoquestionbank.service.impl;

import miao.videoquestionbank.Repository.SubjectRepository;
import miao.videoquestionbank.entity.Subject;
import miao.videoquestionbank.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Subject> getSubjectList() {
        return subjectRepository.findAll();
    }
}
