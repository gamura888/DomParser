package com.highpots.DomParser;

import com.highpots.DomParser.util.DomParserHelper;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class DomParserApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        Map<String, Integer> wordCountMap = new HashMap<>();

        wordCountMap = DomParserHelper.parse("https://highpots.com");

        assertNotNull(wordCountMap);

        assertNotEquals(wordCountMap.keySet().size(),0);

        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}
