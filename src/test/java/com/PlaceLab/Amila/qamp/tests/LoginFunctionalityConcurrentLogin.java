package com.PlaceLab.Amila.qamp.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//TC 04 - Concurrent Logins - Verify that the system handles concurrent logins from multiple devices or browsers correctly.
public class LoginFunctionalityConcurrentLogin {
    private WebDriver chromeDriver;
    private WebDriver edgeDriver;
    @Parameters("password")
    @Test
    public void testSuccessfulConcurrentLogin(String password) {
        // Open two separate browsers or devices
        chromeDriver = new ChromeDriver();
        edgeDriver = new EdgeDriver();

        // Enter the same valid username and password in both instances
        chromeDriver.get("https://demo.placelab.com");
        chromeDriver.findElement(By.id("email")).sendKeys("amila.gicic@gmail.com");
        chromeDriver.findElement(By.id("password")).sendKeys(password);
        edgeDriver.get("https://demo.placelab.com");
        edgeDriver.findElement(By.id("email")).sendKeys("amila.gicic@gmail.com");
        edgeDriver.findElement(By.id("password")).sendKeys(password);

        // Click on the login button in both browser
        Thread t1 = new Thread(() -> {
            chromeDriver.findElement(By.xpath("//input[@type='submit']")).click();
        });
        Thread t2 = new Thread(() -> {
            edgeDriver.findElement(By.xpath("//input[@type='submit']")).click();
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that both instances are either allowed or denied access based on the system's concurrent login policy
        String expectedUrl = "https://demo.placelab.com/dashboard/traffic";
        String actualUrl1 = chromeDriver.getCurrentUrl();
        String actualUrl2 = edgeDriver.getCurrentUrl();
        if ((actualUrl1.equals(expectedUrl) && actualUrl2.equals(expectedUrl))
                || (!actualUrl1.equals(expectedUrl) && !actualUrl2.equals(expectedUrl))) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed!");
        }
        // Close the browsers
        chromeDriver.quit();
        edgeDriver.quit();
    }
        }



