package com.kkolcz.service;

import com.kkolcz.command.AnswerCommand;
import com.kkolcz.dao.AnswerDao;
import com.kkolcz.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("answerService")
public class AnswerServiceImpl extends AbstractService<Answer,AnswerDao,AnswerCommand> implements AnswerService {
    @Autowired
    public AnswerServiceImpl(AnswerDao dao) {
        super(dao);
    }
}
