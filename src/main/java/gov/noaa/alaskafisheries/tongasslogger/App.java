package gov.noaa.alaskafisheries.tongasslogger;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());

	public static void main(String[] args) throws Exception {
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger("");
		Handler ch = new ConsoleHandler();
		ch.setFormatter(new LogFormatter());
		globalLogger.addHandler(ch);
		Configuration config = new Configuration(LOGGER);

		Handler fh = new FileHandler(config.getLogFile() + "%g", config.getLogFileSize(), config.getLogFileCount(), config.isLogFileAppend());
		fh.setFormatter(new LogFormatter());
		globalLogger.addHandler(fh);
		globalLogger.setLevel(getLoggingLevel(config.getLoggingLevel()));

		LOGGER.info("starting tongass logger");
		Connection connection = null;

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(config.getActiveMqUser(), config.getActiveMqPassword(), config.getActiveMqProtocol() + "://" + config.getActiveMqHost()
				+ ":" + config.getActiveMqPort());
		connection = connectionFactory.createConnection();
		connection.setClientID(config.getActiveMqClientId());

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic log = session.createTopic(config.getActiveMqTopic());
		MessageConsumer consumer = session.createConsumer(log);

		consumer.setMessageListener(new LogStatementConsumer(config.getActiveMqConsumerName(), LOGGER));
		connection.start();

	}

	public static Level getLoggingLevel(int level) {
		if (0 == level) {
			return Level.INFO;
		} else if (1 == level) {
			return Level.CONFIG;
		} else if (2 == level) {
			return Level.FINE;
		} else if (3 == level) {
			return Level.FINER;
		} else {
			return Level.FINEST;
		}
	}

}
