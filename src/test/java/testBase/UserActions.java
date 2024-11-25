package testBase;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.CaptureElement;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

import coreUtilities.utils.CustomException;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

/***
 * @author Sudhansu Sekhar Panda
 * This class is responsible to perform all generic or commonly used operations
 *
 */
public class UserActions 
{
	public WebDriver driver;

	public UserActions(WebDriver driver) 
	{
		this.driver=driver; 
	}

	/**
	 * This method is useful to generate the timestamp in ddMMYYYYHHmmss format
	 *
	 * @return Timestamp in String format
	 */
	public String getTimeStamp() {
		return new SimpleDateFormat("ddMMYYYYHHmmss").format(new Date());
	}

	/**
	 * This method is used to convert our by type element to webelement
	 * @param by
	 * @return WebElement
	 */
	public WebElement getWebElement(By by)
	{
		return driver.findElement(by);
	}

	/**
	 * This method is used to convert our by type element to List of webelements
	 * @param by
	 * @return List<WebElement>
	 */
	public List<WebElement> getWebElements(By by)
	{
		return driver.findElements(by);
	}

	/**
	 * This method is responsible to find the element from the DOM. 
	 * If found then it'll return true else it'll return false.
	 * In this case it'll try to find the element in the entire DOM, so it might consume sometime
	 * 
	 * @param by
	 * @return boolean
	 */
	public boolean isDisplayed(By by)
	{
		boolean flag = false;
		try
		{
			waitTillElementVisible(by, 60);
			if(getWebElement(by).isDisplayed())
				flag = true;
		}catch(Exception e)
		{
			flag = false;
		}

		return flag;
	}


	/**
	 * This method is responsible to find the element from the DOM. 
	 * If found then it'll return true else it'll return false.
	 * In this case it'll try to find the element in the entire DOM, so it might consume sometime
	 * 
	 * @param by
	 * @return boolean
	 */
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	
	/**
	 * This method is responsible to find the element from the DOM. 
	 * If found then it'll return true else it'll return false.
	 * In this case it'll try to find the element in the entire DOM, so it might consume sometime
	 * 
	 * @param by
	 * @return boolean
	 */
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * This method is responsible to find the element from the DOM. 
	 * If found then it'll return true else it'll return false.
	 * In this case it'll try to find the element in the entire DOM, so it might consume sometime
	 * 
	 * @param by
	 * @return boolean
	 */
	public boolean isEmpty(By by) {
		WebElement element = driver.findElement(by);
		return element.getText().isEmpty();
	}

	/**
	 * This method is responsible to find the element from the DOM. 
	 * If found then it'll return true else it'll return false.
	 * In this case it'll try to find the element in the entire DOM, so it might consume sometime
	 * 
	 * @param  element
	 * @return boolean
	 */
	public boolean isDisplayed(WebElement element)
	{
		boolean flag = false;
		try
		{
			waitTillElementVisible(element, 60);
			if(element.isDisplayed())
				flag = true;
		}catch(Exception e)
		{
			flag = false;
		}

		return flag;
	}

	/**
	 * This method is useful to check for a specific web element is displayed on UI or not with in the given time frame
	 * 
	 * @param by
	 * @param seconds
	 * @return boolean
	 */
	public boolean isDisplayed(By by, int seconds)
	{
		boolean isDisplayed = false;
		try
		{
			waitTillElementVisible(by, seconds);
			isDisplayed = true;
		}
		catch(Exception e)
		{
			isDisplayed = false;
		}
		return isDisplayed;
	}

	/**
	 * This method is useful to check for a specific web element is displayed on UI or not with in the given time frame
	 * 
	 * @param element
	 * @param seconds
	 * @return boolean
	 */
	public boolean isDisplayed(WebElement element, int seconds)
	{
		boolean isDisplayed = false;
		try
		{
			waitTillElementVisible(element, seconds);
			isDisplayed = true;
		}
		catch(Exception e)
		{
			isDisplayed = false;
		}
		return isDisplayed;
	}

	/**
	 * This method is responsible to find the element is enabled for the further actions. 
	 * If found enabled, then it'll return true else it'll return false.
	 * 
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean isEnabled(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		boolean flag = false;
		if(isDisplayed(by))
		{
			try
			{
				if(getWebElement(by).isEnabled())
					flag = true;
			}catch(Exception e)
			{
				flag = false;
			}
		}
		else
			throw new Exception(elementName+" is not displayed on "+pageName);

		return flag;
	}

	/**
	 * This method is useful to verify whether the web element is enabled or not.
	 * @param by - {@link By} type
	 * @return boolean - true if web element is enabled else false
	 */
	public boolean isEnabled(By by)
	{
		return getWebElement(by).isEnabled();
	}

	/**
	 * This method is responsible to find the element is enabled for the further actions. 
	 * If found enabled, then it'll return true else it'll return false.
	 * 
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean isEnabled(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		boolean flag = false;
		if(isDisplayed(element))
		{
			try
			{
				if(element.isEnabled())
					flag = true;
			}catch(Exception e)
			{
				flag = false;
			}
		}
		else
			throw new Exception(elementName+" is not displayed on "+pageName);

		return flag;
	}

	/**
	 * This method is useful to verify whether the web element is enabled or not.
	 * @param element - {@link WebElement} type
	 * @return boolean - true if web element is enabled else false
	 */
	public boolean isEnabled(WebElement element)
	{
		return element.isEnabled();
	}

	/**
	 * This method is responsible to find the element is enabled for the further actions. 
	 * If found enabled, then it'll return true else it'll return false.
	 * 
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean isSelected(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		boolean flag = false;
		if(isDisplayed(by))
		{
			try
			{
				if(getWebElement(by).isSelected())
					flag = true;
			}catch(Exception e)
			{
				flag = false;
			}
		}
		else
			throw new Exception(elementName+" is not displayed on "+pageName);
		return flag;
	}

	/**
	 * This method is useful to verify whether the element is selected or not
	 * @param by - {@link By} type
	 * @return boolean - it will return true if the element is selected else will return false
	 */
	public boolean isSelected(By by) {
		return getWebElement(by).isSelected();
	}

	/**
	 * This method is responsible to find the element is enabled for the further actions. 
	 * If found enabled, then it'll return true else it'll return false.
	 * 
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean isSelected(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		boolean flag = false;
		if(isDisplayed(element))
		{
			try
			{
				if(element.isSelected())
					flag = true;
			}catch(Exception e)
			{
				flag = false;
			}
		}
		else
			throw new Exception(elementName+" is not displayed on "+pageName);
		return flag;
	}

	/**
	 * This method is useful to verify whether the element is selected or not
	 * @param element - {@link WebElement} type
	 * @return boolean - it will return true if the element is selected else will return false
	 */
	public boolean isSelected(WebElement element) {
		return element.isSelected();
	}

	/**
	 * This method is useful to get the text of the element
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception 
	 */
	public String getText(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		String text="";
		try 
		{
			if(isDisplayed(by))
				text = getWebElement(by).getText();
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return text;
	}

	/**
	 * This method is useful to retrieve the text from the web element
	 * @param by - {@link By} type
	 * @return String
	 */
	public String getText(By by)
	{
		return getWebElement(by).getText();
	}

	/**
	 * This method is useful to get the text of the element
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception 
	 */
	public String getText(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		String text="";
		try 
		{
			if(isDisplayed(element))
			{
				text = element.getText();
			}
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return text;
	}

	/**
	 * This method is useful to retrieve the text from the web element
	 * @param element - {@link WebElement} type
	 * @return String
	 */
	public String getText(WebElement element)
	{
		return element.getText();
	}

	/**
	 * This method is useful to get the Tag Name of the element
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception
	 */
	public String getTagName(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		String tagValue = "";
		try 
		{
			if(isDisplayed(by))
				tagValue = getWebElement(by).getTagName();
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return tagValue;
	}

	/**
	 * This method is useful to retrieve the tag name for the respective web element from DOM
	 * @param by - {@link By} type
	 * @return String
	 */
	public String getTagName(By by)
	{
		return getWebElement(by).getTagName();
	}

	/**
	 * This method is useful to get the Tag Name of the element
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception
	 */
	public String getTagName(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		String tagValue = "";
		try 
		{
			if(isDisplayed(element))
				tagValue = element.getTagName();
			else
				throw new Exception(elementName+" is not displayed on "+pageName);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return tagValue;
	}

	/**
	 * This method is useful to retrieve the tag name for the respective web element from DOM
	 * @param element - {@link WebElement}  type
	 * @return String
	 */
	public String getTagName(WebElement element)
	{
		return element.getTagName();
	}

	/**
	 * This method is useful to get the desired attribute value based on the given attribute name of the element
	 * @param by
	 * @param attributeName
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception
	 */
	public String getAttribute(By by, String attributeName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			return getWebElement(by).getAttribute(attributeName);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/**
	 * This method is useful to retrieve the desired attribute of the given web element
	 * @param by - {@link By} type
	 * @param attributeName - The attribute name in String format
	 * @return String
	 */
	public String getAttribute(By by, String attributeName)
	{
		return getWebElement(by).getAttribute(attributeName);
	}

	/**
	 * This method is useful to get the desired attribute value based on the given attribute name of the element
	 * @param element
	 * @param attributeName
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception
	 */
	public String getAttribute(WebElement element, String attributeName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			return element.getAttribute(attributeName);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/**
	 * This method is useful to retrieve the desired attribute of the given web element
	 * @param element - {@link WebElement} type
	 * @param attributeName - The attribute name in String format
	 * @return String
	 */
	public String getAttribute(WebElement element, String attributeName)
	{
		return element.getAttribute(attributeName);
	}

	/**
	 * This method is useful to collect the attribute values for a list of webelements.
	 * @param by
	 * @param attributeName
	 * @param elementName
	 * @param pageName
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getAttributes(By by, String attributeName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			List<WebElement> webElements = getWebElements(by);		
			List<String> attributes = new ArrayList<String>();
			webElements.parallelStream().forEach(e->attributes.add(e.getAttribute(attributeName)));
			return attributes;
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/**
	 * This method is useful to collect the attribute value for a list of webelements.
	 * @param List<WebElement> elements
	 * @param attributeName
	 * @param elementName
	 * @param pageName
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getAttributes(List<WebElement> elements, String attributeName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			List<String> attributes = new ArrayList<String>();
			elements.parallelStream().forEach(e->attributes.add(e.getAttribute(attributeName)));
			return attributes;
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}

	}

	/**
	 * This method is useful to get the desired css property value based on the given css property name of the element
	 * 
	 * @param by
	 * @param cssProperty
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception
	 */
	public String getCssValue(By by, String cssProperty, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			return getWebElement(by).getCssValue(cssProperty);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/**
	 * This method is useful to retrieve the CSS property like color, font size, etc for the given web element
	 * @param by - {@link By} type
	 * @param cssProperty - {@link String} type
	 * @return String
	 */
	public String getCssValue(By by, String cssProperty)
	{
		return getWebElement(by).getCssValue(cssProperty);
	}

	/**
	 * This method is useful to get the desired css property value based on the given css property name of the element
	 * 
	 * @param element
	 * @param cssProperty
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception
	 */
	public String getCssValue(WebElement element, String cssProperty, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			return element.getCssValue(cssProperty);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/**
	 * This method is useful to retrieve the CSS property like color, font size, etc for the given web element
	 * @param element - {@link WebElement} type
	 * @param cssProperty - {@link String} type
	 * @return String
	 */
	public String getCssValue(WebElement element, String cssProperty)
	{
		return element.getCssValue(cssProperty);
	}

	/***
	 * This method is used to fetch the current browsed url
	 * @return String
	 */
	public String getCurrentUrl()
	{
		return driver.getCurrentUrl();
	}

	/***
	 * This method is used to fetch the title of the current browser
	 * @return String
	 */
	public String getTitle()
	{
		return driver.getTitle();
	}

	/***
	 * This method is used to clear the data present for a text box
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions clear(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			getWebElement(by).click();
			getWebElement(by).clear();
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed/enabled on "+pageName);
		}
	}

	/**
	 * This method is useful to clear the text box before entering any value to the desired text box	
	 * @param by - {@link By} type
	 * @return {@link UserActions}
	 */
	public UserActions clear(By by)
	{
		getWebElement(by).clear();
		return new UserActions(driver);
	}

	/***
	 * This method is used to clear the data present for a text box
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions clear(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			element.click();
			element.clear();
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed/enabled on "+pageName);
		}
	}

	/**
	 * This method is useful to clear the text box before entering any value to the desired text box	
	 * @param element - {@link WebElement} type
	 * @return {@link UserActions}
	 */
	public UserActions clear(WebElement element)
	{
		element.clear();
		return new UserActions(driver);
	}

	/**
	 * This method is useful to perform click operation on a webelement. Here the elementName and the pageName is used for the logging purpose
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions click(WebElement element, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			if(isEnabled(element, elementName, pageName))
				element.click();
			else
				throw new Exception(elementName + " is not enabled on " + pageName);
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName + " is not displayed on " + pageName);
		}

	}

	/**
	 * This method is useful to perform click operation on a element. Here the elementName and the pageName is used for the logging purpose
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions click(By by, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			waitTillElementLocated(by, 30, elementName, pageName)
			.waitTillElementLocated(by, 30, elementName, pageName);
			WebElement element = getWebElement(by);
			if(isEnabled(element, elementName, pageName))
				element.click();
			else 
				throw new Exception(elementName + " is not enabled on " + pageName);
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName + " is not displayed on " + pageName);
		}
	}

	/**
	 * This method is useful to click on the given web element	
	 * @param by - {@link By} type
	 * @return {@link UserActions}
	 */
	public UserActions click(By by)
	{
		getWebElement(by).click();
		return new UserActions(driver);
	}

	/**
	 * This method is useful to click on the given web element	
	 * @param element - {@link WebElement} type
	 * @return {@link UserActions}
	 */
	public UserActions click(WebElement element)
	{
		element.click();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to click on the desired element using the {@link Actions} class
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions actionsClick(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			WebElement element = getWebElement(by);
			if(element.isEnabled())
				new Actions(driver).click(element)
				.perform();
			else
				throw new Exception(elementName + " is not enabled on " + pageName);
			return new UserActions(driver);
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/***
	 * This method is useful to click on the desired element using the {@link Actions} class
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions actionsClick(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			if(element.isEnabled())
				new Actions(driver).click(element)
				.perform();
			else
				throw new Exception(elementName + " is not enabled on " + pageName);
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/**
	 * This method is useful to click on the given web element using {@link Actions} class
	 * @param by - {@link By} type
	 * @return {@link UserActions}
	 */
	public UserActions actionsClick(By by)
	{
		new Actions(driver)
		.click(getWebElement(by))
		.build()
		.perform();
		return new UserActions(driver);
	}

	/**
	 * This method is useful to click on the given web element using {@link Actions} class
	 * @param element - {@link WebElement} type
	 * @return {@link UserActions}
	 */
	public UserActions actionsClick(WebElement element)
	{
		new Actions(driver)
		.click(element)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to click on the desired WebElement using {@link Actions} class. 
	 * In this first the controller will hover on the desired element then click on that element.
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions mouseHoverClick(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			if(getWebElement(by).isEnabled())
				new Actions(driver).moveToElement(getWebElement(by))
				.click()
				.build()
				.perform();
			else
				throw new Exception(elementName + " is not enabled on " + pageName);
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/***
	 * This method is useful to click on the desired WebElement using {@link Actions} class. 
	 * In this first the controller will hover on the desired element then click on that element.
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions mouseHoverClick(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			if(element.isEnabled())
				new Actions(driver).moveToElement(element)
				.click()
				.build()
				.perform();
			else
				throw new Exception(elementName+" is not enabled on "+pageName);
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}

	}

	/**
	 * This method is useful to click on the given web element using {@link Actions} class. In this the controller will move to the desired element 
	 * then will click on the same.
	 * @param by - {@link By} type
	 * @return {@link UserActions}
	 */
	public UserActions mouseHoverClick(By by)
	{
		new Actions(driver)
		.moveToElement(getWebElement(by))
		.click()
		.build()
		.perform();
		return new UserActions(driver);
	}

	/**
	 * This method is useful to click on the given web element using {@link Actions} class. In this the controller will move to the desired element 
	 * then will click on the same.
	 * @param element - {@link WebElement} type
	 * @return {@link UserActions}
	 */
	public UserActions mouseHoverClick(WebElement element)
	{
		new Actions(driver)
		.moveToElement(element)
		.click()
		.build()
		.perform();
		return new UserActions(driver);
	}

	/**
	 * This method is useful to click on the given web element using {@link Actions} class. In this the controller will move to the desired element 
	 * then will click on the same.
	 * @param by - {@link By} type
	 * @return {@link UserActions}
	 */
	public UserActions mouseHoverClickOnElement(By by)
	{
		WebElement element = getWebElement(by);
		new Actions(driver)
		.moveToElement(element)
		.click(element)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/**
	 * This method is useful to click on the given web element using {@link Actions} class. In this the controller will move to the desired element 
	 * then will click on the same.
	 * @param element - {@link WebElement} type
	 * @return {@link UserActions}
	 */
	public UserActions mouseHoverClickOnElement(WebElement element)
	{
		new Actions(driver)
		.moveToElement(element)
		.click(element)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/**
	 * This method is useful when user has to click on the target element by hovering an another element. In this case user has to mouse hover on
	 * element1 which is by1 and clicking on element2 which is by2 as per this method.
	 * @param by1
	 * @param by2
	 * @return {@link UserActions}
	 */
	public UserActions mouseHoverClick(By by1, By by2) {
		new Actions(driver)
		.moveToElement(getWebElement(by1))
		.click(getWebElement(by2))
		.build()
		.perform();
		return new UserActions(driver);
	}

	/**
	 * This method is useful when user has to click on the target element by hovering an another element. In this case user has to mouse hover on
	 * element1 which is by1 and clicking on element2 which is by2 as per this method.
	 * @param by1
	 * @param by2
	 * @return {@link UserActions}
	 */
	public UserActions mouseHoverClick(WebElement element1, WebElement element2) {
		new Actions(driver)
		.moveToElement(element1)
		.click(element2)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to click on the desired WebElement using JavascriptExecutor
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions jsClick(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/***
	 * This method is useful to click on the desired WebElement using JavascriptExecutor
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions jsClick(By by, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", getWebElement(by));
			return new UserActions(driver);
		}
		catch(Exception e) 
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
	}

	/**
	 * This method is useful to click on the given web element using {@link JavascriptExecutor}.
	 * @param by - {@link By} type
	 * @return {@link UserActions}
	 */
	public UserActions jsClick(By by)
	{
		((JavascriptExecutor) driver)
		.executeScript("arguments[0].click()", getWebElement(by));
		return new UserActions(driver);
	}

	/**
	 * This method is useful to click on the given web element using {@link JavascriptExecutor}.
	 * @param element - {@link WebElement} type
	 * @return {@link UserActions}
	 */
	public UserActions jsClick(WebElement element)
	{
		((JavascriptExecutor) driver)
		.executeScript("arguments[0].click()", element);
		return new UserActions(driver);
	}

	/**
	 * This method is useful to click on the based on the image of the element is passed as an argument
	 * 
	 * @param completeImagePath
	 * @return {@link UserActions}
	 * @throws FindFailed
	 */
	public void sikuliClick(String imagePath) throws FindFailed
	{
		// Create a screen object
		Screen screen = new Screen();

		// Wait for the input box to appear on the screen
		Pattern buttonPattern = new Pattern(imagePath).similar(0.1d);
		screen.wait(buttonPattern, 90); // Wait for 10 seconds

		Match inputBoxMatch = screen.find(buttonPattern);
		System.out.println("sikuli3");
		// Click on the input box to activate it
		screen.click(inputBoxMatch);



	}

	/**
	 * This method is useful to click on the based on the image of the element is passed as an argument
	 * 
	 * @param completeImagePath
	 * @return {@link UserActions}
	 * @throws FindFailed
	 */
	public void sikuliType(String imagePath, String inputText) throws FindFailed
	{


		// Create a screen object
		Screen screen = new Screen();

		// Wait for the input box to appear on the screen
		Pattern inputBoxPattern = new Pattern(imagePath).similar(0.1d);
		System.out.println("sikuli1");
		screen.wait(inputBoxPattern, 90); // Wait for 10 seconds
		//            System.out.println("sikuli2");
		//            // Find the input box on the screen
		//            //
		Match inputBoxMatch = screen.find(inputBoxPattern);
		System.out.println("sikuli3");
		// Click on the input box to activate it
		screen.click(inputBoxMatch);
		System.out.println("sikuli4");
		// Type the input text
		screen.type(inputBoxMatch, inputText);
		System.out.println("sikuli5");



	}

	public UserActions inputMicrosoftPasswordUsingSikuli(String password, String inputText) throws FindFailed
	{


		Pattern password_input = new Pattern(password).similar(0.1d);

		Screen screen = new Screen();
		screen.wait(password_input, 60);
		screen.type(password_input, inputText);
		screen.type(Key.ENTER);

		return new UserActions(driver);

	}

	public void sikuliTypeEnterKey() throws FindFailed
	{	

		// Create a screen object
		Screen screen = new Screen();
		try {
			// Type the Enter key
			screen.type(Key.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/***
	 * This method is useful to enter the value inside the text box.
	 * @param element
	 * @param textToBeEntered
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions sendKeys(WebElement element,String textToBeEntered, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			element.click();
			element.clear();
			element.sendKeys(textToBeEntered);
			return new UserActions(driver);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * 
	 * @param by
	 * @param textToBeEntered
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions sendKeys(By by, String textToBeEntered, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			waitTillElementLocated(by, 30, elementName, pageName);
			WebElement webElement = getWebElement(by);
			webElement.clear();
			webElement.sendKeys(textToBeEntered);
			return new UserActions(driver);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * @param element - {@link WebElement} type
	 * @param textToBeEntered - {@link String} type: Any input which must be a string data type
	 * @return {@link UserActions}
	 */
	public UserActions sendKeys(WebElement element,String textToBeEntered)
	{
		element.sendKeys(textToBeEntered);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * @param by - {@link By} type
	 * @param textToBeEntered - {@link String} type: Any input which must be a string data type
	 * @return {@link UserActions}
	 */
	public UserActions sendKeys(By by, String textToBeEntered)
	{
		getWebElement(by).sendKeys(textToBeEntered);
		return new UserActions(driver);
	}



	/***
	 * This method is useful to enter the keyboard value inside the text box.
	 * @param by
	 * @param key
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions sendKeys(By by, Keys key, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		WebElement webElement = getWebElement(by);
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			webElement.click();
			webElement.clear();
			webElement.sendKeys(key);
			return new UserActions(driver);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
	}

	/***
	 * This method is useful to enter the keyboard value inside the text box.
	 * @param element
	 * @param key
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions sendKeys(WebElement element, Keys key, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			element.click();
			element.clear();
			element.sendKeys(key);
			return new UserActions(driver);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * @param element - {@link WebElement} type
	 * @param key - {@link Keys} type
	 * @return {@link UserActions}
	 */
	public UserActions sendKeys(WebElement element, Keys key)
	{
		element.sendKeys(key);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * @param by - {@link By} type
	 * @param key - {@link Keys} type
	 * @return {@link UserActions}
	 */
	public UserActions sendKeys(By by, Keys key)
	{
		getWebElement(by).sendKeys(key);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * @param element
	 * @param textToBeEntered
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions actionsSendKeys(WebElement element, String textToBeEntered, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			new Actions(driver).click(element)
			.sendKeys(textToBeEntered)
			.build()
			.perform();
			return new UserActions(driver);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * 
	 * @param by
	 * @param textToBeEntered
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions actionsSendKeys(By by, String textToBeEntered, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		WebElement webElement = getWebElement(by);
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			new Actions(driver).click(webElement)
			.sendKeys(textToBeEntered)
			.build()
			.perform();
			return new UserActions(driver);
		}
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
	}

	/***
	 * This method is useful to enter the value inside the text box using {@link Actions} class
	 * @param element - {@link WebElement} type
	 * @param textToBeEntered - {@link String} type
	 * @return {@link UserActions}
	 */
	public UserActions actionsSendKeys(WebElement element, String textToBeEntered)
	{
		new Actions(driver)
		.sendKeys(element, textToBeEntered)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box using {@link Actions} class
	 * @param by - {@link By} type
	 * @param textToBeEntered - {@link String} type
	 * @return {@link UserActions}
	 */
	public UserActions actionsSendKeys(By by, String textToBeEntered)
	{
		new Actions(driver)
		.sendKeys(getWebElement(by), textToBeEntered)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the keyboard value inside the text box.
	 * @param by
	 * @param key
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions actionsSendKeys(By by, Keys key, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		WebElement webElement = getWebElement(by);
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			new Actions(driver).click(webElement)
			.sendKeys(webElement, key)
			.build()
			.perform();
			return new UserActions(driver);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
	}

	/***
	 * This method is useful to enter the keyboard value inside the text box.
	 * @param element
	 * @param key
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions actionsSendKeys(WebElement element, Keys key, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			new Actions(driver).click(element)
			.sendKeys(element, key)
			.build()
			.perform();
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box using {@link Actions} class.
	 * @param element - {@link WebElement} type
	 * @param key - {@link Keys} type
	 * @return {@link UserActions}
	 */
	public UserActions actionsSendKeys(WebElement element, Keys key)
	{
		new Actions(driver)
		.sendKeys(element, key)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box using {@link Actions} class
	 * @param by - {@link By} type
	 * @param key - {@link Keys} type
	 * @return {@link UserActions}
	 */
	public UserActions actionsSendKeys(By by, Keys key)
	{
		new Actions(driver)
		.sendKeys(getWebElement(by), key)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * @param element
	 * @param textToBeEntered
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions jsSendKeys(WebElement element, String textToBeEntered, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+textToBeEntered+"';", element);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box.
	 * 
	 * @param by
	 * @param textToBeEntered
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions jsSendKeys(By by, String textToBeEntered, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		WebElement webElement = getWebElement(by);
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+textToBeEntered+"';", webElement);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box using {@link JavascriptExecutor} class
	 * @param element - {@link WebElement} type
	 * @param textToBeEntered - {@link String} type
	 * @return {@link UserActions}
	 */
	public UserActions jsSendKeys(WebElement element, String textToBeEntered)
	{
		((JavascriptExecutor)driver)
		.executeScript("arguments[0].value='"+textToBeEntered+"';", element);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to enter the value inside the text box using {@link JavascriptExecutor} class
	 * @param by - {@link By} type
	 * @param textToBeEntered - {@link String} type
	 * @return {@link UserActions}
	 */
	public UserActions jsSendKeys(By by, String textToBeEntered)
	{
		((JavascriptExecutor)driver)
		.executeScript("arguments[0].value='"+textToBeEntered+"';", getWebElement(by));
		return new UserActions(driver);
	}

	/***
	 * This method is useful to select the desired option from the drop down by the visible text.
	 * @param element
	 * @param option
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions selectByVisibleText(WebElement element, String option, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			Select select = new Select(element);
			boolean flag = isValidOption(element, option, elementName, pageName);
			if (flag)
				select.selectByVisibleText(option);
			else
				throw new Exception(option + " is not a valid option in " + elementName);
		} 
		catch (CustomException e) 
		{
			throw new Exception(e);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to select the desired option from the drop down by the visible text.
	 * @param by
	 * @param option
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions selectByVisibleText(By by, String option, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			WebElement dropdown = getWebElement(by);
			Select select = new Select(dropdown);
			boolean flag = isValidOption(dropdown, option, elementName, pageName);
			if (flag)
				select.selectByVisibleText(option);
			else 
				throw new CustomException(option + " is not a valid option in " + elementName);
		} 
		catch (CustomException e) 
		{
			throw new Exception(e);
		}
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to select the desired option from the drop down by the visible text.
	 * @param element - {@link WebElement} type
	 * @param option - {@link String} type: the option which needs to be selected for the respective drop down
	 * @return {@link UserActions}
	 */
	public UserActions selectByVisibleText(WebElement element, String option) 
	{
		new Select(element)
		.selectByVisibleText(option);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to select the desired option from the drop down by the visible text.
	 * @param by - {@link By} type
	 * @param option - {@link String} type: the option which needs to be selected for the respective drop down
	 * @return {@link UserActions}
	 */
	public UserActions selectByVisibleText(By by, String option)
	{
		new Select(getWebElement(by))
		.selectByVisibleText(option);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to find out the desired option is present inside the respective drop down or not. If found then it will return true else false.
	 * @param element
	 * @param option
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isValidOption(WebElement element, String option, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			return options.parallelStream().anyMatch(e->e.getText().trim().equals(option));
		}
		catch(Exception e) 
		{
			throw new Exception(elementName + " is not displayed on " + pageName);
		}
	}

	/***
	 * This method is useful to find out the desired option is present inside the respective drop down or not. If found then it will return true else false.
	 * @param by
	 * @param option
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isValidOption(By by, String option, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			Select select = new Select(getWebElement(by));
			List<WebElement> options = select.getOptions();
			return options.parallelStream().anyMatch(e->e.getText().trim().equals(option));
		}
		catch(Exception e) 
		{
			throw new Exception(elementName + " is not displayed on " + pageName);
		}
	}

	/***
	 * This method is useful to select the desired option from the drop down based on the value attribute of the respective tag.
	 * @param by
	 * @param value
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions selectByValue(By by, String value, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			WebElement dropdown = getWebElement(by);
			Select select = new Select(dropdown);
			boolean flag = isValidValue(by, "value", elementName, pageName);
			if (flag)
				select.selectByValue(value);
			else
				throw new CustomException(value + " is not a valid for " + elementName);
		} 
		catch (CustomException e) 
		{
			throw new Exception(e);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to select the desired option from the drop down by the visible text.
	 * @param element - {@link WebElement} type
	 * @param value - {@link String} type: the option which needs to be selected for the respective drop down
	 * @return {@link UserActions}
	 */
	public UserActions selectByValue(WebElement element, String value) 
	{
		new Select(element)
		.selectByValue(value);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to select the desired option from the drop down by the visible text.
	 * @param by - {@link By} type
	 * @param value - {@link String} type: the option which needs to be selected for the respective drop down
	 * @return {@link UserActions}
	 */
	public UserActions selectByValue(By by, String value)
	{
		new Select(getWebElement(by))
		.selectByVisibleText(value);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to find out the desired option is present for the value attribute or not. if present then it'll return true else false. 
	 * @param by
	 * @param value
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean isValidValue(By by, String value, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			List<String> values = getAttributes(by, "value", elementName, pageName);
			return values.parallelStream().anyMatch(e->e.trim().equals(value));
		}
		catch(Exception e) 
		{
			throw new Exception(elementName + " is not displayed on " + pageName);
		}

	}

	/***
	 * This method is useful to find out the desired option is present for the value attribute or not. if present then it'll return true else false. 
	 * @param List<WebElement> elements
	 * @param value
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean isValidValue(List<WebElement> elements, String value, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{

			List<String> values = getAttributes(elements, "value", elementName, pageName);
			return values.parallelStream().anyMatch(e->e.trim().equals(value));
		}
		catch(Exception e) 
		{
			throw new Exception(elementName + " is not displayed on " + pageName);
		}

	}

	/***
	 * This method is useful to select the desired option from the drop down based on the index of the option.
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @param value
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions selectByIndex(By by, String elementName, String pageName, int index) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			WebElement dropdown = getWebElement(by);
			Select select = new Select(dropdown);
			boolean flag = isValidIndex(dropdown, index, elementName, pageName);
			if (flag)
				select.selectByIndex(index);
			else
				throw new CustomException(index + " is not a valid index for " + elementName);
		} 
		catch (CustomException e) 
		{
			throw new Exception(e);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to select the desired option from the drop down by the visible text.
	 * @param element - {@link WebElement} type
	 * @param index - {@link Integer} type: the index of the option which needs to be selected for the respective drop down
	 * @return {@link UserActions}
	 */
	public UserActions selectByIndex(WebElement element, int index) 
	{
		new Select(element)
		.selectByIndex(index);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to select the desired option from the drop down by the visible text.
	 * @param by - {@link By} type
	 * @param index - {@link Integer} type: the index of the option which needs to be selected for the respective drop down
	 * @return {@link UserActions}
	 */
	public UserActions selectByValue(By by, int index)
	{
		new Select(getWebElement(by))
		.selectByIndex(index);
		return new UserActions(driver);
	}

	/***
	 * This method is useful to find out the desired option is present for the value attribute or not. if present then it'll return true else false. 
	 * @param element
	 * @param index
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isValidIndex(WebElement element, int index, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		boolean flag = false;
		try
		{
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			if(index >= 0 || index < options.size())
				flag = true;
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}

		return flag;
	}

	/***
	 * This method is useful to find out the desired option is present for the value attribute or not. if present then it'll return true else false. 
	 * @param by
	 * @param index
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isValidIndex(By by, int index, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		boolean flag = false;
		try
		{
			Select select = new Select(getWebElement(by));
			List<WebElement> options = select.getOptions();
			if(index >= 0 || index < options.size())
				flag = true;
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}

		return flag;
	}

	/***
	 * This method is useful to retrieve all the options present for the drop down,
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getDropdownOptions(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		List<String> optionValues = new ArrayList<String>();
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			WebElement dropdown = getWebElement(by);
			Select select = new Select(dropdown);
			List<WebElement> options = select.getOptions();
			if (!options.isEmpty())
			{
				options.parallelStream().forEach(e -> optionValues.add(e.getText()));
			}
			else
				throw new CustomException(" No options are present for " + elementName);
		} 
		catch (CustomException e) 
		{
			throw new Exception(e);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return optionValues;
	}

	/***
	 * This method is useful to retrieve all the options present for the drop down,
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getDropdownOptions(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		List<String> optionValues = new ArrayList<String>();
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			if (!options.isEmpty())
			{
				options.parallelStream().forEach(e -> optionValues.add(e.getText()));
			}
			else
				throw new CustomException(" No options are present for " + elementName);
		} 
		catch (CustomException e) 
		{
			throw new Exception(e);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return optionValues;
	}

	/**
	 * This method is useful to get all the selected option from the given list. 
	 * Basically can be used for the multi-select list.
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getAllSelectedOptions(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		List<String> selectedOptionValues = new ArrayList<String>();
		try 
		{
			waitTillPageLoad(by, 30, pageName);
			WebElement dropdown = getWebElement(by);
			Select select = new Select(dropdown);
			List<WebElement> options = select.getOptions();
			if (!options.isEmpty())
			{
				options.parallelStream().forEach(e -> selectedOptionValues.add(e.getText()));
			}
			else
				throw new CustomException(" No options are present for " + elementName);
		} 
		catch (CustomException e) 
		{
			throw new Exception(e);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return selectedOptionValues;
	}

	/**
	 * This method is useful to get all the selected option from the given list. 
	 * Basically can be used for the multi-select list.
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getAllSelectedOptions(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		List<String> selectedOptionValues = new ArrayList<String>();
		try 
		{
			waitTillPageLoad(element, 30, pageName);
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			if (!options.isEmpty())
			{
				options.parallelStream().forEach(e -> selectedOptionValues.add(e.getText()));
			}
			else
				throw new CustomException(" No options are present for " + elementName);
		} 
		catch (CustomException e) 
		{
			throw new Exception(e);
		} 
		catch (Exception e) 
		{
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return selectedOptionValues;
	}

	/***
	 * This method is useful to verify whether we can select multiple values from the desired list.
	 * This will return only true or false based on the result.
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isMultiSelectList(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		boolean flag = false;
		try
		{
			waitTillPageLoad(by, 30, pageName);
			Select select = new Select(getWebElement(by));
			flag = select.isMultiple();
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return flag;
	}

	/***
	 * This method is useful to verify whether we can select multiple values from the desired list.
	 * This will return only true or false based on the result.
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isMultiSelectList(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		boolean flag = false;
		try
		{
			waitTillPageLoad(element, 30, pageName);
			Select select = new Select(element);
			flag = select.isMultiple();
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return flag;
	}

	/***
	 * This method is useful to get the default selected value of the dropdown.
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception
	 */
	public String getFirstSelectedOptionFromDropdown(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		String desiredValue="";
		try
		{
			waitTillPageLoad(by, 30, pageName);
			Select select = new Select(getWebElement(by));
			desiredValue = select.getFirstSelectedOption().getText();
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return desiredValue;
	}

	/***
	 * This method is useful to get the default selected value of the dropdown.
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return String
	 * @throws Exception
	 */
	public String getFirstSelectedOptionFromDropdown(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		String desiredValue="";
		try
		{
			waitTillPageLoad(element, 30, pageName);
			Select select = new Select(element);
			desiredValue = select.getFirstSelectedOption().getText();
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return desiredValue;
	}

	/***
	 * This method is useful to handle the web table.
	 * In this method it will iterate through all the table headers, it'll retrieve the table headers and their position and will store in a map.
	 * In this map the key parameter is the Table Header in String format and the respective table header position should be in integer format
	 * @param List<WebElement> tableHeaders
	 * @param tableName
	 * @param pageName
	 * @return Map<String, Integer>
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public Map<String, Integer> getColIndex(List<WebElement> tableHeaders, String tableName, String pageName) throws Exception 
	{
		if(tableName==null)
			throw new Exception("Table name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			Map<String, Integer> colNameIndex = new HashMap<String, Integer>();
			for (int i = 0; i < tableHeaders.size(); i++) {
				String actualColumnHeader = tableHeaders.get(i).getText();
				colNameIndex.put(actualColumnHeader, i + 1);
			}
			if (colNameIndex != null)
				return colNameIndex;
			else
				throw new Exception("Desired Table has no table headers");
		}
		catch(Exception e)
		{
			throw new Exception(tableName+" is not displayed on "+pageName);
		}

	}

	/***
	 * This method is useful to handle the web table.
	 * In this method it will iterate through all the table headers, it'll retrieve the table headers and their position and will store in a map.
	 * In this map the key parameter is the Table Header in String format and the respective table header position should be in integer format
	 * @param tableHeaders
	 * @param tableName
	 * @param pageName
	 * @return Map<String, Integer>
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public Map<String, Integer> getColIndex(By tableHeaders, String tableName, String pageName) throws Exception 
	{
		if(tableName==null)
			throw new Exception("Table name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try 
		{
			List<WebElement> tableHeaderElements = getWebElements(tableHeaders);
			Map<String, Integer> colNameIndex = new HashMap<String, Integer>();
			for (int i = 0; i < tableHeaderElements.size(); i++) {
				String actualColumnHeader = tableHeaderElements.get(i).getText();
				colNameIndex.put(actualColumnHeader, i + 1);
			}
			if (colNameIndex != null)
				return colNameIndex;
			else
				throw new Exception("Desired Table has no table headers");
		}
		catch(Exception e)
		{
			throw new Exception(tableName+" is not displayed on "+pageName);
		}

	}

	/***
	 * This method is useful to retrieve the row number of desired data in the given web table.
	 * @param List<WebElement> tableItems
	 * @param desiredItem
	 * @param tableName
	 * @param pageName
	 * @return int
	 * @throws Exception
	 */
	public int getRowIndex(List<WebElement> tableItems, String desiredItem, String tableName, String pageName) throws Exception 
	{
		if(tableName==null)
			throw new Exception("Table name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			int rowIndex = 0;
			for (int i = 0; i < tableItems.size(); i++) {
				String actualItemDescription = tableItems.get(i).getText();
				if (actualItemDescription.equals(desiredItem)) {
					rowIndex = i + 1;
					break;
				}
			}
			if (rowIndex != 0)
				return rowIndex;
			else
				throw new Exception("Desired Item is not present in cart table");
		}
		catch(Exception e)
		{
			throw new Exception(tableName+" is not displayed on "+pageName);
		}

	}

	/***
	 * This method is useful to retrieve the row number of desired data in the given web table.
	 * @param by
	 * @param desiredItem
	 * @param tableName
	 * @param pageName
	 * @return int
	 * @throws Exception
	 */
	public int getRowIndex(By by, String desiredItem, String tableName, String pageName) throws Exception 
	{
		if(tableName==null)
			throw new Exception("Table name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			int rowIndex = 0;
			List<WebElement> tableItems = getWebElements(by);
			for (int i = 0; i < tableItems.size(); i++) {
				String actualItemDescription = tableItems.get(i).getText();
				if (actualItemDescription.equals(desiredItem)) {
					rowIndex = i + 1;
					break;
				}
			}
			if (rowIndex != 0)
				return rowIndex;
			else
				throw new Exception("Desired Item is not present in cart table");
		}
		catch(Exception e)
		{
			throw new Exception(tableName+" is not displayed on "+pageName);
		}

	}

	/***
	 * This method is useful to get the random alpha numeric generated value
	 * @param n
	 * @return String
	 */
	public String randomString(int n) 
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) 
		{
			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	/***
	 * This method is useful to hover the mouse on the desired element.
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions mouseHoverOperation(WebElement element, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		waitTillPageLoad(element, 30, pageName);
		if (isDisplayed(element)) 
		{
			Point location = element.getLocation();
			int x = location.getX();
			int y = location.getY() - 300;
			((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")");
			new Actions(driver).moveToElement(element)
			.perform();
		} else {
			throw new Exception(elementName + " is not displayed on " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to hover the mouse on the desired element.
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions mouseHoverOperation(By by, String elementName, String pageName) throws Exception 
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		waitTillPageLoad(by, 30, pageName);
		if (isDisplayed(by)) 
		{
			WebElement element = getWebElement(by);
			Point location = element.getLocation();
			int x = location.getX();
			int y = location.getY() - 300;
			((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")");
			new Actions(driver).moveToElement(element)
			.perform();
		} else {
			throw new Exception(elementName + " is not displayed/enabled in " + pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to navigate to the desired application url
	 * @param url
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions navigateTo(String url) throws Exception
	{
		if(url!=null)
			driver.navigate().to(url);
		else
			throw new Exception("URL value should not be null");
		return new UserActions(driver);
	}

	/***
	 * This method is useful to navigate to the desired application url
	 * @param url
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions navigateTo(URL url) throws Exception
	{
		if(url!=null)
			driver.navigate().to(url);
		else
			throw new Exception("URL should not be null");
		return new UserActions(driver);
	}

	/***
	 * This method is useful to refresh the current focused web page.
	 * @return {@link UserActions}
	 */
	public  UserActions refreshPage() {
		driver.navigate()
		.refresh();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to navigate back to the previous page
	 * @return {@link UserActions}
	 */
	public UserActions navigateBack()
	{
		driver.navigate()
		.back();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to move forward to the next page
	 * @return {@link UserActions}
	 */
	public UserActions navigateForward()
	{
		driver.navigate()
		.forward();
		return new UserActions(driver);
	}



	/***
	 * This method is useful to collect the window IDs when multiple windows are getting opened during the automation script execution.
	 * @return Iterator<String>
	 */
	public Iterator<String> getWindowHandles()
	{
		return driver.getWindowHandles()
				.iterator();
	}

	/**
	 * This method is used for switching between windows
	 * 
	 * @param windowId
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions switchToWindow(String windowId) throws Exception
	{
		if(windowId!=null)
			driver.switchTo()
			.window(windowId); 
		else
			throw new Exception("Window id should not be a null value");
		return new UserActions(driver);
	}

	/***
	 * This method is responsible to scroll the page till the desired element is visible on the UI. 
	 * 
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 * 
	 */
	public UserActions jsScrollPageTillElementVisible(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			waitTillElementLocated(by, 30, elementName, pageName);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", getWebElement(by));
			return new UserActions(driver);
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not present in "+pageName);
		}

	}

	/***
	 * This method is responsible to scroll the page till the desired element is visible on the UI. 
	 * 
	 * @param element
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 * 
	 */
	public UserActions jsScrollPageTillElementVisible(WebElement element, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			waitTillPageLoad(element, 30, pageName);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
			return new UserActions(driver);
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not present in "+pageName);
		}

	}

	/***
	 * This method is responsible to scroll the page till the footer of the web page.
	 * 
	 * @return {@link UserActions}
	 */
	public UserActions jsScrollTillPageFooter()
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollTo(0, document.body.scrollHeight)");
		return new UserActions(driver);
	}

	/**
	 * This method will give user an opportunity to execute the custom script for performing the desired event.
	 * @param script
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions jsScriptExecutor(String script) throws Exception
	{
		if(script!=null)
			((JavascriptExecutor)driver).executeScript(script);
		else
			throw new Exception("The script value should not be null");
		return new UserActions(driver);
	}

	/***
	 * This method is useful to resize the current browser window size to the desired window size.
	 * @param width
	 * @param height
	 * @return {@link UserActions}
	 */
	public UserActions setCustomWindowSize(int width, int height)
	{
		driver.manage()
		.window()
		.setSize(new Dimension(width, height));
		return new UserActions(driver);
	}

	/***
	 * This method is useful to drag the desired element to the desired position.
	 * @param from
	 * @param to
	 * @param sourceElementName
	 * @param targetElementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions dragAndDrop(By from, By to, String sourceElementName, String targetElementName, String pageName) throws Exception
	{
		if(sourceElementName==null)
			throw new Exception("Source element name should not be null");
		if(targetElementName==null)
			throw new Exception("Target element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		WebElement source = getWebElement(from);
		WebElement target = getWebElement(to);
		waitTillPageLoad(from, 30, pageName);
		waitTillPageLoad(to, 30, pageName);
		new Actions(driver).dragAndDrop(source, target)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to drag the desired element to the desired position.
	 * @param source
	 * @param target
	 * @param sourceElementName
	 * @param targetElementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions dragAndDrop(WebElement source, WebElement target, String sourceElementName, String targetElementName, String pageName) throws Exception
	{
		if(sourceElementName==null)
			throw new Exception("Source element name should not be null");
		if(targetElementName==null)
			throw new Exception("Target element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		waitTillPageLoad(source, 30, pageName);
		waitTillPageLoad(target, 30, pageName);
		new Actions(driver).dragAndDrop(source, target)
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to drag the desired element to the desired position with the given offset value.
	 * @param from
	 * @param xOffset
	 * @param yOffset
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions dragAndDropBy(By by, int xOffset, int yOffset, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			waitTillPageLoad(by, 30, pageName);
			new Actions(driver).dragAndDropBy(getWebElement(by), xOffset, yOffset)
			.build()
			.perform();
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to drag the desired element to the desired position with the given offset value.
	 * @param element
	 * @param xOffset
	 * @param yOffset
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions dragAndDropBy(WebElement element, int xOffset, int yOffset, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			waitTillPageLoad(element, 30, pageName);
			new Actions(driver).dragAndDropBy(element, xOffset, yOffset)
			.build()
			.perform();
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to upload the file. 
	 * In this case the file upload dialog should be displayed on the screen.
	 * If the dialog is visible on the screen then it'll enter the complete file path in the file name field 
	 * and will click on open button to upload the desired file.
	 * If user wants to upload a single file then the format should be <Entire file path> 
	 * If user wants to upload multiple files then the format should be "<Entire path of File1>"<space>"<Entire path of File2>" and so on
	 * @param fileNameFieldImage
	 * @param openButtonImage
	 * @param fileToBeUploaded
	 * @return {@link UserActions}
	 * @throws FindFailed
	 */
	public UserActions fileUploadSikuli(String fileNameFieldImage, String openButtonImage, String fileToBeUploaded) throws FindFailed
	{
		Pattern fileName = new Pattern(fileNameFieldImage).similar(0.1d);
		Pattern openButton = new Pattern(openButtonImage).similar(0.1d);

		Screen screen = new Screen();
		screen.wait(fileName, 2);
		screen.type(fileName, fileToBeUploaded);
		screen.click(openButton);
		return new UserActions(driver);
	}

	/**
	 * This method is useful to upload the file using the file injection mechanism.
	 * @param by
	 * @param filePath
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */

	public UserActions fileUploadSelenium(By by, String filePath, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			getWebElement(by).sendKeys(filePath);
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return new UserActions(driver);
	}

	/**
	 * This method is useful to upload the file using the file injection mechanism.
	 * @param element
	 * @param filePath
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */

	public UserActions fileUploadSelenium(WebElement element, String filePath, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			element.sendKeys(filePath);
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return new UserActions(driver);
	}

	/**
	 * This method is used to switch inside the frame based on the given index
	 * 
	 * @param index
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions switchToFrame(int index, String pageName) throws Exception
	{
		if(pageName==null)
			throw new Exception("Page name should not be null");
		List<WebElement> totalNumberOfFrames = getWebElements(By.tagName("iframe"));
		if(index<=totalNumberOfFrames.size() && index>=0)
			driver.switchTo()
			.frame(index);
		else
			throw new IllegalArgumentException("Index value should in between 0 to "+totalNumberOfFrames.size());
		return new UserActions(driver);
	}

	/**
	 * This method is used to switch inside the frame based on the given name or id attribute
	 * 
	 * @param name or id - {@link String} type: Have to pass either the name or id attribute value of the respective "iframe" tag
	 * @return {@link UserActions}
	 */
	public UserActions switchToFrame(String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
		return new UserActions(driver);
	}

	/**
	 * This method is used to switch inside the frame based on the given by type element
	 * 
	 * @param by - {@link By} type
	 * @return {@link UserActions}
	 */
	public UserActions switchToFrame(By by)
	{
		driver.switchTo().frame(getWebElement(by));
		return new UserActions(driver);
	}

	/**
	 * This method is used to switch inside the frame based on the given web element
	 * 
	 * @param element - {@link WebElement} type
	 * @return {@link UserActions}
	 */
	public UserActions switchToFrame(WebElement element)
	{
		driver.switchTo().frame(element);
		return new UserActions(driver);
	}

	/**
	 * This method is used to switch to parent frame from child frame
	 * @return {@link UserActions}
	 */
	public UserActions switchToParentFrame()
	{
		driver.switchTo().parentFrame();
		return new UserActions(driver);
	}

	/**
	 * This method is used to switch to default page from frame
	 * @return {@link UserActions}
	 */
	public UserActions switchToDefaultContent()
	{
		driver.switchTo().defaultContent();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to capture the screenshot of the current web page and store the same inside current projects Screenshot\Failed folder.
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception
	 */
	public UserActions takeScreenshot(String pageName) throws Exception 
	{
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File(System.getProperty("user.dir")+"/Screenshots/" + pageName + getTimeStamp() + ".png"));
		} 
		catch (Exception e) 
		{
			throw new Exception(e);
		}
		return new UserActions(driver);
	}

	/**
	 * This method will help to capture the screenshot in base64 format which will help to attach the screenshot in reports
	 * @return String
	 * @throws Exception
	 */

	public String takeBase64Screenshot() throws Exception
	{
		try {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		} 
		catch (Exception e) 
		{
			throw new Exception(e);
		}
	}

	/**
	 * This method is useful to capture the screenshot of the desired webelement
	 * @param by
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions takeElementScreenshot(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			waitTillElementLocated(by, 30, elementName, pageName);
			File src = getWebElement(by).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File(System.getProperty("user.dir")+"/Screenshot/ElementScreenshot/" + elementName + getTimeStamp() + ".png"));
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return new UserActions(driver);
	}

	/**
	 * This method is useful to take the full page screenshot. As a user I need to pass the filePath where I want to save the image
	 * and also I need to pass the file name which will save my image with the given name.
	 * 
	 * @param filePath
	 * @param fileName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions takeFullPageScreenshot(String filePath, String fileName) throws Exception
	{
		if(filePath != null)
		{
			if(fileName != null)
				Shutterbug.shootPage(driver, Capture.FULL_SCROLL, 1000, true)
				.withName(fileName)
				.save(filePath);
			else
				throw new Exception("File name should not be a null value"); 
		}

		else
			throw new Exception("File path should not be a null value");
		return new UserActions(driver);
	}

	/**
	 * This method is useful to take the full screenshot of the desired WebElement. 
	 * As a user I need to pass a By type element, the filePath where I want to save the image and also I need to pass the file name 
	 * which will save my image with the given name. 
	 * 
	 * @param by
	 * @param filePath
	 * @param fileName
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions takeElementScreenshot(By by, String filePath, String fileName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			if(filePath != null)
			{
				if(fileName != null)
				{
					waitTillPageLoad(by, 30, pageName);
					Shutterbug.shootElement(driver, by, CaptureElement.FULL_SCROLL, true)
					.withName(fileName)
					.save(filePath);
				}
				else
					throw new Exception("File name should not be a null value");
			}
			else
				throw new Exception("File path should not be a null value");
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}

		return new UserActions(driver);
	}

	/**
	 * This method is useful to take the full screenshot of the desired WebElement. 
	 * As a user I need to pass a By type element, the filePath where I want to save the image and also I need to pass the file name 
	 * which will save my image with the given name. 
	 * 
	 * @param element
	 * @param filePath
	 * @param fileName
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions takeElementScreenshot(WebElement element, String filePath, String fileName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			if(filePath != null)
			{
				if(fileName != null)
				{
					waitTillPageLoad(element, 30, pageName);
					Shutterbug.shootElement(driver, element, CaptureElement.FULL_SCROLL, true)
					.withName(fileName)
					.save(filePath);
				}
				else
					throw new Exception("File name should not be a null value");
			}
			else
				throw new Exception("File path should not be a null value");
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return new UserActions(driver);
	}

	/**
	 * This method is useful to take the full frame screenshot. 
	 * As a user I need to pass a By type element, the filePath where I want to save the image and also I need to pass the file name 
	 * which will save my image with the given name. 
	 * 
	 * @param by
	 * @param filePath
	 * @param fileName
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions takeFrameScreenshot(By by, String filePath, String fileName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			if(filePath != null)
			{
				if(fileName != null)
				{
					waitTillPageLoad(by, 30, pageName);
					Shutterbug.shootFrame(driver, getWebElement(by), CaptureElement.FULL_SCROLL, 1000, true)
					.withName(fileName)
					.save(filePath);
				}
				else
					throw new Exception("File name should not be a null value");
			}
			else
				throw new Exception("File path should not be a null value");
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful to compare two different images with same size(dimensions).
	 * true: If both the images are exactly same
	 * false: If a small deviation found 
	 * @param fileNameWithPath
	 * @return boolean
	 * @throws Exception
	 */
	public boolean imageStrictCompare(String fileNameWithPath) throws Exception
	{
		boolean flag = false;
		if(fileNameWithPath!=null)
		{
			BufferedImage actualImage = ImageIO.read(new File(fileNameWithPath));
			flag = Shutterbug.shootPage(driver, Capture.FULL_SCROLL, 1000, true)
					.withName("FullScreenImage")
					.equals(actualImage);
		}
		else
			throw new Exception("File name path should not be null");
		return flag;
	}

	/***
	 * This method is useful to compare two different images with same size(dimensions).
	 * true: If both the images are exactly same or below the given deviation value 
	 * false: If exceeds the deviation value
	 * @param fileNameWithPath
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean imageCompare(String fileNameWithPath, double deviation) throws Exception
	{
		boolean flag = false;
		if(fileNameWithPath!=null)
		{
			BufferedImage actualImage = ImageIO.read(new File(fileNameWithPath));
			flag = Shutterbug.shootPage(driver, Capture.FULL_SCROLL, 1000, true)
					.withName("FullScreenImage")
					.equals(actualImage, deviation);
		}
		else
			throw new Exception("File name path should not be null");
		return flag;
	}

	/***
	 * This method is useful when user is trying to compare the desired base image with the currently captured image during run
	 * time. After the comparison, it'll save an image file in the destination(passed as an argument by the user) with the 
	 * differences highlighted in red color for the further validation.
	 * 
	 * @param fileNameWithPath
	 * @param destFilePathWithName
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean imageStrictCompare(String fileNameWithPath, String destFilePathWithName) throws Exception
	{
		boolean flag =false;
		if(fileNameWithPath!=null)
		{
			if(destFilePathWithName!=null)
			{
				BufferedImage actualImage = ImageIO.read(new File(fileNameWithPath));
				flag = Shutterbug.shootPage(driver, Capture.FULL_SCROLL, 1000, true)
						.withName("FullScreenImage")
						.equalsWithDiff(actualImage, destFilePathWithName);
			}
			else
				throw new Exception("Destination file name path should not be null");
		}
		else
			throw new Exception("File name path should not be null");

		return flag;
	}

	/**
	 * This method is useful to take the full screenshot of the desired WebElement in runtime and compare with the base image. 
	 * As a user I need to pass a By type element, the file name with path for validating 
	 * with the current run time image. After image comparison, if any difference found then it'll save as the destination location with the file name.
	 * 
	 * @param by
	 * @param filePath
	 * @param fileName
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public boolean takeElementScreenshotAndCompare(By by, String fileNameWithPath, String destFilePathWithName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		waitTillPageLoad(by, 30, pageName);
		boolean flag =false;
		if(fileNameWithPath!=null)
		{
			if(destFilePathWithName!=null)
			{
				BufferedImage actualImage = ImageIO.read(new File(fileNameWithPath));
				flag = Shutterbug.shootElement(driver, by, CaptureElement.FULL_SCROLL)
						.withName(elementName+"Image")
						.equalsWithDiff(actualImage, destFilePathWithName);
			}
			else
				throw new Exception("Destination file name path should not be null");
		}
		else
			throw new Exception("File name path should not be null");

		return flag;

	}

	/**
	 * This method is useful to take the full screenshot of the desired WebElement in runtime and compare with the base image. 
	 * As a user I need to pass a WebElement type element, the file name with path for validating 
	 * with the current run time image. After image comparison, if any difference found then it'll save as the destination location with the file name.
	 * 
	 * @param element
	 * @param filePath
	 * @param fileName
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public boolean takeElementScreenshotAndCompare(WebElement element, String fileNameWithPath, String destFilePathWithName, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		waitTillPageLoad(element, 30, pageName);
		boolean flag =false;
		if(fileNameWithPath!=null)
		{
			if(destFilePathWithName!=null)
			{
				BufferedImage actualImage = ImageIO.read(new File(fileNameWithPath));
				flag = Shutterbug.shootElement(driver, element, CaptureElement.FULL_SCROLL)
						.withName(elementName+"Image")
						.equalsWithDiff(actualImage, destFilePathWithName);
			}
			else
				throw new Exception("Destination file name path should not be null");
		}
		else
			throw new Exception("File name path should not be null");

		return flag;
	}

	/***
	 * This method is used to compare two different images. 
	 * User has to pass two different images with their full path for the comparison
	 * 
	 * @param img1
	 * @param img2
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean imageCompareUsingAshot(String img1, String img2) throws Exception
	{
		boolean flag = false;
		if(img1!=null)
		{
			if(img2!=null)
			{
				BufferedImage expectedImage = ImageIO.read(new File(img1));
				BufferedImage actualImage = ImageIO.read(new File(img2));
				ImageDiffer imgDiffer = new ImageDiffer();
				ImageDiff imgDiff = imgDiffer.makeDiff(expectedImage, actualImage);
				flag = imgDiff.hasDiff()?true:false;
			}
			else
				throw new Exception("Image2 file name path should not be null"); 
		}
		else
			throw new Exception("Image1 file name path should not be null");
		return flag;
	}

	/***
	 * This method is used to perform pixel wise comparison between two different images 
	 * @param img1
	 * @param img2
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean imageComparison(String img1, String img2) throws Exception
	{
		if(img1!=null)
		{
			if(img2!=null)
			{
				BufferedImage expectedImage = ImageIO.read(new File(img1));
				BufferedImage actualImage = ImageIO.read(new File(img2));

				if (expectedImage.getWidth() == actualImage.getWidth() && expectedImage.getHeight() == actualImage.getHeight()) 
				{
					for (int x = 0; x < expectedImage.getWidth(); x++) 
					{
						for (int y = 0; y < expectedImage.getHeight(); y++) 
						{
							if (expectedImage.getRGB(x, y) != actualImage.getRGB(x, y))
								return false;
						}
					}
				} else {
					return false;
				}

			}
			else
				throw new Exception("Image2 file name path should not be null");
		}
		else
			throw new Exception("Image1 file name path should not be null");
		return true;

	}

	/***
	 * This method will perform the image comparison between two different images and store the difference in another image
	 * @param img1
	 * @param img2
	 * @param filePathWithName
	 * @return BufferedImage
	 * @throws Exception 
	 */
	public BufferedImage getDifferencesInImageComparison(String img1, String img2, String filePathWithName) throws Exception
	{
		BufferedImage image3=null;
		if(img1!=null)
		{
			if(img2!=null)
			{
				if(filePathWithName!=null)
				{
					BufferedImage expectedImage = ImageIO.read(new File(img1));
					BufferedImage actualImage = ImageIO.read(new File(img2));
					image3 = new BufferedImage(expectedImage.getWidth(), expectedImage.getHeight(), expectedImage.getType());

					try 
					{
						int color;
						for (int x = 0; x < expectedImage.getWidth(); x++)
						{
							for (int y = 0; y < expectedImage.getHeight(); y++) 
							{
								color = Math.abs(actualImage.getRGB(x, y) - expectedImage.getRGB(x, y));
								image3.setRGB(x, y, color);
							}
						}
					} 
					catch (Exception e) 
					{
						System.out.println(e);
					}
				}
				else
					throw new Exception("File name path should not be null");
			}
			else
				throw new Exception("Image2 file name path should not be null");
		}
		else
			throw new Exception("Image2 file name path should not be null");		
		return image3;
	}

	/***
	 * This method is useful to generate a random number between the start value and end value-1
	 * @param startValue
	 * @param endValue
	 * @return long
	 */
	public long randomNumber(long startValue, long endValue)
	{
		return new Random().longs(startValue, endValue)
				.findFirst()
				.getAsLong();
	}

	/***
	 * This method is useful to generate a random number between the start value and end value-1
	 * @param startValue
	 * @param endValue
	 * @return int
	 */
	public int randomNumber(int startValue, int endValue)
	{
		return new Random().ints(startValue, endValue)
				.findFirst()
				.getAsInt();
	}

	/***
	 * This method is useful to get the dimension of the desired WebElement.
	 * This method will retrieve the width of the element and will be stored inside the map with "Width" key and height will be stored in "Height" key
	 * @param by - {@link By} type
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getWebElementDimension(By by)
	{
		Map<String, Integer> dimension = new HashMap<String, Integer>();
		Dimension size = getWebElement(by).getSize();
		dimension.put("Width", size.getWidth());
		dimension.put("Height", size.getHeight());
		return dimension;
	}

	/***
	 * This method is useful to get the dimension of the desired WebElement.
	 * This method will retrieve the width of the element and will be stored inside the map with "Width" key and height will be stored in "Height" key
	 * @param element - {@link WebElement} type
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getWebElementDimension(WebElement element)
	{
		Map<String, Integer> dimension = new HashMap<String, Integer>();
		Dimension size = element.getSize();
		dimension.put("Width", size.getWidth());
		dimension.put("Height", size.getHeight());
		return dimension;
	}

	/***
	 * This method is useful to resize the width of the desired element based on the given offset value.
	 * @param by - {@link By} type
	 * @param incrementBy
	 * @return {@link UserActions}
	 */
	public UserActions resizeElementWidth(By by, int incrementBy)
	{
		Map<String, Integer> dimension = getWebElementDimension(by);
		WebElement element = getWebElement(by);
		new Actions(driver).clickAndHold(element)
		.moveByOffset(dimension.get("Width") + incrementBy, dimension.get("Height"))
		.release()
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to resize the width of the desired element based on the given offset value.
	 * @param element - {@link WebElement} type
	 * @param incrementBy
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions resizeElementWidth(WebElement element, int incrementBy) throws Exception
	{
		Map<String, Integer> dimension = getWebElementDimension(element);
		new Actions(driver).clickAndHold(element)
		.moveByOffset(dimension.get("Width")+incrementBy, dimension.get("Height"))
		.release()
		.build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to resize the height of the desired element based on the given offset value.
	 * @param by - {@link By} type
	 * @param incrementBy
	 * @return {@link UserActions}
	 */
	public UserActions resizeElementHeight(By by, int incrementBy)
	{
		Map<String, Integer> dimension = getWebElementDimension(by);
		WebElement element = getWebElement(by);
		new Actions(driver).clickAndHold(element)
		.moveByOffset(dimension.get("Width"), dimension.get("Height") + incrementBy).release().build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to resize the height of the desired element based on the given offset value.
	 * @param element
	 * @param incrementBy
	 * @return {@link UserActions}
	 */
	public UserActions resizeElementHeight(WebElement element, int incrementBy)
	{
		Map<String, Integer> dimension = getWebElementDimension(element);
		new Actions(driver).clickAndHold(element)
		.moveByOffset(dimension.get("Width"), dimension.get("Height") + incrementBy).release().build()
		.perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to slide the slider handle with the given offset values
	 * @param by - {@link By} type
	 * @param xOffset - {@link Integer} type: The value should be shifted in the horizontal axis
	 * @param yOffset - {@link Integer} type: The value should be shifted in the vertical axis
	 * @return {@link UserActions}
	 */
	public UserActions slide(By by, int xOffset, int yOffset)
	{
		WebElement element = getWebElement(by);
		new Actions(driver).clickAndHold(element).moveByOffset(xOffset, yOffset).release(element).build().perform();
		return new UserActions(driver);
	}

	/***
	 * This method is useful to slide the slider handle with the given offset values
	 * @param element - {@link WebElement} type
	 * @param xOffset - {@link Integer} type: The value should be shifted in the horizontal axis
	 * @param yOffset - {@link Integer} type: The value should be shifted in the vertical axis
	 * @return {@link UserActions}
	 */
	public UserActions slide(WebElement element, int xOffset, int yOffset) throws Exception
	{
		new Actions(driver).clickAndHold(element).moveByOffset(xOffset, yOffset).release(element).build().perform();
		return new UserActions(driver);
	}

	/**
	 * This method is useful to wait for the desired element for 60 seconds maximum. If the element is visible within max time limit then
	 * controller will start executing the next line of code else it'll fail saying element not found
	 * 
	 * @param element
	 * @param seconds
	 * @return CommonEvents
	 */
	public UserActions waitTillElementVisible(WebElement element, long seconds) {
		new WebDriverWait(driver, Duration.ofSeconds(seconds))
		.until(ExpectedConditions.visibilityOf(element));
		return new UserActions(driver);
	}

	/**
	 * This method is useful to wait for the desired element for 60 seconds maximum. If the element is visible within max time limit then
	 * controller will start executing the next line of code else it'll fail saying element not found
	 * 
	 * @param by
	 * @param seconds
	 * @return {@link UserActions}
	 */
	public UserActions waitTillElementVisible(By by, long seconds) {
		new WebDriverWait(driver, Duration.ofSeconds(seconds))
		.until(ExpectedConditions.visibilityOf(getWebElement(by)));
		return new UserActions(driver);
	}

	/**
	 * This method is useful to wait for the desired element for 60 seconds maximum. If the element is clickable within max time limit then
	 * controller will start executing the next line of code else it'll fail saying element not clickable
	 * 
	 * @param element
	 * @param seconds
	 * @return {@link UserActions}
	 */
	public UserActions waitTillElementClickable(WebElement element, long seconds) {
		new WebDriverWait(driver, Duration.ofSeconds(seconds))
		.until(ExpectedConditions.elementToBeClickable(element));
		return new UserActions(driver);
	}

	/**
	 * This method is useful to wait for the desired element for 60 seconds maximum. If the element is clickable within max time limit then
	 * controller will start executing the next line of code else it'll fail saying element not clickable
	 * 
	 * @param by
	 * @param seconds
	 * @return {@link UserActions}
	 */
	public UserActions waitTillElementClickable(By by, long seconds) {
		new WebDriverWait(driver, Duration.ofSeconds(seconds))
		.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
		return new UserActions(driver);
	}


	/***
	 * This method is useful for locating the element in the DOM
	 * 
	 * @param by
	 * @param seconds
	 * @param elementName
	 * @param pageName
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions waitTillElementLocated(By by, long seconds, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		try
		{
			new WebDriverWait(driver, Duration.ofSeconds(seconds))
			.until(ExpectedConditions.presenceOfElementLocated(by));
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not located on "+pageName);
		}
		return new UserActions(driver);
	}

	/***
	 * This method is useful for locating the element in the DOM
	 * 
	 * @param by
	 * @param seconds
	 * @return {@link UserActions}
	 * @throws Exception 
	 */
	public UserActions waitTillElementLocated(By by, long seconds)
	{
		new WebDriverWait(driver, Duration.ofSeconds(seconds))
		.until(ExpectedConditions.presenceOfElementLocated(by));
		return new UserActions(driver);
	}

	/***
	 * This method is useful to wait for a particular {@link WebElement}'s attribute value contains the desired value
	 * @param by
	 * @param seconds
	 * @param attribute
	 * @param desiredValue
	 * @return {@link UserActions}
	 */
	public UserActions waitTillAttributeContains(By by, long seconds, String attribute, String desiredValue)
	{
		new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.attributeContains(by, attribute, desiredValue));
		return new UserActions(driver);
	}

	/***
	 * This method is useful to wait for a particular {@link WebElement}'s attribute value contains the desired value
	 * @param element
	 * @param seconds
	 * @param attribute
	 * @param desiredValue
	 * @return {@link UserActions}
	 */
	public UserActions waitTillAttributeContains(WebElement element, long seconds, String attribute, String desiredValue) throws Exception
	{
		new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.attributeContains(element, attribute, desiredValue));
		return new UserActions(driver);
	}

	/***
	 * This method is useful to wait for a particular {@link WebElement}'s attribute value contains the desired value
	 * @param by - {@link By} type
	 * @param seconds - time in seconds
	 * @param attribute
	 * @param desiredValue
	 * @return {@link UserActions}
	 */
	public UserActions waitTillAttributeToBe(By by, long seconds, String attribute, String desiredValue) throws Exception
	{
		new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.attributeToBe(by, attribute, desiredValue));
		return new UserActions(driver);
	}

	/***
	 * This method is useful to wait for a particular {@link WebElement}'s attribute value contains the desired value
	 * @param element
	 * @param seconds
	 * @param attribute
	 * @param desiredValue
	 * @return {@link UserActions}
	 */
	public UserActions waitTillAttributeToBe(WebElement element, long seconds, String attribute, String desiredValue)
	{
		new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.attributeToBe(element, attribute, desiredValue));
		return new UserActions(driver);
	}

	/***
	 * This method is useful for users to wait till a specific web element count should be equal to the parsed value
	 * 
	 * @param by - {@link By} type
	 * @param seconds - time in seconds
	 * @param count - expected count
	 * @return {@link UserActions}
	 */
	public UserActions waitTillNumberOfElementsToBe(By by, long seconds, int count) throws Exception
	{
		new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.numberOfElementsToBe(by, count));
		return new UserActions(driver);
	}

	/***
	 * This method is useful for users to wait till a specific web element count should be more than the parsed value
	 * 
	 * @param by - {@link By} type
	 * @param seconds - time in seconds
	 * @param count - expected count
	 * @return {@link UserActions}
	 */
	public UserActions waitTillNumberOfElementsToBeMoreThan(By by, long seconds, int count)
	{
		new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.numberOfElementsToBeMoreThan(by, count));
		return new UserActions(driver);
	}

	/***
	 * This method is useful for users to wait till a specific web element count should be less than the parsed value
	 * 
	 * @param by - {@link By} type
	 * @param seconds - time in seconds
	 * @param count - expected count
	 * @return {@link UserActions}
	 */
	public UserActions waitTillNumberOfElementsToBeLessThan(By by, long seconds, int count)
	{
		new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.numberOfElementsToBeLessThan(by, count));
		return new UserActions(driver);
	}

	/**
	 * This method is useful to wait till the ajax call gets completed.
	 * This wait will be based on the given user input seconds.
	 * @param seconds
	 * @param pageName
	 * @return {@link UserActions}
	 * 
	 */
	public UserActions waitTillPageLoad(By by, int seconds, String pageName) throws Exception
	{
		int count = 1;
		boolean isDisplayed = false;
		boolean noActive = false;
		boolean ajaxIsComplete = false;

		while(count<=seconds) 
		{
			ajaxIsComplete = (boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState")
					.toString()
					.equals("complete");
			if(ajaxIsComplete) 
			{
				try
				{
					noActive = (boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0;");
					isDisplayed = isDisplayed(by, 1);
				}
				catch(Exception e)
				{
					isDisplayed = isDisplayed(by, 1);
				}


				if (noActive || isDisplayed)
					break;
				else
					count++;
			}
			else
				count++;
		}
		if(count>seconds)
			throw new Exception("The ajax calls for "+pageName+" could not be completed in "+seconds+" seconds");
		return new UserActions(driver);
	}

	/**
	 * This method is useful to wait till the ajax call gets completed.
	 * This wait will be based on the given user input seconds.
	 * @param seconds
	 * @param pageName
	 * @return {@link UserActions}
	 * 
	 */
	public UserActions waitTillPageLoad(WebElement element, int seconds, String pageName) throws Exception
	{
		int count = 1;
		boolean isDisplayed = false;
		boolean noActive = false;
		boolean ajaxIsComplete = false;

		while(count<=seconds) 
		{
			ajaxIsComplete = (boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState")
					.toString()
					.equals("complete");
			if(ajaxIsComplete) 
			{
				try
				{
					noActive = (boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0;");
					isDisplayed = isDisplayed(element, 1);
				}
				catch(Exception e)
				{
					isDisplayed = isDisplayed(element, 1);
				}


				if (noActive || isDisplayed)
					break;
				else
					count++;
			}
			else
				count++;
		}
		if(count>seconds)
			throw new Exception("The ajax calls for "+pageName+" could not be completed in "+seconds+" seconds");
		return new UserActions(driver);
	}

	/***
	 * This method is useful to wait for a specific WebElement with a polling time.
	 * 
	 * @param by
	 * @param timeDuration
	 * @return {@link UserActions}
	 */
	public WebElement fluentWait(By by, long timeDuration)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeDuration)) 			
				.pollingEvery(Duration.ofSeconds(2)) 			
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver ) {
				return driver.findElement(by);
			}
		});
		return element;
	}

	/***
	 * This method is useful to wait for a specific WebElement with a polling time.
	 * 
	 * @param element
	 * @param timeDuration
	 * @return {@link UserActions}
	 */
	public WebElement fluentWait(final WebElement element, long timeDuration)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeDuration)) 			
				.pollingEvery(Duration.ofSeconds(2)) 			
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		WebElement finalElement = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver ) {
				return element;
			}
		});
		return finalElement;
	}

	/***
	 * This method will execute the script to verify whether the desired element is present in the DOM or not.
	 * It'll wait till the maximum timeout given by the user. 
	 * @param location - This should be the locator value
	 * @param locatorType - This should be the locator type. Only id or name or className or tagName or cssSelector or xpath is allowed
	 * @param maxTimeout - This is the maximum time the script will wait for
	 * @param elementName - Name of the web element
	 * @param pageName - Page name where the web element is present
	 * @return {@link UserActions}
	 * @throws Exception
	 */

	public UserActions waitTillElementLocatedInDom(String location, String locatorType, long maxTimeout, String elementName, String pageName) throws Exception
	{
		int count=1;
		long maxCount = maxTimeout*4;
		boolean flag = false;
		long totalCount = 0;
		do {
			try {
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					String path = "var aCount = document.evaluate(\"count("+location+")\", document, null, XPathResult.ANY_TYPE, null );return aCount.numberValue;";
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
					totalCount =(long) jsExecutor.executeScript(path);
				}
				else if(locatorType.equalsIgnoreCase("cssSelector"))
				{
					String path = "return document.querySelectorAll(\""+location+"\").length;";
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
					totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
				}
				else if(locatorType.equalsIgnoreCase("id"))
				{
					String finalLocation = "*[id='"+location+"']";
					String path = "return document.querySelectorAll(\""+finalLocation+"\").length;";
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
					totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
				}
				else if(locatorType.equalsIgnoreCase("name"))
				{
					String finalLocation = "*[name='"+location+"']";
					String path = "return document.querySelectorAll(\""+finalLocation+"\").length;";
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
					totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
				}
				else if(locatorType.equalsIgnoreCase("className"))
				{
					String finalLocation = "*[class='"+location+"']";
					String path = "return document.querySelectorAll(\""+finalLocation+"\").length;";
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
					totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
				}
				else if(locatorType.equalsIgnoreCase("tagName"))
				{
					String path = "return document.querySelectorAll(\""+location+"\").length;";
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
					totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
				}
				else
					throw new IllegalArgumentException(locatorType+" is not a valid locator type for location. The locator types can be "
							+ "id or name or className or tagName or cssSelector or xpath");

				if(totalCount>0)
					break;
				else {
					Thread.sleep(250);
					count++;
				}
			}catch(Exception e) {
				flag = false;
				count++;
			}
		}while(count<=maxCount && !flag);
		if(count==maxCount && flag==false)
			throw new NoSuchElementException(elementName+" is not present in "+pageName);
		return new UserActions(driver);
	}

	/***
	 * This method will execute the script to verify whether the desired element is present in the DOM or not.
	 * It'll wait till the maximum timeout given by the user. 
	 * @param location - This should be the locator value
	 * @param locatorType - This should be the locator type. Only id or name or className or tagName or cssSelector or xpath is allowed
	 * @param maxTimeout - This is the maximum time the script will wait for
	 * @return boolean - If the locator present in the DOM within the max timeout then return true else false
	 * @throws Exception 
	 */

	public boolean isElementLocatedInDom(String location, String locatorType, long maxTimeout) throws Exception
	{
		int count=1;
		long maxCount = maxTimeout*4;
		boolean flag = false;
		long totalCount = 0;
		do {
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				String path = "var aCount = document.evaluate(\"count("+location+")\", document, null, XPathResult.ANY_TYPE, null );return aCount.numberValue;";
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
				totalCount =(long) jsExecutor.executeScript(path);
			}
			else if(locatorType.equalsIgnoreCase("cssSelector"))
			{
				String path = "return document.querySelectorAll(\""+location+"\").length;";
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
				totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
			}
			else if(locatorType.equalsIgnoreCase("id"))
			{
				String finalLocation = "*[id='"+location+"']";
				String path = "return document.querySelectorAll(\""+finalLocation+"\").length;";
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
				totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
			}
			else if(locatorType.equalsIgnoreCase("name"))
			{
				String finalLocation = "*[name='"+location+"']";
				String path = "return document.querySelectorAll(\""+finalLocation+"\").length;";
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
				totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
			}
			else if(locatorType.equalsIgnoreCase("className"))
			{
				String finalLocation = "*[class='"+location+"']";
				String path = "return document.querySelectorAll(\""+finalLocation+"\").length;";
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
				totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
			}
			else if(locatorType.equalsIgnoreCase("tagName"))
			{
				String path = "return document.querySelectorAll(\""+location+"\").length;";
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
				totalCount = Long.parseLong((String) jsExecutor.executeScript(path).toString());
			}
			else
				throw new IllegalArgumentException(locatorType+" is not a valid locator type for location. The locator types can be "
						+ "id or name or className or tagName or cssSelector or xpath");

			if(totalCount>0) {
				flag = true;
				break;
			}
			else {
				Thread.sleep(250);
				count++;
			}
		}while(count<=maxCount);
		return flag;
	}



	/***
	 * This method is used to calculate the differences in the given times. As a user while invoking this method we need to pass the format of the time
	 *  along with the input times. This method will be returning the hour, minutes, seconds difference between two different input times.
	 *  To use the values, we've to use hoursDifference, minutesDifference, secondsDifference as a key parameter in the returned map value.
	 * @param format
	 * @param inputTime1
	 * @param inputTime2
	 * @return Map<String, Long>
	 */
	public Map<String, Long> getTimeDifference(String format, String inputTime1, String inputTime2)
	{
		Map<String, Long> timeDifferences = new HashMap<String, Long>();

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		LocalTime time1 = LocalTime.parse(inputTime1, dateTimeFormatter);
		LocalTime time2 = LocalTime.parse(inputTime2, dateTimeFormatter);

		long hoursDifference = ChronoUnit.HOURS.between(time1, time2);
		long minutesDifference = ChronoUnit.MINUTES.between(time1, time2)%60;
		long secondsDifference = ChronoUnit.SECONDS.between(time1, time2)%60;

		timeDifferences.put("hoursDifference", hoursDifference);
		timeDifferences.put("minutesDifference", minutesDifference);
		timeDifferences.put("secondsDifference", secondsDifference);

		return timeDifferences;
	}

	/***
	 * This method is useful to find the input data is in the desired pattern. As a user we need to pass the regular expression to this method.
	 * So that based on the regular expression, the method will match the desired input data and after the match, it'll return true or false.
	 * @param regEx
	 * @param inputData
	 * @return boolean
	 */
	public boolean isPatternMatches(String regEx, String inputData)
	{
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regEx);
		return pattern.matcher(inputData).find();
	}

	/***
	 * This method is useful for user to open a new tab in the same focused browser
	 * @return {@link UserActions}
	 */
	public UserActions openNewTab()
	{
		driver.switchTo().newWindow(WindowType.TAB);
		return new UserActions(driver);
	}

	/***
	 * This method is useful for user to open a new browser window
	 * @return {@link UserActions}
	 */
	public UserActions openNewWindow()
	{
		driver.switchTo().newWindow(WindowType.WINDOW);
		return new UserActions(driver);
	}

	/**
	 * This method is useful to close the current driver focused window
	 * @return {@link UserActions}
	 */
	public UserActions closeCurrentWindow()
	{
		driver.close();
		return new UserActions(driver);
	}

	/**
	 * This method is useful to update the current date format to desired date format.
	 * @param currentDate - {@link String}: current date value
	 * @param currentFormat - {@link String}: current date format
	 * @param requiredFormat - {@link String}: desired date format
	 * @return {@link String}
	 * @throws Exception
	 */
	public String dateFormatter(String currentDate, String currentFormat, String requiredFormat) throws Exception {
		DateFormat sdf = new SimpleDateFormat(currentFormat);
		Date date = sdf.parse(currentDate);
		return new SimpleDateFormat(requiredFormat).format(date);
	}

	/**
	 * This method is useful to add some days/weeks/months/years to the given date. The returned date format will be as per the input date format
	 * @param date - {@link String}: date value
	 * @param dateFormat - {@link String}: current date format
	 * @param typeToAdd - {@link String}: type of value want to add. Allowed options are days/weeks/months/years
	 * @param valueToAdd - {@link Integer}: how much want to add to the current value
	 * @return {@link String}
	 * @throws Exception
	 */
	public String addParamToDate(String date, String dateFormat, String typeToAdd, int valueToAdd) throws Exception {
		DateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		Calendar calender = Calendar.getInstance();
		calender.setTime(dateFormatter.parse(date));
		if(typeToAdd.equalsIgnoreCase("days"))
			calender.add(Calendar.DAY_OF_MONTH, valueToAdd);
		else if(typeToAdd.equalsIgnoreCase("weeks"))
			calender.add(Calendar.WEEK_OF_MONTH, valueToAdd);
		else if(typeToAdd.equalsIgnoreCase("months"))
			calender.add(Calendar.MONTH, valueToAdd);
		else if(typeToAdd.equalsIgnoreCase("years"))
			calender.add(Calendar.YEAR, valueToAdd);
		else
			throw new Exception(typeToAdd+" is not a valid option. Valid options are days, weeks or years");
		return dateFormatter.format(calender.getTime());
	}

	/**
	 * This method is useful to retrieve the day from the given date.
	 * @param date - {@link String}: date value in string format
	 * @param dateFormat - {@link String}: current date format
	 * @return {@link Integer}
	 * @throws Exception
	 */
	public int getDayFromDate(String date, String dateFormat) throws Exception {
		DateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		Date parse = dateFormatter.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * This method is useful to retrieve the month from the given date. Month value stands 0 for January and so on till 11 for December.
	 * Example: If the given date is 26/07/2015, then this method will return 6 as value. Similarly if the date value is 31-December-2000, then
	 * this method will return 11.
	 * @param date - {@link String}: date value in string format
	 * @param dateFormat - {@link String}: current date format
	 * @return {@link Integer}
	 * @throws Exception
	 */
	public int getMonthFromDate(String date, String dateFormat) throws Exception {
		DateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		Date parse = dateFormatter.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * This method is useful to retrieve the year from the given date.
	 * @param date - {@link String}: date value in string format
	 * @param dateFormat - {@link String}: current date format
	 * @return {@link Integer}
	 * @throws Exception
	 */
	public int getYearFromDate(String date, String dateFormat) throws Exception {
		DateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		Date parse = dateFormatter.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * This method is useful to generate the random date between the two give dates. It'll return a random date in "yyyy-mm-dd" format
	 * @param startDate - {@link String}: desired start date
	 * @param startDateFormat - {@link String}: start date format
	 * @param endDate - {@link String}: desired end date
	 * @param endDateFormat - {@link String}: end date format
	 * @return {@link String}
	 * @throws Exception
	 */
	public String generateRandomDate(String startDate, String startDateFormat, String endDate, String endDateFormat) throws Exception {

		int startDateDay = getDayFromDate(startDate, startDateFormat);
		int startDateMonth = getMonthFromDate(startDate, startDateFormat);
		int startDateYear = getYearFromDate(startDate, startDateFormat);

		int endDateDay = getDayFromDate(endDate, endDateFormat);
		int endDateMonth = getMonthFromDate(endDate, endDateFormat);
		int endDateYear = getYearFromDate(endDate, endDateFormat);

		LocalDate startingDate = LocalDate.of(startDateYear, startDateMonth+1, startDateDay);
		long start = startingDate.toEpochDay();

		LocalDate endingDate = LocalDate.of(endDateYear, endDateMonth+1, endDateDay);
		long end = endingDate.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		String randomDate = LocalDate.ofEpochDay(randomEpochDay).toString();
		return randomDate;
	}

	public UserActions clickOnAnElementIfItIsPresent(By by) {

		// Find all matching elements on the page
		java.util.List<WebElement> elements = driver.findElements(by);

		// Check if any elements are found
		if (!elements.isEmpty()) {
			// Click on the first matching element
			elements.get(0).click();
		}
		return new UserActions(driver);
	}
	public UserActions fileUpload(String pathOfTheFile ) throws AWTException
	{
		StringSelection selectFile=new StringSelection(pathOfTheFile); 
		//		internally copy the file
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectFile, null);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		return new UserActions(driver);
	}

	public UserActions highlightElement(WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		// Add the highlight
		jse.executeScript("arguments[0].setAttribute('style', 'border: solid 5px green');", element);

		try {
			// Wait for a specified time (e.g., 500 milliseconds)
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Remove the highlight
		jse.executeScript("arguments[0].setAttribute('style', '');", element);

		return new UserActions(driver);
	}

	public UserActions highlightElementAfterAction(WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].setAttribute('style','border: solid 5px green');", element);

		try {
			// Wait for a specified time (e.g., 500 milliseconds)
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Remove the highlight
		jse.executeScript("arguments[0].setAttribute('style', '');", element);

		return new UserActions(driver);
	}

	public UserActions performAltN() {
		// Create Actions object
		Actions actions = new Actions(driver);

		// Perform Alt + N keyboard operation
		actions.keyDown(Keys.ALT)
		.sendKeys("n")
		.keyUp(Keys.ALT)
		.build()
		.perform();

		return new UserActions(driver);
	}

	/***
	 * This method is useful to get the default selected value of the dropdown.
	 * @param by
	 * @return String
	 * @throws Exception
	 */
	public String getFirstSelectedOption(By by) throws Exception
	{
		String desiredValue=""; 
		try
		{
			Select select = new Select(getWebElement(by));
			desiredValue = select.getFirstSelectedOption().getText();
		}
		catch(Exception e)
		{
			throw e;
		}
		return desiredValue;
	}

	public UserActions acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("No alert present: " + e.getMessage());
		}
		return new UserActions(driver);
	}

	public UserActions dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("No alert present: " + e.getMessage());
		}
		return new UserActions(driver);
	}

	public UserActions scrollIntoView(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);

		return new UserActions(driver);
	}

	public boolean isElementVisible(By element) {
		try {
			waitTillElementVisible(element, 60);
			return getWebElement(element).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String validateAlertMessage() {
		String alertMessageValue = "";
		try {
			Alert alert = driver.switchTo().alert();
			alertMessageValue = alert.getText();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("No alert present: " + e.getMessage());
		}
		return alertMessageValue;
	}
}

