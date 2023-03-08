package com.lrc.page;

import com.lrc.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BingPage extends BasePage {
    WebDriver driver;

    public BingPage(WebDriver driver) {
        this.driver = driver;
    }

    private By serchInput = By.id("sb_form_q");
    private By serchBt = By.id("search_icon");

    private By pageTwo = By.xpath("//a[contains(@class,'b_widePag sb_bp') and text()='2']");

    private By xpath = By.xpath("//ol[@id='b_results']//h2/a");

    public void sendKey(String name) {
        sendKey(driver, serchInput, name, "搜索框中输入");
    }

    public void clickSearch() {
        click(driver, serchBt, "点击搜索图标");
    }

    public void clickPageTwo() {
        scrollElement(driver, pageTwo);
        click(driver, pageTwo, "点击第二页");
    }

    public List<String> getTitle() {
        List<String> list = new ArrayList<>();
        List<WebElement> elements = driver.findElements(xpath);
        for (WebElement element : elements) {
            list.add(element.getText());
        }
        return list;
    }

    public List<String> getLink() {
        List<String> list = new ArrayList<>();
        List<WebElement> elements = driver.findElements(xpath);
        for (WebElement element : elements) {
            list.add(element.getAttribute("href"));
        }
        return list;
    }
}
