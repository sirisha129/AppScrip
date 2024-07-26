package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

public class wrapperMethods {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public wrapperMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void click(WebElement element) {
        try {
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            Thread.sleep(1000);
            
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking: " + e.getMessage());
        }
    }

    public void sendKeys(WebElement element, String keysToSend) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(keysToSend);
            Thread.sleep(900);
        } catch (Exception e) {
            System.out.println("Exception occurred while sending keys: " + e.getMessage());
        }
    }

    public WebElement findElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));           
            return element;
        } catch (Exception e) {
            System.out.println("Exception occurred while finding element: " + locator + " - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void navigateTo(String url) {
        try {
            driver.get(url);
            Thread.sleep(3000);
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            //wait.until(ExpectedConditions.urlToBe("https://trulyfreehome.dev/"));
        } catch (Exception e) {
            System.out.println("Exception occurred while navigating to the URL: " + e.getMessage());
        }
    }

    public void writeInToExcel(String filePath, String sheetName, Object[][] dataToWrite) {
        System.out.println("*Adding new row to: " + sheetName + "*");

        try {
            XSSFWorkbook workbook;
            XSSFSheet sheet;
            File file = new File(filePath);

            if (file.exists()) {
                // Open existing workbook
                workbook = new XSSFWorkbook();
                sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    // Create sheet if it doesn't exist
                    sheet = workbook.createSheet(sheetName);
                }
            } else {
                // Create new workbook and sheet
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet(sheetName);
            }

            // Getting the count of existing records
            int rowCount = sheet.getLastRowNum();

            // Iterating new data to update
            for (Object[] data : dataToWrite) {
                // Creating new row from the next row count
                XSSFRow row = sheet.createRow(++rowCount);
                int columnCount = 0;
                // Iterating through each piece of information
                for (Object info : data) {
                    // Creating new cell and setting the value
                    XSSFCell cell = row.createCell(columnCount++);
                    if (info instanceof String) {
                        cell.setCellValue((String) info);
                    } else if (info instanceof Integer) {
                        cell.setCellValue((Integer) info);
                    } else if (info instanceof Double) {
                        cell.setCellValue((Double) info);
                    }
                }
            }

            // Creating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(file);
            workbook.write(os);

            // Close the workbook and output stream
            workbook.close();
            os.close();

            System.out.println("Excel file has been updated successfully.\n");

        } catch (Exception e) {
            System.err.println("Exception while updating an existing excel file.\n");
            e.printStackTrace();
        }
    }
    
    
    
}
