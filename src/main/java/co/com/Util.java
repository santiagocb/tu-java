package co.com;

import java.util.Random;

public class Util {
    public static double nextDouble(Random r, double origin, double bound) {
        return r.nextDouble() * (bound - origin) + origin;
    }
}
