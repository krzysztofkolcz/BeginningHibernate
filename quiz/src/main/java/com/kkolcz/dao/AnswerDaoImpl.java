package com.kkolcz.dao;

import com.kkolcz.model.Answer;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {
}
