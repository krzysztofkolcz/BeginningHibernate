package com.kkolcz.dao;

import com.kkolcz.model.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {
}
