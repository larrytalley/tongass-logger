package gov.noaa.alaskafisheries.tongasslogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	private int loggingLevel;
	private String logFile;
	private String activeMqProtocol;
	private String activeMqHost;
	private String activeMqPort;
	private String activeMqTopic;
	private String activeMqClientId;
	private String activeMqConsumerName;
	private String activeMqUser;
	private String activeMqPassword;
	private int logFileSize;
	private int logFileCount;
	private boolean logFileAppend;

	public Configuration(Logger logger) {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			String filename = "app.properties";
			LOGGER.info("loading properties: " + filename);
			input = new FileInputStream(filename);
			// load a properties file
			prop.load(input);
			loggingLevel = Integer.parseInt(prop.getProperty("loggingLevel"));
			logFile = prop.getProperty("logFile");
			logFileSize = Integer.parseInt(prop.getProperty("logFileSize"));
			logFileCount = Integer.parseInt(prop.getProperty("logFileCount"));
			String a = prop.getProperty("logFileAppend");
			if("true".equals(a))
				logFileAppend=true;
			else
				logFileAppend=false;
			activeMqProtocol = prop.getProperty("activeMqProtocol");
			activeMqHost = prop.getProperty("activeMqHost");
			activeMqPort = prop.getProperty("activeMqPort");
			activeMqTopic = prop.getProperty("activeMqTopic");
			activeMqClientId = prop.getProperty("activeMqClientId");
			activeMqConsumerName = prop.getProperty("activeMqConsumerName");
			activeMqUser = prop.getProperty("activeMqUser");
			activeMqPassword = prop.getProperty("activeMqPassword");
			LOGGER.info("properties loaded: " + this.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getLoggingLevel() {
		return loggingLevel;
	}

	public void setLoggingLevel(int loggingLevel) {
		this.loggingLevel = loggingLevel;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public String getActiveMqProtocol() {
		return activeMqProtocol;
	}

	public void setActiveMqProtocol(String activeMqProtocol) {
		this.activeMqProtocol = activeMqProtocol;
	}

	public String getActiveMqHost() {
		return activeMqHost;
	}

	public void setActiveMqHost(String activeMqHost) {
		this.activeMqHost = activeMqHost;
	}

	public String getActiveMqPort() {
		return activeMqPort;
	}

	public void setActiveMqPort(String activeMqPort) {
		this.activeMqPort = activeMqPort;
	}

	public String getActiveMqTopic() {
		return activeMqTopic;
	}

	public void setActiveMqTopic(String activeMqTopic) {
		this.activeMqTopic = activeMqTopic;
	}

	public String getActiveMqClientId() {
		return activeMqClientId;
	}

	public void setActiveMqClientId(String activeMqClientId) {
		this.activeMqClientId = activeMqClientId;
	}

	public String getActiveMqConsumerName() {
		return activeMqConsumerName;
	}

	public void setActiveMqConsumerName(String activeMqConsumerName) {
		this.activeMqConsumerName = activeMqConsumerName;
	}

	public String getActiveMqUser() {
		return activeMqUser;
	}

	public void setActiveMqUser(String activeMqUser) {
		this.activeMqUser = activeMqUser;
	}

	public String getActiveMqPassword() {
		return activeMqPassword;
	}

	public void setActiveMqPassword(String activeMqPassword) {
		this.activeMqPassword = activeMqPassword;
	}

	public int getLogFileSize() {
		return logFileSize;
	}

	public void setLogFileSize(int logFileSize) {
		this.logFileSize = logFileSize;
	}

	public int getLogFileCount() {
		return logFileCount;
	}

	public void setLogFileCount(int logFileCount) {
		this.logFileCount = logFileCount;
	}

	public boolean isLogFileAppend() {
		return logFileAppend;
	}

	public void setLogFileAppend(boolean logFileAppend) {
		this.logFileAppend = logFileAppend;
	}

	@Override
	public String toString() {
		return "Configuration [loggingLevel=" + loggingLevel + ", logFile=" + logFile + ", activeMqProtocol=" + activeMqProtocol + ", activeMqHost=" + activeMqHost + ", activeMqPort=" + activeMqPort
				+ ", activeMqTopic=" + activeMqTopic + ", activeMqClientId=" + activeMqClientId + ", activeMqConsumerName=" + activeMqConsumerName + ", activeMqUser=" + activeMqUser
				+ ", activeMqPassword=****" + ", logFileSize=" + logFileSize + ", logFileCount=" + logFileCount + ", logFileAppend=" + logFileAppend + "]";
	}



}
