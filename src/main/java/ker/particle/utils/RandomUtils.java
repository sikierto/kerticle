package ker.particle.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kierpagdato on 6/23/17.
 */
public final class RandomUtils {

    private Random random = new Random();



    public static int generateRandom(int max){
        return ThreadLocalRandom.current().nextInt(0, max + 1);
    }

    public static int generateRandom(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static double generateRandom(double max){
        return ThreadLocalRandom.current().nextDouble(0, max + 1);
    }

    public static double generateRandom(double min, double max){
        return ThreadLocalRandom.current().nextDouble(min, max + 1);
    }
}
