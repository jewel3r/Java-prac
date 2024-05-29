package com.example.luck;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTest {

    @Test
    void MainPage() {
        ChromeDriver driver = new ChromeDriver();
        //driver.get("https://www.google.com/");
        driver.get("http://localhost:8080/");
        assertEquals("", driver.getTitle());
        driver.quit();
    }

    @Test
    void HeaderTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/");

        WebElement clientButton = driver.findElement(By.id("clients"));
        clientButton.click();
        Duration duration = Duration.ofSeconds(1);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Client List", driver.getTitle());

        WebElement rootButton = driver.findElement(By.id("home"));
        rootButton.click();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("", driver.getTitle());

        WebElement empButton = driver.findElement(By.id("employees"));
        empButton.click();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Employee List", driver.getTitle());

        rootButton = driver.findElement(By.id("home"));
        rootButton.click();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("", driver.getTitle());

        WebElement servButton = driver.findElement(By.id("services"));
        servButton.click();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Services List", driver.getTitle());

        rootButton = driver.findElement(By.id("home"));
        rootButton.click();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("", driver.getTitle());

        WebElement contractButton = driver.findElement(By.id("contracts"));
        contractButton.click();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Contract List", driver.getTitle());

        rootButton = driver.findElement(By.id("home"));
        rootButton.click();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("", driver.getTitle());

        driver.quit();
    }

    @Test
    void addAndCancel() {
        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://localhost:8080/clients");
        assertEquals("Client List", driver.getTitle());
        Duration duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        WebElement addClient = driver.findElement(By.id("addButton"));
        Actions actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        //addClient.click();
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Информация о человеке", driver.getTitle());

        driver.findElement(By.id("clientName")).sendKeys("ноунейм");
        driver.findElement(By.id("clientAddress")).sendKeys("ноуадр");
        driver.findElement(By.id("clientPhone")).sendKeys("ноуфон");
        driver.findElement(By.id("clientEmail")).sendKeys("ноумейл");
        WebElement we = driver.findElement(By.id("clientBirthDate"));
        we.clear();
        we.sendKeys("22-11-2015");
        WebElement element = driver.findElement(By.id("submitButton"));
        actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        driver.manage().timeouts().implicitlyWait(duration);

        assertEquals("Client List", driver.getTitle());
        driver.get("http://localhost:8080/clients?page=1");
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Client List", driver.getTitle());
        driver.get("http://localhost:8080/clients?page=0");
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Client List", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(duration);
        driver.get("http://localhost:8080/clients?page=13");
        assertEquals("Ошибка!", driver.getTitle());

        driver.get("http://localhost:8080/clients");
        assertEquals("Client List", driver.getTitle());
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("addButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        //addClient.click();
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Информация о человеке", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("cancelButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Client List", driver.getTitle());

        /*
        EMPLOYEE
         */

        driver.get("http://localhost:8080/employees");
        assertEquals("Employee List", driver.getTitle());
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("addButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        //addClient.click();
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Информация о сотруднике", driver.getTitle());

        driver.findElement(By.id("employeeName")).sendKeys("ноунейм");
        driver.findElement(By.id("employeeAddress")).sendKeys("ноуадр");
        driver.findElement(By.id("employeePhone")).sendKeys("ноуфон");
        driver.findElement(By.id("employeeEmail")).sendKeys("ноумейл");
        driver.findElement(By.id("employeeEducation")).sendKeys("ноуед");
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        WebElement Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("employeeBirthDate")));
        Category_Body.sendKeys("22-11-2015");
        addClient = driver.findElement(By.id("submitButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        driver.manage().timeouts().implicitlyWait(duration);

        assertEquals("Employee List", driver.getTitle());
        driver.get("http://localhost:8080/employees?page=1");
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Employee List", driver.getTitle());
        driver.get("http://localhost:8080/employees?page=0");
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Employee List", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(duration);
        driver.get("http://localhost:8080/employees?page=13");
        assertEquals("Ошибка!", driver.getTitle());

        driver.get("http://localhost:8080/employees");
        assertEquals("Employee List", driver.getTitle());
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("addButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        //addClient.click();
        duration = Duration.ofSeconds(10);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Информация о сотруднике", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        addClient = driver.findElement(By.id("home"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("", driver.getTitle());

        driver.get("http://localhost:8080/employees");
        assertEquals("Employee List", driver.getTitle());
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("addButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        //addClient.click();
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Информация о сотруднике", driver.getTitle());

        driver.findElement(By.id("employeeName")).sendKeys("ноунейм");
        driver.findElement(By.id("employeeEmail")).sendKeys("ноумейл");
        driver.findElement(By.id("employeeEducation")).sendKeys("ноуед");
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        wait = new WebDriverWait(driver, duration);
        Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("employeeBirthDate")));
        Category_Body.sendKeys("22-11-2015");
        addClient = driver.findElement(By.id("submitButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        driver.manage().timeouts().implicitlyWait(duration);

        assertEquals("Информация о сотруднике", driver.getTitle());

        /*
        SERVICE
         */

        driver.get("http://localhost:8080/services");
        assertEquals("Services List", driver.getTitle());
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("home"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        //addClient.click();
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("", driver.getTitle());
        /*
        CONTRACTS
         */

        driver.get("http://localhost:8080/contracts");
        assertEquals("Contract List", driver.getTitle());
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("addButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        //addClient.click();
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Информация о соглашении", driver.getTitle());

        we = driver.findElement(By.id("signDate"));
        we.clear();
        we.sendKeys("11-01-1234");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(duration);

        assertEquals("Contract List", driver.getTitle());
        driver.get("http://localhost:8080/contracts?page=1");
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Contract List", driver.getTitle());
        driver.get("http://localhost:8080/contracts?page=0");
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Contract List", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(duration);
        driver.get("http://localhost:8080/contracts?page=13");
        assertEquals("Ошибка!", driver.getTitle());

        driver.get("http://localhost:8080/contracts");
        assertEquals("Contract List", driver.getTitle());
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("addButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        //addClient.click();
        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Информация о соглашении", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(duration);
        addClient = driver.findElement(By.id("cancelButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        driver.manage().timeouts().implicitlyWait(duration);
        driver.manage().timeouts().implicitlyWait(duration);
        assertEquals("Contract List", driver.getTitle());

        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);

        driver.get("http://localhost:8080/singleClient?id=2");
        assertEquals("Информация о человеке", driver.getTitle());
        WebElement myInfo = driver.findElement(By.id("clientInfo"));
        List<WebElement> cells = myInfo.findElements(By.tagName("p"));

        assertEquals(cells.getFirst().getText(), "Имя клиента: Игорь Юрьевич Харламов");
        driver.get("http://localhost:8080/singleClient?id=103");
        assertEquals("Ошибка!", driver.getTitle());

        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);

        driver.get("http://localhost:8080/singleContract?id=3");
        assertEquals("Информация о соглашении", driver.getTitle());
        myInfo = driver.findElement(By.id("contractInfo"));
        cells = myInfo.findElements(By.tagName("p"));

        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);

        assertEquals(cells.getFirst().getText(), "Клиент: Игорь Юрьевич Харламов");
        driver.get("http://localhost:8080/singleContract?id=103");
        assertEquals("Ошибка!", driver.getTitle());

        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);

        driver.get("http://localhost:8080/singleEmployee?id=3");
        assertEquals("Информация о сотруднике", driver.getTitle());
        myInfo = driver.findElement(By.id("employeeInfo"));
        cells = myInfo.findElements(By.tagName("p"));

        duration = Duration.ofSeconds(2);
        driver.manage().timeouts().implicitlyWait(duration);

        assertEquals(cells.getFirst().getText(), "Имя сотрудника: Джим Керри");
        driver.get("http://localhost:8080/singleclient?id=103");
        assertEquals("Ошибка!", driver.getTitle());

        driver.get("http://localhost:8080/clients");
        driver.findElement(By.id("txtSearch")).sendKeys("рандом");
        addClient = driver.findElement(By.id("searchButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        assertEquals("Client List", driver.getTitle());

        driver.get("http://localhost:8080/employees");
        driver.findElement(By.id("txtSearch")).sendKeys("рандом");
        addClient = driver.findElement(By.id("searchButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        assertEquals("Employee List", driver.getTitle());

        driver.get("http://localhost:8080/contracts");
        driver.findElement(By.id("txtSearch")).sendKeys("1");
        addClient = driver.findElement(By.id("searchButton"));
        actions = new Actions(driver);

        actions.moveToElement(addClient).click().perform();
        assertEquals("Contract List", driver.getTitle());


        driver.quit();
    }
}
