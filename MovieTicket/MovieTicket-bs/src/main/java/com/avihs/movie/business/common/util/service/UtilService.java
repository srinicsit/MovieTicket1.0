package com.avihs.movie.business.common.util.service;

import java.util.List;

import com.avihs.movie.business.model.Certificate;
import com.avihs.movie.business.model.Language;
import com.avihs.movie.business.model.SeatTypes;

public interface UtilService {

	public List<SeatTypes> getAllSeatTypes();
	
	public List<Language> getAllLanguages();
	
	public List<Certificate> getAllCertificates();
}
