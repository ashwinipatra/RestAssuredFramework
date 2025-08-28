package com.api.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.exceptions.FWException;

public class Log4jLogger {

	private final Logger logger;
	public Log4jLogger(Class<?> clazz) {
		logger = LogManager.getLogger(clazz);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void error(String message) {
		logger.error(message);
	}

	public void error(String message, Throwable throwable) {
		logger.error(message, throwable);
	}
	public void error(Throwable throwable) {
		logger.error(throwable);
	}

	public void fatal(String message) {
		logger.fatal(message);
	}

	public void fatal(String message, Throwable throwable) {
		logger.fatal(message, throwable);
	}
	public void fatal(Throwable throwable) {
		logger.fatal(throwable);
	}

	public void warn(String message) {
		logger.warn(message);
	}

	public void debug(String message) {
		logger.debug(message);
	}

	
	public void logAndThrow(String message,Throwable throwable) {
		logger.error(message, throwable);
		throw new FWException(message, throwable);
	}
}
