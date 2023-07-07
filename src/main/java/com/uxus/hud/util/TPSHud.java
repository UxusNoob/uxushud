package com.uxus.hud.util;

public class TPSHud {

    public static long lastTick = -1;
    public static long lastUpdate = -1;
    public static double tps = -1;


    public static int updateTime(long ticks) {
        if (lastTick < 0) {
            lastTick = ticks;
            lastUpdate = System.nanoTime();
            return 0;
        }

        long time = System.nanoTime();
        // In nano seconds, so 1000000000 in a second
        // Or 1000000 in a millisecond
        double elapsedMilli = (time - lastUpdate) / 1000000d;
        int passedTicks = (int) (ticks - lastTick);
        if (passedTicks > 0) {
            double mspt = elapsedMilli / passedTicks;

            tps = round(Math.min(1000 / mspt, 20), 2) ;
        }

        lastTick = ticks;
        lastUpdate = time;

        return passedTicks;
    }
//suka dodelaj tps color loshara jebanaja
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }


}


