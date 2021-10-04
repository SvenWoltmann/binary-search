package eu.happycoders.binarysearch.common;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Array utilities.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
@SuppressWarnings("squid:S2245") // We don't need secure random numbers for wait time
@SuppressFBWarnings("PREDICTABLE_RANDOM") // We don't need secure random numbers for wait time
public final class ArrayUtils {

  private static final int NUM_INCREMENTS = 16;

  private ArrayUtils() {}

  /**
   * Creates a sorted array with random integers.
   *
   * <p>The simple way would be to fill an array with random values, then sort it. But that would
   * take very long compared to the binary search.
   *
   * <p>The way we're going here is to fill the array with values repeatedly incremented by a short
   * random sequence of increments.
   *
   * @param size the number of elements
   * @return the array
   */
  public static int[] createSortedArray(int size) {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    int[] array = new int[size];

    if (size == 1) {
      array[0] = random.nextInt();
    } else if (size != 0) {
      int maxIncrement = (int) (4_294_967_295L / size);

      int[] increments = new int[NUM_INCREMENTS];
      for (int i = 0; i < NUM_INCREMENTS; i++) {
        increments[i] = random.nextInt(1, maxIncrement);
      }

      int val = Integer.MIN_VALUE;
      for (int i = 0; i < size; i++) {
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
  @SuppressWarnings("PMD.AvoidArrayLoops") // false positive
  public static int[] pickRandomKeys(int[] array, int numKeys) {
    int[] keys = new int[numKeys];
    int arraySize = array.length;
    if (arraySize != 0) {
      ThreadLocalRandom random = ThreadLocalRandom.current();
      for (int i = 0; i < numKeys; i++) {
        int keyPos = random.nextInt(0, arraySize);
        keys[i] = array[keyPos];
      }
    }
    return keys;
  }
}
