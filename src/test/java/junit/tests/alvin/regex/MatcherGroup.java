package junit.tests.alvin.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * Author: HaoQiang
 * Date: 2016/7/15
 * Time: 12:56
 *
 * @Copyright (C) 2008-2016 oneapm.com. all rights reserved.
 */
public class MatcherGroup {
    public static void main(String[] args) {
        Pattern p=Pattern.compile("((abc)+)(\\d+)");
        System.out.println(p.pattern());
        Matcher m=p.matcher("abcabc2223abc33");
        System.out.println(m.groupCount());
        while(m.find())
        {
            System.out.println(m.group(1) +":"+ m.group(2));
        }
//        m.find();   //匹配aaa2223
//        System.out.println(m.groupCount());   //返回2,因为有2组
//        m.start(1);   //返回0 返回第一组匹配到的子字符串在字符串中的索引号
//        m.start(2);   //返回3
//        m.end(1);   //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置.
//        m.end(2);   //返回7
//        System.out.println(m.group(1));   //返回aaa,返回第一组匹配到的子字符串
//        System.out.println(m.group(2));   //返回2223,返回第二组匹配到的子字符串
    }
}
