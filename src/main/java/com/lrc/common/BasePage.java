package com.lrc.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @param
 * @return
 * @description 封装页面层的公用方法
 **/
public class BasePage {
    private static Logger logger = Logger.getLogger(BasePage.class);

    /**
     * 显式等待元素可见二次封装
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     */
    public WebElement waitElementVisible(WebDriver driver, By by) {
        WebElement webElement = null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("定位元素【" + by + "】异常");
        }
        return webElement;
    }

    /**
     * 判断元素是否存在封装
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     */
    public boolean exisWaitElementVisible(WebDriver driver, By by) {
        WebElement webElement = null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            logger.error("定位元素【" + by + "】异常");
            return false;
        }
    }

    /**
     * 判断元素是否存在封装
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     */
    public boolean exisWaitElementVisible(WebDriver driver, By by, int times) {
        WebElement webElement = null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, times);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            logger.error("定位元素【" + by + "】异常");
            return false;
        }
    }

    /**
     * 显式等待元素可见二次封装
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     */
    public WebElement waitElementVisible(WebDriver driver, By by, int times) {
        WebElement webElement = null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, times);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("定位元素【" + by + "】异常");
        }

        return webElement;
    }

    /**
     * 显式等待元素可被点击二次封装
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     * @param times  元素等待时间
     */
    public WebElement waitElementClickable(WebDriver driver, By by, int times) {
        WebElement webElement = null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, times);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            logger.error("定位元素【" + by + "】异常");
        }
        return webElement;
    }

    /**
     * 显式等待元素消失
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     */
    public boolean waitElementNoVisibel(WebDriver driver, By by) {
        Boolean webElement = null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("元素【" + by + "】存在");
        }
        return webElement;
    }

    /**
     * 显式等待元素消失
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     */
    public boolean waitElementNoVisibel(WebDriver driver, By by, int time) {
        Boolean webElement = null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, time);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("元素【" + by + "】存在");
        }
        return webElement;
    }

    /**
     * 显式等待元素内容消失
     *
     * @param driver    驱动对象
     * @param by        元素定位信息
     * @param text      元素内容
     * @param waitTimes 等待时间（秒）
     */
    public boolean waitIElementNoWithText(WebDriver driver, By by, String text, int waitTimes) {
        Boolean webElement = null;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, waitTimes);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
        } catch (Exception e) {
            logger.error("元素【" + by + "】存在");
        }
        return webElement != null;
    }

    /**
     * Alert弹窗切换
     *
     * @param driver 驱动对象
     */
    public String switchAlertText(WebDriver driver) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        logger.info("切换到alert窗口");
        Alert alert = driver.switchTo().alert();
//        alert.accept();  //点击确定
        //alert.dismiss(); //点击取消
        String returnValue = alert.getText();  //获取弹窗文本
        return returnValue;
    }

    /**
     * 输入框输入数据通用方法
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     * @param data   输入的数据
     */
    public void sendKey(WebDriver driver, By by, String data, String elementName) {
        logger.info("往元素【" + elementName + "】输入数据【" + data + "】");
        waitElementVisible(driver, by).sendKeys(data);
    }

    /**
     * 点击操作的通用方法
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     */
    public void click(WebDriver driver, By by, String elementName) {
        logger.info("对元素【" + elementName + "】进行点击");
        waitElementClickable(driver, by, 20).click();
    }

    /**
     * 点击操作的通用方法
     *
     * @param driver 驱动对象
     * @param by     元素定位信息
     */
    public void click(WebDriver driver, By by, String elementName, int times) {
        logger.info("对元素【" + elementName + "】进行点击");
        waitElementClickable(driver, by, times).click();
    }

    /**
     * 判断元素文本value值
     *
     * @param driver    驱动对象
     * @param by        元素定位信息
     * @param text      文本内容
     * @param waitTimes 等待时间（秒）
     * @return
     */
    public boolean textToBePresentInElementValue(WebDriver driver, By by, String text, int waitTimes) {
        Boolean webElement = null;
        try {
            //1、实例化WebDriverWait
            WebDriverWait webDriverWait = new WebDriverWait(driver, waitTimes);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.textToBePresentInElementValue(by, text));
        } catch (Exception e) {
            logger.error("元素【" + by + "】的文本存在【" + text + "】");
        }
        return webElement;
    }

    /**
     * 等待元素的text内容出现
     *
     * @param driver    驱动对象
     * @param by        元素定位信息
     * @param text      文本内容
     * @param waitTimes 等待时间（秒）
     * @return
     */
    public boolean textToBePresentInElement(WebDriver driver, By by, String text, int waitTimes) {
        Boolean webElement = null;
        try {
            //1、实例化WebDriverWait
            WebDriverWait webDriverWait = new WebDriverWait(driver, waitTimes);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
        } catch (Exception e) {
            logger.error("元素【" + by + "】的文本存在【" + text + "】");
        }
        return webElement;
    }

    /**
     * 获取元素文本方法封装
     *
     * @param driver      驱动对象
     * @param by          元素定位信息
     * @param elementName 元素名称
     * @return
     */
    public String getText(WebDriver driver, By by, String elementName) {
        String text = waitElementVisible(driver, by).getText();
        logger.info("获取元素【" + elementName + "】文本【" + text + "】");
        return text;
    }

    /**
     * 获取元素文本方法封装
     *
     * @param driver      驱动对象
     * @param by          元素定位信息
     * @param elementName 元素名称
     * @return
     */
    public String getText(WebDriver driver, By by, String elementName, int times) {
        String text = waitElementVisible(driver, by, times).getText();
        logger.info("获取元素【" + elementName + "】文本【" + text + "】");
        return text;
    }

    /**
     * 获取元素属性方法封装
     *
     * @param driver         驱动对象
     * @param by             元素定位信息
     * @param attributeValue 属性名
     * @param elementName    元素名称
     * @return
     */
    public String getAttribute(WebDriver driver, By by, String attributeValue, String elementName) {
        String text = waitElementVisible(driver, by).getAttribute(attributeValue);
        logger.info("获取元素【" + elementName + "】属性" + elementName + "的值【" + text + "】");
        return text;
    }

    /**
     * 切换到指定IFrame封装
     *
     * @param driver    驱动对象
     * @param by        元素定位信息
     * @param frameInfo 自定义frame信息
     */
    public void switchFrame(WebDriver driver, By by, String frameInfo) {
        WebElement element = waitElementVisible(driver, by);
        logger.info("切换IFrame:" + frameInfo);
        driver.switchTo().frame(element);
    }

    /**
     * 从IFrame中切换到默认页面封装
     *
     * @param driver 驱动对象
     */
    public void switchDefaultFrame(WebDriver driver) {
        logger.info("切换回默认的页面");
        driver.switchTo().defaultContent();
    }

    /**
     * Alert弹窗切换
     *
     * @param driver 驱动对象
     */
    public void switchAlert(WebDriver driver) {
        logger.info("切换到alert窗口");
        Alert alert = driver.switchTo().alert();
        alert.accept();  //点击确定
    }

    /**
     * 鼠标聚焦到指定元素的通用方法
     *
     * @param driver      驱动对象
     * @param by          元素单位信息
     * @param elementName 元素名称
     */
    public void moveToElement(WebDriver driver, By by, String elementName) {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitElementVisible(driver, by)).perform();
        logger.info("鼠标移动到元素【" + elementName + "】上了");
    }

    /**
     * 鼠标聚焦到指定元素的通用方法
     *
     * @param driver 驱动对象
     * @param by     元素单位信息
     */
    public void enterElement(WebDriver driver, By by) {
        WebElement element = waitElementVisible(driver, by);
        element.sendKeys(Keys.ENTER);
        logger.info("键盘回车");
    }

    /**
     * 页面滚动到页面底部的通用方法
     *
     * @param driver 驱动对象
     * @param by     元素单位信息
     */
    public void scrollToPageDownButtom(WebDriver driver, By by) {
        WebElement element = waitElementVisible(driver, by);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        logger.info("滚动到页面底部");
    }

    /**
     * 页面滚动到页面顶部的通用方法
     *
     * @param driver 驱动对象
     * @param by     元素单位信息
     */
    public void scrollToPageUpButtom(WebDriver driver, By by) {
        WebElement element = waitElementVisible(driver, by);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        logger.info("滚动到页面顶部");
    }

    /**
     * 页面滚动到指定元素的通用方法
     *
     * @param driver 驱动对象
     * @param by     元素单位信息
     */
    public void scrollElement(WebDriver driver, By by) {
        WebElement element = waitElementVisible(driver, by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        logger.info("滚动到指定元素");
    }

    /**
     * 页面滚动到指定坐标的通用方法
     *
     * @param driver 驱动对象
     * @param x      x坐标
     * @param y      y坐标
     */
    public void scrollToCoordinates(WebDriver driver, int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")");
        logger.info("滚动到指定坐标");
    }

    /**
     * 输入框输入数据通用方法
     *
     * @param driver      驱动对象
     * @param by          元素定位信息
     * @param elementName 元素名称
     */
    public boolean isSelectedElement(WebDriver driver, By by, String elementName) {
        WebElement element = waitElementVisible(driver, by);
        boolean isSelected = element.isSelected();
        logger.info("判断" + elementName + "是否被选中");
        return isSelected;

    }

    /**
     * 清除数据通用方法
     *
     * @param driver      驱动对象
     * @param by          元素定位信息
     * @param elementName 元素名称
     */
    public void clearElement(WebDriver driver, By by, String elementName) {
        WebElement element = waitElementVisible(driver, by);
        element.clear();
        logger.info("清除" + elementName + "元素内容");
    }

    /**
     * js点击通用方法
     *
     * @param driver      驱动对象
     * @param by          元素定位信息
     * @param elementName 元素名称
     */
    public void jsClick(WebDriver driver, By by, String elementName) {
        WebElement element = waitElementVisible(driver, by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        logger.info("js点击" + elementName + "元素");
    }
    /**
     * js获取选中框的状态
     * @param driver      驱动对象
     * @param by          元素定位信息
     * @param elementName 元素名称
     */
    public boolean jsCheckboxSelected(WebDriver driver, By by, String elementName) {
        WebElement element = waitElementVisible(driver, by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        logger.info("获取" + elementName + "元素状态为"+js.executeScript("return arguments[0].checked;", element));
        return  (boolean)js.executeScript("return arguments[0].checked;", element);

    }
    /**
     * 等待元素属性的值出现
     *
     * @param driver    驱动对象
     * @param by        元素定位信息
     * @param attribute 元素属性名称
     * @param value     元素属性值
     * @param seconds   等待时间（秒）
     */
    public boolean attributeContains(WebDriver driver, By by, String attribute, String value, int seconds) {
        boolean webElement = false;
        try {
            //1、实例化WebDriverWait 超时时间10s
            WebDriverWait webDriverWait = new WebDriverWait(driver, seconds);
            //2、通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.attributeContains(by, attribute, value));
        } catch (Exception e) {
            logger.error("元素【" + by + "】的属性获取异常");
        }
        return webElement;
    }
}
