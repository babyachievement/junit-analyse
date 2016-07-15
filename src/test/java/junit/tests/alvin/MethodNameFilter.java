package junit.tests.alvin;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Author: HaoQiang
 * Date: 2016/7/13
 * Time: 18:27
 *
 * @Copyright (C) 2008-2016 oneapm.com. all rights reserved.
 */
public class MethodNameFilter extends Filter {
    private final Set<String> excludedMethods = new HashSet<String>();

    public MethodNameFilter(String... excludedMethods) {
        for(String method: excludedMethods) {
            this.excludedMethods.add(method);
        }
    }

    @Override
    public boolean shouldRun(Description description) {
        String methodName = description.getMethodName();
        if(excludedMethods.contains(methodName)){
            return  false;
        }
        return true;
    }

    @Override
    public String describe() {
        return this.getClass().getSimpleName() + "-excluded methods: " + this.excludedMethods;
    }
}
