package com.avihs.movie.business.email.service;

import java.util.Map;

public interface EmailService {

	public void sendMail(final Map model, final String velocityTemplateFile);
}
