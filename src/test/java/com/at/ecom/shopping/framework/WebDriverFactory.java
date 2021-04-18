package com.at.ecom.shopping.framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Factory class for creating the {@link WebDriver} object as required
 * 
 * @author siva
 */
public class WebDriverFactory {
	private static Properties properties;
	static Logger log = Logger.getLogger(WebDriverFactory.class);
	private static String userdir = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\";

	private WebDriverFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the appropriate {@link WebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @return The corresponding {@link WebDriver} object
	 */

	@SuppressWarnings("deprecation")
	public static WebDriver getWebDriver(Browser browser) {
		WebDriver driver = null;
		properties = Settings.getInstance();
		try {
			switch (browser) {
			case CHROME:
				// Takes the system proxy settings automatically

				System.setProperty("webdriver.chrome.driver", userdir + "chromedriver.exe");
				String downloadFilepath = System.getProperty("user.dir") + "\\ApplicationData\\Downloads";
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("download.default_directory", downloadFilepath);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("disable-infobars");
				options.addArguments("chrome.switches", "--disable-extensions");
				options.addArguments("--test-type");
				options.addArguments("--disable-features=VizDisplayCompositor");
				options.addArguments("--window-position=0,0 --window-size=1000,1000");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, prefs);
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(cap);
				break;

			case FIREFOX:
				// Takes the system proxy settings automatically
				System.setProperty("webdriver.gecko.driver", userdir + "geckodriver.exe");
				driver = new FirefoxDriver();
				break;

			case INTERNET_EXPLORER:
				// Takes the system proxy settings automatically

				System.setProperty("webdriver.ie.driver", userdir + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;

			case SAFARI:
				// Takes the system proxy settings automatically

				driver = new SafariDriver();
				break;

			case CHROME_HEADLESS:
				log.info("HEADLESS BROWSER INSTANCE HAS BEEN INITIATED");
				System.setProperty("webdriver.chrome.driver", userdir + "chromedriver.exe");
				options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
				break;

			default:
				throw new Exception("Unhandled browser!");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		return driver;
	}

	private static DesiredCapabilities getProxyCapabilities() {
		properties = Settings.getInstance();
		String proxyUrl = properties.getProperty("ProxyHost") + ":" + properties.getProperty("ProxyPort");

		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.MANUAL);
		proxy.setHttpProxy(proxyUrl);
		proxy.setFtpProxy(proxyUrl);
		proxy.setSslProxy(proxyUrl);

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

		return desiredCapabilities;
	}

	/**
	 * Function to return the {@link RemoteWebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param browserVersion
	 *            The browser version to be used for the test execution
	 * @param platform
	 *            The {@link Platform} to be used for the test execution
	 * @param remoteUrl
	 *            The URL of the remote machine to be used for the test
	 *            execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser, String browserVersion, Platform platform,
			String remoteUrl) {

		properties = Settings.getInstance();
		boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));

		DesiredCapabilities desiredCapabilities = null;
		if (proxyRequired) {
			desiredCapabilities = getProxyCapabilities();
		} else {
			desiredCapabilities = new DesiredCapabilities();
		}

		desiredCapabilities.setBrowserName(browser.getValue());

		if (browserVersion != null) {
			desiredCapabilities.setVersion(browserVersion);
		}
		if (platform != null) {
			desiredCapabilities.setPlatform(platform);
		}

		desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
														// remote execution

		URL url = getUrl(remoteUrl);

		return new RemoteWebDriver(url, desiredCapabilities);
	}

	private static URL getUrl(String remoteUrl) {
		URL url = null;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		return url;
	}

	/**
	 * Function to return the {@link RemoteWebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param remoteUrl
	 *            The URL of the remote machine to be used for the test
	 *            execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser, String remoteUrl) {
		return getRemoteWebDriver(browser, null, null, remoteUrl);
	}

}