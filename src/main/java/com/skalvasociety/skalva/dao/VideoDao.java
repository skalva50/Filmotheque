package com.skalvasociety.skalva.dao;

import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Video;

@Repository("VideoDao")
public class VideoDao extends AbstractDao<Integer, Video> implements IVideoDao {
}
