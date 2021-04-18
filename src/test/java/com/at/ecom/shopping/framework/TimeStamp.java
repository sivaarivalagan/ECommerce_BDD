package com.at.ecom.shopping.framework;

import java.io.File;
import java.util.Properties;

/**
 * Singleton class which manages the creation of timestamped result folders for
 * every test batch execution
 * 
 * @author siva
 */
public class TimeStamp {
	public static volatile String reportPathWithTimeStamp;

	private TimeStamp() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the timestamped result folder path
	 * 
	 * @return The timestamped result folder path
	 */
	public static String getInstance() {
		if (reportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (reportPathWithTimeStamp == null) { // Double-checked locking
					Properties properties = Settings.getInstance();
					String timeStamp = "Run_" + Util.getCurrentFormattedTime(properties.getProperty("DateFormatString"))
							.replace(" ", "_").replace(":", "-");

					reportPathWithTimeStamp = Util.getResultsPath() + Util.getFileSeparator() + timeStamp;

					new File(reportPathWithTimeStamp).mkdirs();
				}
			}
		}

		return reportPathWithTimeStamp;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}