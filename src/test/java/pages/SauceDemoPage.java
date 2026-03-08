package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;


public class SauceDemoPage {

    public SauceDemoPage() { PageFactory.initElements(Driver.getDriver(), this); }

    @FindBy(xpath = "//input[@id='user-name']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id='login-button']")
    public WebElement loginButton;

    @FindBy(xpath = "//span[@class='title']")
    public WebElement products;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    public WebElement addToCart;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement basket;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    public WebElement backpackUrunBasligi;

    @FindBy(xpath = "//button[@id='checkout']")
    public WebElement checkout;

    @FindBy(xpath = "//input[@id='first-name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    public WebElement postalCode;

    @FindBy(xpath = "//input[@id='continue']")
    public WebElement continueButton;

    @FindBy(xpath = "//h2[@class='complete-header']")
    public WebElement complete;

    @FindBy(xpath = "//button[@id='finish']")
    public WebElement finish;

    @FindBy(xpath = "//button[@id='back-to-products']")
    public WebElement backHome;

    public void loginOl() {
        username.sendKeys(ConfigReader.getProperty("username"));
        password.sendKeys(ConfigReader.getProperty("password"));
        loginButton.click();
    }

    public void formuDoldur() {
        firstName.sendKeys(ConfigReader.getProperty("firstname"));
        lastName.sendKeys(ConfigReader.getProperty("lastname"));
        postalCode.sendKeys(ConfigReader.getProperty("postalcode"));
        continueButton.click();
    }

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorMessage;

    public void login(String kullaniciAdi, String sifre) {
        username.sendKeys(kullaniciAdi);
        password.sendKeys(sifre);
        loginButton.click();
    }



}
