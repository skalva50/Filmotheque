package com.skalvasociety.skalva.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Sexe;


@Repository("sexeDao")
@Transactional
public class SexeDao extends AbstractDao<Integer, Sexe> implements ISexeDao {

}
