package com.skalvasociety.skalva.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.dao.IVideoDao;

@Service("VideoService")
@Transactional
public class VideoService extends AbstractService<Serializable, Video> implements IVideoService {	
	@Autowired
	IVideoDao videoDao;
}
