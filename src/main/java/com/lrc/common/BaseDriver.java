package com.lrc.common;

import com.lrc.utils.PropertyReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @param
 * @return
 * @description 封装用例层的公用方法
 **/
public class BaseDriver {
    private static Logger logger = Logger.getLogger(BaseDriver.class);
    public RemoteWebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setup() {
        //用例前置
        //1、打开浏览器
        openBrowser(PropertyReader.getProperty("driver.BROWSER"));
        maxBrowser();
        //2、进入登录页面
        toURL(PropertyReader.getProperty("driver.INDEX_URL"));
    }

    @AfterTest(alwaysRun = true)
    public void teardown() {
        //用例后置
        //退出浏览器
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quitBrowser();
    }

    /**
     * 打开所有浏览器通用方法封装
     *
     * @param browserName 浏览器名
     */
    public void openBrowser(String browserName) {
        RemoteWebDriver webDriver = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        chrome.setCapability(ChromeOptions.CAPABILITY, options);
        chrome.setCapability("acceptInsecureCerts", true);
        webDriver = new ChromeDriver(chrome);
        driver = webDriver;
    }

    /**
     * 关闭浏览器通用方法
     */
    public void closeBrowser() {
        logger.info("====================关闭浏览器=====================");
        driver.close();
    }

    /**
     * 退出浏览器通用方法
     */
    public void quitBrowser() {
        logger.info("====================退出浏览器=====================");
        driver.quit();
    }


    /**
     * 最大化浏览器
     */
    public void maxBrowser() {
        logger.info("================最大化浏览器===================");
        driver.manage().window().maximize();
    }

    /**
     * 访问指定网址
     *
     * @param url 访问地址
     */
    public void toURL(String url) {
        logger.info("================访问网址：===================" + url);
        driver.get(url);
    }


    /**
     * 封装的通用切换窗口的方法-根据对应窗口的标题来切换
     *
     * @param title 窗口标题
     */
    public void switchWindowWithTitle(String title) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            //根据窗口的标题来进行判断
            if (title.equals(driver.getTitle())) {
                break;
            } else {
                logger.info("切换到标题为：【" + title + "】的窗口");
                driver.switchTo().window(windowHandle);
            }
        }
    }

    /**
     * 封装的通用切换窗口的方法-根据对应窗口的url来切换
     *
     * @param url 窗口url
     */
    public void switchWindowWithURL(String url) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            //根据窗口的URL来进行判断
            if (url.equals(driver.getCurrentUrl())) {
                break;
            } else {
                logger.info("切换到url为:【" + url + "】的窗口");
                driver.switchTo().window(windowHandle);
            }
        }
    }

    /**
     * 关闭其他窗口，只保留第一个打开的窗口
     */
    public void closeOhtersWindows() {
        try {
            Set<String> winHandles = driver.getWindowHandles();//使用set集合获取所有窗口句柄
            List<String> list = new ArrayList(winHandles);
            String name = list.get(0);
            Iterator<String> it = winHandles.iterator();//创建迭代器，迭代winHandles里的句柄
            while (it.hasNext()) {//用it.hasNext()判断时候有下一个窗口,如果有就切换到下一个窗口
                String win = it.next();//获取集合中的元素
                if (!win.equals(name)) { //如果此窗口不是关闭前的窗口
                    driver.switchTo().window(win);//切换到新窗口
                    logger.info("关闭url为:【" + driver.getCurrentUrl() + "】的窗口");
                    driver.close();//关闭窗口
                }
            }
            driver.switchTo().window(name);
            logger.info("切换到url为:【" + driver.getCurrentUrl() + "】的窗口");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void myAssertTrue(boolean condition, String assertDescription) {
        logger.info("断言：【" + assertDescription + "】条件表达式【" + condition + "】");
        Assert.assertTrue(condition);
    }

    public void myAssertEquals(String actual, String expected, String assertDescription) {
        logger.info("断言：【" + assertDescription + "】实际值【" + actual + "】期望值【" + expected + "】");
        Assert.assertEquals(actual, expected);

    }

    public void myAssertEquals(int actual, int expected, String assertDescription) {
        logger.info("断言：【" + assertDescription + "】实际值【" + actual + "】期望值【" + expected + "】");
        Assert.assertEquals(actual, expected);
    }

    public void myAssertEquals(double actual, double expected, String assertDescription) {
        logger.info("断言：【" + assertDescription + "】实际值【" + actual + "】期望值【" + expected + "】");
        Assert.assertEquals(actual, expected);
    }

    public void myAssertEquals(float actual, float expected, String assertDescription) {
        logger.info("断言：【" + assertDescription + "】实际值【" + actual + "】期望值【" + expected + "】");
        Assert.assertEquals(actual, expected);
    }

    public void myAssertEquals(Object actual, Object expected, String assertDescription) {
        logger.info("断言：【" + assertDescription + "】实际值【" + actual + "】期望值【" + expected + "】");
        Assert.assertEquals(actual, expected);
    }
}
