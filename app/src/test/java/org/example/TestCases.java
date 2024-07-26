package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class TestCases {

    static ChromeDriver driver;
    static wrapperMethods utility;
    static JavascriptExecutor js;
    static WebDriverWait wait;

    @BeforeSuite(alwaysRun = true)
    public static void driverSetUp() {
        // System.out.println("Initializing : TestCases");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        utility = new wrapperMethods(driver);
        //js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @Test(priority = 1, enabled = true)
    public void createAccount() throws InterruptedException {
  
        //try {
            utility.navigateTo("https://trulyfreehome.dev/");
            Thread.sleep(2000);

            WebElement click_Yes = utility.findElement(By.xpath("//button[@class='jsx-46f6405b54523d64 join_btn']"));
            utility.click(click_Yes);

            WebElement mobile_Number = utility.findElement(By.xpath("//input[@id='regPhoneInput']"));
            utility.sendKeys(mobile_Number, "3033999233");

            WebElement click_Checkbox = utility.findElement(By.xpath("//div[@class='jsx-2527197127 pr-2 pt-2']/input"));
            utility.click(click_Checkbox);

            WebElement click_Continue = utility.findElement(By.xpath("//button[@type='submit']"));
            utility.click(click_Continue);

            WebElement verification1 = utility
                    .findElement(By.xpath("//input[@aria-label='Please enter verification code. Digit 1']"));
            utility.sendKeys(verification1, "1");

            WebElement verification2 = utility.findElement(By.xpath("//input[@aria-label='Digit 2']"));
            utility.sendKeys(verification2, "1");
            
            WebElement verification3 = utility.findElement(By.xpath("//input[@aria-label='Digit 3']"));
            utility.sendKeys(verification3, "1");
            
            WebElement verification4 = utility.findElement(By.xpath("//input[@aria-label='Digit 4']"));
            utility.sendKeys(verification4, "1");

            WebElement FN = utility.findElement(By.xpath("//input[@label='First Name*']"));
            utility.sendKeys(FN, "ksrisha");

            WebElement LN = utility.findElement(By.xpath("//input[@label='Last Name*']"));
            utility.sendKeys(LN, "buda");

            WebElement email = utility.findElement(By.xpath("//input[@label='Enter your best email*']"));
            utility.sendKeys(email, "ksiribuda84@gmail.com");
           
            WebElement click_done = utility.findElement(By.xpath("//*[text()='DONE']"));
            utility.click(click_done);

            System.out.println("Successfully Signed UP");

        //} catch (Exception e) {
        //    e.printStackTrace();
        //    Assert.fail("Test case failed due to exception: " + e.getMessage());
        //}

    }

    @Test(priority = 2, enabled = true)
    public static void megaMenu() throws InterruptedException {
        //try {

            driver.getCurrentUrl();
            WebElement click_brand = utility
                    .findElement(By.xpath("//div[@class='jsx-2446998146 ecom_wrapper']/div/nav/ul/li[2]"));
            // utility.click(click_brand);
            Actions actions = new Actions(driver);
            // Hover over the "Brands" menu item
            actions.moveToElement(click_brand).click().perform();
            Thread.sleep(1000);

            WebElement click_brand_dropdown = utility.findElement(By.xpath("//div[@class=\"jsx-2890353459 \"]/a/p[text()='Emani']"));
            actions.moveToElement(click_brand_dropdown).click().perform();
            // utility.click(click_brand_dropdown);
            Thread.sleep(1000);

            System.out.println("Successfully clicked on Brand Emani");

        //} catch (Exception e) {
        //    e.printStackTrace();
       // }
    }

    @Test(priority = 3, enabled = true)
    public static void PLP_ToExcel() throws IOException, InterruptedException {
        driver.getCurrentUrl();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'product_image')]")));

        // Extract product details
        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'product_image')]"));
        List<Object[]> productDetails = new ArrayList<>();       

        for(WebElement product : products) {            
            String productName = wait.until(ExpectedConditions.visibilityOf(product.findElement(By.xpath("//span[contains(@class,'card_title')]")))).getText();
            System.out.println(productName);
            String productPrice = wait.until(ExpectedConditions.visibilityOf(product.findElement(By.xpath("//span[contains(@class,'stroke')]")))).getText();
            System.out.println(productPrice);
            productDetails.add(new Object[]{productName, productPrice});

            //Thread.sleep(500);
        }
        
        // Define the file path and sheet name
        String filePath = "EmaniProducts.xlsx";
        String sheetName = "EmaniProducts";

        // Write data into Excel
        utility.writeInToExcel(filePath, sheetName, productDetails.toArray(new Object[0][]));
    }

    @Test(priority = 4, enabled = true)
    public static void productDetailsPage() throws InterruptedException {

        Thread.sleep(1000);

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='jsx-c3ec2b6b129f087d product_image pb-2']"));
        WebElement firstProduct = products.get(0);
        utility.click(firstProduct);

        // Validating product detail displayed on not
        WebElement product_Details_Page = utility.findElement(By.xpath("//h1[@class='jsx-1005774697 product_name']"));
        Assert.assertTrue(product_Details_Page.isDisplayed());

        // change the quantity
        WebElement quantityField = utility.findElement(By.xpath("//select[@aria-label='Timeline']"));
        Select select_dropdown = new Select(quantityField);
        select_dropdown.selectByValue("3");
        Thread.sleep(500);

        WebElement addToCart = utility.findElement(By.xpath("//button[text()='Add to Cart']"));
        utility.click(addToCart);
        Thread.sleep(800);

        WebElement cart_Count = utility.findElement(By.xpath("//span[@class='jsx-1508469962 product_count']"));
        String cartCount = cart_Count.getText();
        String expected = cartCount;

        Assert.assertEquals(cartCount, expected, "Successfully Added to cart");

    }

    @Test(priority = 5, enabled = true)
    public static void onClick_cartIcon() throws InterruptedException {

        //click on cart icon
        WebElement cart_Icon_Click = utility.findElement(By.xpath("//a[@href='/cart']"));
        utility.click(cart_Icon_Click);
        Thread.sleep(800);

        Assert.assertTrue(utility.findElement(By.xpath("//h2[@class='title_2']")).isDisplayed());

        // validating product name in the cart
        String actual_Prd_Name = utility.findElement(By.xpath("//h2[@class='title_2']")).getText();
        String expected_prd_Name = actual_Prd_Name;
        Assert.assertEquals(actual_Prd_Name, expected_prd_Name, "Product name does not match in Cart");

        // validating product price in cart
        String actualProductPrice = utility.findElement(By.xpath("//span[@class='total_price ml-auto']")).getText();
        String expected_Prd_Price = actualProductPrice;
        Assert.assertEquals(actualProductPrice, expected_Prd_Price, "Product price does not match in cart");

        // Click on the "Proceed to Checkout" button
        WebElement checkOut_Click = utility.findElement(By.xpath("//a[@class='w-100 proceed_btn desktop_btn']"));
        utility.click(checkOut_Click);

    }

    @Test(priority = 6, enabled = true)

    public static void shipping_Details() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));

        WebElement first_Name = utility.findElement(By.name("firstName"));
        utility.sendKeys(first_Name, "Buda");

        WebElement last_Name = utility.findElement(By.name("lastName"));
        utility.sendKeys(last_Name, "siri");

        WebElement phone_Number = utility.findElement(By.id("regPhoneInput"));
        utility.sendKeys(phone_Number, "3033362233");

        WebElement address = utility.findElement(By.name("addLine1"));
        utility.sendKeys(address, "24 Battery P1");

        WebElement zipCode = utility.findElement(By.name("pincode"));
        utility.sendKeys(zipCode, "10004");

        WebElement city = utility.findElement(By.name("city"));
        utility.sendKeys(city, "New York");

        WebElement state = utility.findElement(By.xpath("state"));
        utility.sendKeys(state, "New York");

        WebElement country = utility.findElement(By.xpath("//div[@class='select__single-value css-n5bts8']"));
        utility.sendKeys(country, "United States of America");

        WebElement continueClick = utility.findElement(By.id("continue_btn"));
        utility.click(continueClick);
        //Thread.sleep(2000);


        // wait.until(ExpectedConditions.elementToBeClickable(By.id("exclusiveDealsCloseButton"))).click();
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("thankYouPage")));
    }

    @Test(priority = 7, enabled = true)

    public static void addCard_Details() throws InterruptedException {

        driver.getCurrentUrl();                

        WebElement add_Card_plus_Click = utility.findElement(By.xpath("//button[@class='jsx-3023080569 button__link']"));
        utility.click(add_Card_plus_Click);       
        Thread.sleep(1000);

        WebElement card_Name = utility.findElement(By.id("full_name"));
        utility.sendKeys(card_Name, "BudaSiri");    
        
        Thread.sleep(1000);

        WebElement card_Number = utility.findElement(By.xpath("//div[@class='jsx-65ec41720f925320 row ']/div/div[@id='spreedly-number-test']"));
        //if(card_Number.isDisplayed()){
            //utility.click(card_Number);
            utility.sendKeys(card_Number, "4242 4242 4242 4242");
        //}
        Thread.sleep(400);
        // utility.click(card_Number);
        
       
        WebElement cvv = utility.findElement(By.xpath("//div[@class='jsx-65ec41720f925320 row ']/div/div[@id='cvv']"));
        utility.sendKeys(cvv, "111");

        WebElement month = utility.findElement(By.id("month"));
        utility.sendKeys(month, "05");

        WebElement year = utility.findElement(By.id("year"));
        utility.sendKeys(year, "2026");

        WebElement add_Card_Click = utility.findElement(By.id("sumbitbutton"));
        utility.click(add_Card_Click);
        

        WebElement confirm_Order_Click = utility.findElement(By.id("confirm_btn"));
        utility.click(confirm_Order_Click);
       

        WebElement click_on_Cross = utility.findElement(By.xpath("//*[@class='close_icon']"));
        utility.click(click_on_Cross);
        

    }

    @Test(priority = 8, enabled = true)
    private void verifyOrder_Logout() {
        driver.getCurrentUrl();
        wait.until(ExpectedConditions.urlContains("orders"));

        String orderId = utility.findElement(By.className("jsx-353953937")).getText();
        System.out.println("Order NO is : " + orderId);

        WebElement profile_Click = utility.findElement(By.xpath("//*[@class='arrow_icon md_none']"));
        utility.click(profile_Click);

        WebElement logout_click = utility.findElement(By.xpath("//*[text()='Logout']"));
        utility.click(logout_click);

        WebElement logout = utility.findElement(By.xpath("//button[text()='Logout']"));
        utility.click(logout);

    }

    @AfterSuite(alwaysRun = true)

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
