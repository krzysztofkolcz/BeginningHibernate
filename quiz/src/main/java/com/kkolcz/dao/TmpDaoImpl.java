package com.kkolcz.dao;

import com.kkolcz.model.Tmp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TmpDaoImpl extends AbstractDao<Tmp> implements TmpDao {
}
