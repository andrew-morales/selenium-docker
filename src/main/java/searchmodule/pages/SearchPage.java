package searchmodule.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
//	@FindBy(name = "q")
//	private WebElement searchTxt;
	
	@FindBy(id = "search_form_input_homepage")
	private WebElement searchTxt;
	
	@FindBy(id = "search_button_homepage")
	private WebElement searchBtn;
	
	@FindBy(linkText = "Videos")
	private WebElement videosLink;
	
//	@FindBy(className = "time--vid")
//	private List<WebElement> allVideos;
	
	@FindBy(className = "tile__media")
	private List<WebElement> allVideos;
	
	public SearchPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public void goTo(){
		driver.get("https://duckduckgo.com/");
	}
	
	public void doSearch(String keyword){
		wait.until(ExpectedConditions.visibilityOf(this.searchTxt));
		searchTxt.clear();
		searchTxt.sendKeys(keyword);
		searchBtn.click();
	}
	
	public void goToVideos(){
		wait.until(ExpectedConditions.visibilityOf(videosLink));
		videosLink.click();
	}
	
	public int getResult(){
		By by = By.className("tile__media");
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
		System.out.println(allVideos.size());
		return allVideos.size();
	}
}
