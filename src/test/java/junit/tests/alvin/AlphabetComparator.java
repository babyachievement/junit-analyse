package junit.tests.alvin;

import org.junit.runner.Description;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * Author: HaoQiang
 * Date: 2016/7/13
 * Time: 18:31
 *
 * @Copyright (C) 2008-2016 oneapm.com. all rights reserved.
 */
public class AlphabetComparator implements Comparator<Description> {
    @Override
    public int compare(Description o1, Description o2) {
        return o1.getMethodName().compareTo(o2.getMethodName());
    }
}
