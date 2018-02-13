package com.kkolcz.service;

import com.kkolcz.command.QuestionCommand;
import com.kkolcz.dao.QuestionDao;
import com.kkolcz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("questionService")
public class QuestionServiceImpl extends AbstractService<Question,QuestionDao,QuestionCommand> implements QuestionService {

    @Autowired
    public QuestionServiceImpl(QuestionDao dao) {
        super(dao);
    }
}