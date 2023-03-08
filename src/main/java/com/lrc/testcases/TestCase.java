package com.lrc.testcases;

import com.lrc.common.BaseDriver;
import com.lrc.page.BingPage;
import com.lrc.utils.Until;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TestCase extends BaseDriver {
    @Test
    public void test() {
        BingPage bingPage = new BingPage(driver);
        bingPage.sendKey("TOM");
        bingPage.clickSearch();
        bingPage.clickPageTwo();
        List<String> title = bingPage.getTitle();
        List<String> link = bingPage.getLink();
        Map<String, String> map = IntStream.range(0, title.size()).collect(HashMap::new, (m, i) -> m.put(title.get(i), link.get(i)), (m, n) -> {
        });
        System.out.println("=====================结果=======================");
        map.keySet().forEach(s -> {
            System.out.println(s + "-->" + map.get(s));
        });
        List<String> domain = new ArrayList<>();
        for (String s : link) {
            String s1 = Until.urlTest(s);
            domain.add(s1);
        }
        System.out.println("===============统计==================");
        Map<String, Long> numbers = Until.getNumbers(domain);
        numbers.keySet().forEach(s -> {
            System.out.println(s + "-->" + numbers.get(s));
        });

    }
}
