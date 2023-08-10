package com.example.sport_api.util;

import org.apache.commons.lang3.time.StopWatch;

public class TimeMeasurementUtil {
    public static StopWatch stopWatch = new StopWatch();

    public static void startTimer() {
        stopWatchReset();
        stopWatch.start();
    }

    public static void timeTaken() {
        stopWatch.stop();
        System.out.println("Total Time Taken : " + stopWatch.getTime());
    }

    public static void stopWatchReset() {
        stopWatch.reset();
    }
}
