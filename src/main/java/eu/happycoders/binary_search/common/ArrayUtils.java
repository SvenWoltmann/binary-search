package eu.happycoders.binary_search.common;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Array utilities.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class ArrayUtils {

  private static final int NUM_INCREMENTS = 16;

  /**
   * Creates a sorted array with random integers.
   *
   * <p>The simple way would be to fill an array with random values, then sort it. But that would
   * take very long compared to the binary search.
   *
   * <p>The way we're going here is to fill the array with values repeatedly incremented by a short
   * random sequence of increments.
   *
   * @param n the number of elements
   * @return the array
   */
  public static int[] createSortedArray(int n) {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    int[] array = new int[n];

    if (n == 1) {
      array[0] = random.nextInt();
    } else if (n != 0) {
      int maxIncrement = (int) (4_294_967_295L / n);

      int[] increments = new int[NUM_INCREMENTS];
      for (int i = 0; i < NUM_INCREMENTS; i++) {
        increments[i] = random.nextInt(1, maxIncrement);
      }

      int val = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
        array[i] = val;
        val += increments[i % NUM_INCREMENTS];
      }
    }

    return array;
  }

  /**
   * Creates an array of randomly picked keys from the given array.
   *
   * @param array the array to pick keys from
   * @param numKeys the number of keys to pick
   */
  public static int[] pickRandomKeys(int[] array, int numKeys) {
    int keys[] = new int[numKeys];
    int n = array.length;
    if (n != 0) {
      ThreadLocalRandom random = ThreadLocalRandom.current();
      for (int i = 0; i < numKeys; i++) {
        int keyPos = random.nextInt(0, n);
        keys[i] = array[keyPos];
      }
    }
    return keys;
  }
}
