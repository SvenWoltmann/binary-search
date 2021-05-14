package eu.happycoders.binary_search;

import eu.happycoders.binary_search.common.ArrayUtils;
import eu.happycoders.binary_search.common.Statistics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Measures the runtime of binary search for various sizes of int arrays.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BinarySearchRuntime {

  private static final int MAX_WARMUPS = 10;
  private static final int MAX_ITERATIONS = 100;

  private static final int BATCH_SIZE = 100;

  private static final int[] ARRAY_SIZES =
      new int[] {
        0,
        10_000,
        20_000,
        50_000,
        100_000,
        200_000,
        500_000,
        1_000_000,
        1_500_000,
        2_000_000,
        3_000_000,
        4_000_000,
        6_000_000,
        8_000_000,
        10_000_000,
        15_000_000,
        20_000_000,
        30_000_000,
        40_000_000,
        60_000_000,
        80_000_000,
        100_000_000,
        150_000_000,
        200_000_000
      };

  private static final Map<Integer, List<Long>> TIMES = new HashMap<>();

  private static long globalBlackhole = 0;

  public static void main(String[] args) {
    for (int i = 0; i < MAX_WARMUPS; i++) {
      runTests(i, true);
    }
    for (int i = 0; i < MAX_ITERATIONS; i++) {
      runTests(i, false);
    }
  }

  private static void runTests(int iteration, boolean warmup) {
    System.out.printf("%n%sIteration %d:%n", warmup ? "Warmup - " : "Test - ", iteration + 1);

    for (int arraySize : ARRAY_SIZES) {
      runSingleTest(iteration, warmup, arraySize);
    }

    System.out.println("globalBlackhole = " + globalBlackhole);
  }

  private static void runSingleTest(int iteration, boolean warmup, int n) {
    System.gc();

    int[] array = ArrayUtils.createSortedArray(n);
    int[] keys = ArrayUtils.pickRandomKeys(array, BATCH_SIZE);

    long localBlackhole = 0;
    long time = System.nanoTime();
    for (int i = 0; i < BATCH_SIZE; i++) {
      localBlackhole += BinarySearch.binarySearchIteratively(array, keys[i]);
    }
    time = (System.nanoTime() - time) / BATCH_SIZE;
    globalBlackhole += localBlackhole;

    System.out.printf(Locale.US, "Time for array with %,11d elements: %,6d ns", n, time);

    if (!warmup) {
      List<Long> times = TIMES.computeIfAbsent(n, k -> new ArrayList<>());
      times.add(time);
      long median = Statistics.median(times);
      System.out.printf(
          Locale.US, "  -->  Median after %2d iterations = %,6d ns", iteration + 1, median);
    }
    System.out.println();
  }
}
