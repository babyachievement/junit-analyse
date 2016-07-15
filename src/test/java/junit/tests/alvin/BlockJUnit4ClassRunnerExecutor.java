package junit.tests.alvin;

import org.junit.internal.runners.ErrorReportingRunner;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.manipulation.Sorter;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * Created by IntelliJ IDEA.
 * Author: HaoQiang
 * Date: 2016/7/13
 * Time: 18:32
 *
 * @Copyright (C) 2008-2016 oneapm.com. all rights reserved.
 */
public class BlockJUnit4ClassRunnerExecutor {
    public static void main(String[] args) {
        RunNotifier notifier = new RunNotifier();
        Result result = new Result();
        notifier.addFirstListener(result.createListener());

        Runner runner = null;
        try {
            runner = new BlockJUnit4ClassRunner(CoreJUnit4SampleTest.class);
            try {
                ((BlockJUnit4ClassRunner)runner).filter(new MethodNameFilter("testFilteredOut"));
            } catch (NoTestsRemainException e) {
                System.out.println("All methods are been filtered out");
                return;
            }
            ((BlockJUnit4ClassRunner)runner).sort(new Sorter(new AlphabetComparator()));
        } catch (InitializationError initializationError) {
            runner = new ErrorReportingRunner(CoreJUnit4SampleTest.class, initializationError);
        }

        notifier.fireTestRunStarted(runner.getDescription());
        runner.run(notifier);
    }
}
