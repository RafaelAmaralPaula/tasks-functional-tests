package br.ce.wc.aquibo.tasks.functionaltest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    private WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://192.168.100.112:4444/wd/hub") , new ChromeOptions());
        driver.navigate().to("http://192.168.100.112:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
    }

    @Test
    public void deveSalvarTarefaComDataAtual(){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        driver.findElement(By.id("addTodo")).click();

        driver.findElement(By.id("task")).sendKeys("Teste com selenium two");

        driver.findElement(By.id("dueDate")).sendKeys(date);

        driver.findElement(By.id("saveButton")).click();

        String mensagem = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Sucesso BRAZIL!" , mensagem);

        driver.quit();


    }

    @Test
    public void deveSalvarTarefaComSucesso(){
        driver.findElement(By.id("addTodo")).click();

        driver.findElement(By.id("task")).sendKeys("Teste via selinium");

        driver.findElement(By.id("dueDate")).sendKeys("30/12/2022");

        driver.findElement(By.id("saveButton")).click();

        String mensagem = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Sucesso BRAZIL!" , mensagem);

        driver.quit();
    }



    @Test
    public void naoDeveSalvarTarefaComDataNoPassado(){
        driver.findElement(By.id("addTodo")).click();

        driver.findElement(By.id("task")).sendKeys("Error");

        driver.findElement(By.id("dueDate")).sendKeys("30/10/2022");

        driver.findElement(By.id("saveButton")).click();

        String mensagem = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Due date must not be in past" , mensagem);

        driver.quit();
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao(){
        driver.findElement(By.id("addTodo")).click();

        driver.findElement(By.id("task")).sendKeys("");

        driver.findElement(By.id("dueDate")).sendKeys("31/12/2022");

        driver.findElement(By.id("saveButton")).click();

        String mensagem = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the task description" , mensagem);

        driver.quit();
    }

    @Test
    public void naoDeveSalvarTarefaSemData(){
        driver.findElement(By.id("addTodo")).click();

        driver.findElement(By.id("task")).sendKeys("Teste selenium");

        driver.findElement(By.id("dueDate")).sendKeys("");

        driver.findElement(By.id("saveButton")).click();

        String mensagem = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the due date" , mensagem);

        driver.quit();
    }
}
