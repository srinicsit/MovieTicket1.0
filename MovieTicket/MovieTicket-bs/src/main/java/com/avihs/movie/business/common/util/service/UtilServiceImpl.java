package com.avihs.movie.business.common.util.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avihs.movie.business.common.util.dao.UtilDao;
import com.avihs.movie.business.model.Certificate;
import com.avihs.movie.business.model.Language;
import com.avihs.movie.business.model.SeatTypes;

@Service
public class UtilServiceImpl implements UtilService {

	@Autowired
	private UtilDao utilDao;

	@Override
	public List<SeatTypes> getAllSeatTypes() {
		return utilDao.get(SeatTypes.class);
	}

	@Override
	public List<Language> getAllLanguages() {
		return utilDao.get(Language.class);
	}

	@Override
	public List<Certificate> getAllCertificates() {
		return utilDao.get(Certificate.class);
	}

}
