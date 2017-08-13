package com.skalvasociety.skalva.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Sexe;

@Service("SexeService")
@Transactional
public class SexeService extends AbstractService<Serializable, Sexe> implements ISexeService{

}
