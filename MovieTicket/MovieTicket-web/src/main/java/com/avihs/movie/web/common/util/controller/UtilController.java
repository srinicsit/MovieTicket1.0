package com.avihs.movie.web.common.util.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.common.util.service.UtilService;
import com.avihs.movie.business.model.Certificate;
import com.avihs.movie.business.model.Language;
import com.avihs.movie.business.model.SeatTypes;

@Controller
@RequestMapping("/utils")
public class UtilController {

	@Autowired
	private UtilService utilService;

	@RequestMapping(value = "/allSeatTypes", method = RequestMethod.GET)
	public @ResponseBody
	List<SeatTypes> getAllSeatTypes() {

		return utilService.getAllSeatTypes();
	}

	@RequestMapping(value = "/allLanguages", method = RequestMethod.GET)
	public @ResponseBody
	List<Language> getAllLanguages() {

		return utilService.getAllLanguages();
	}

	@RequestMapping(value = "/allCertificates", method = RequestMethod.GET)
	public @ResponseBody
	List<Certificate> getAllCertificates() {
		return utilService.getAllCertificates();
	}

	@RequestMapping(value = "/rowNumberChars", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> getRowNumberChar() {

		Map<String, String> rowNumberCharMap = new HashMap<String, String>();
		rowNumberCharMap.put("1", "A");
		rowNumberCharMap.put("2", "B");
		rowNumberCharMap.put("3", "C");
		rowNumberCharMap.put("4", "D");
		rowNumberCharMap.put("5", "E");
		rowNumberCharMap.put("6", "F");
		rowNumberCharMap.put("7", "G");

		rowNumberCharMap.put("8", "H");
		rowNumberCharMap.put("9", "I");
		rowNumberCharMap.put("10", "J");
		rowNumberCharMap.put("11", "K");
		rowNumberCharMap.put("12", "L");
		rowNumberCharMap.put("13", "M");
		rowNumberCharMap.put("14", "N");

		rowNumberCharMap.put("15", "O");
		rowNumberCharMap.put("16", "P");
		rowNumberCharMap.put("17", "Q");
		rowNumberCharMap.put("18", "R");
		rowNumberCharMap.put("19", "S");
		rowNumberCharMap.put("20", "T");
		rowNumberCharMap.put("21", "U");

		rowNumberCharMap.put("22", "V");
		rowNumberCharMap.put("23", "W");
		rowNumberCharMap.put("24", "X");
		rowNumberCharMap.put("25", "Y");
		rowNumberCharMap.put("26", "Z");

		return rowNumberCharMap;
	}
}
