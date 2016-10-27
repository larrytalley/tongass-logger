package gov.noaa.alaskafisheries.tongasslogger;

import java.util.function.Supplier;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class LogStatementConsumer implements ExceptionListener, MessageListener {

	private static final Logger LOGGERINTERNAL = Logger.getLogger(LogStatementConsumer.class.getName());
	private String name = null;
	private Handler fh;

	public LogStatementConsumer(String name, Logger parent) {
		this.name = name;
	}

	@Override
	public synchronized void onException(JMSException e) {
		LOGGERINTERNAL.severe(e.getMessage());
	}

	@Override
	public void onMessage(Message msg) {
		try {
			if (msg instanceof TextMessage) {
				String src = msg.getStringProperty("src");
				String loggingLevel = msg.getStringProperty("loggingLevel");
				String className = msg.getStringProperty("className");
				Logger LOGGER = Logger.getLogger(className);

				//LOGGER.addHandler(fh);
				//LOGGER.setParent(LOGGERINTERNAL);
				if (Level.INFO.toString().equals(loggingLevel)) {
					LOGGER.info("SOURCE: " + src + " MESSAGE: " + ((TextMessage) msg).getText());
				} else if (Level.FINE.equals(loggingLevel)) {
					LOGGER.fine("SOURCE: " + src + " MESSAGE: " + ((TextMessage) msg).getText());
				} else if (Level.FINER.equals(loggingLevel)) {
					LOGGER.finer("SOURCE: " + src + " MESSAGE: " + ((TextMessage) msg).getText());
				} else if (Level.FINEST.equals(loggingLevel)) {
					LOGGER.finest("SOURCE: " + src + " MESSAGE: " + ((TextMessage) msg).getText());
				} else if (Level.SEVERE.equals(loggingLevel)) {
					LOGGER.severe("SOURCE: " + src + " MESSAGE: " + ((TextMessage) msg).getText());
				} else if (Level.WARNING.equals(loggingLevel)) {
					LOGGER.warning("SOURCE: " + src + " MESSAGE: " + ((TextMessage) msg).getText());
				} else {
					LOGGER.severe("Unexpected logging level: " + loggingLevel);
				}

			} else {
				LOGGERINTERNAL.severe("Unexpected message type: " + msg.getClass());
			}
		} catch (Exception e) {
			LOGGERINTERNAL.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
