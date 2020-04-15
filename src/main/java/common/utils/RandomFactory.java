package common.utils;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that generates random values for testing purposes.
 *
 * @author Jamie Martin
 * @author Perdana Bailey
 */
public class RandomFactory {

    /**
     * Creates a random string.
     *
     * @return String: random string sequence.
     */
    public static String String() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Creates a random boolean.
     *
     * @return boolean: true or false.
     */
    public static boolean Boolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Creates a random integer.
     *
     * @param max: the max value to return.
     * @return int: a positive integer between 0 and the supplied max.
     */
    public static int Int(int max) {
        Random random = new Random();
        return Math.abs(random.nextInt(max));
    }

    /**
     * Creates a random array of bytes.
     *
     * @param length: the length of bytes to return.
     * @return byte[]: an array of random bytes.
     */
    public static byte[] Bytes(int length) {
        Random random = new Random();
        byte[] b = new byte[length];
        random.nextBytes(b);
        return b;
    }

    /**
     * Creates a random HEX color as a string.
     *
     * @return String: a random HEX color.
     */
    public static String Color() {
        Random random = new Random();
        int rand_num = random.nextInt(0xffffff + 1);
        return String.format("#%06x", rand_num);
    }

    /**
     * Creates a random timestamp as an Instant.
     *
     * @return Instant: a random time.
     */
    public static Instant Instant() {
        return Instant.ofEpochSecond(Math.abs(ThreadLocalRandom.current().nextInt()));
    }
}