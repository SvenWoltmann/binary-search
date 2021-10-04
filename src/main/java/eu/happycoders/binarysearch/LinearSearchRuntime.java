package eu.happycoders.binarysearch;

import eu.happycoders.binarysearch.common.ArrayUtils;
import eu.happycoders.binarysearch.common.Statistics;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Measures the runtime of binary search for various sizes of int arrays.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
// Ignore these warnings - this is just a test program
@SuppressWarnings({"squid:S106", "PMD.SystemPrintln", "PMD.UseUtilityClass"})
public class LinearSearchRuntime {

  private static final int MAX_WARMUPS = 10;
  private static final int MAX_ITERATIONS = 100;

  private static final int BATCH_SIZE = 5;

  private static final int[] ARRAY_SIZES = {
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

  private static final Map<Integer, List<Long>> TIMES = new ConcurrentHashMap<>();

  private static long globalBlackhole;

  /**
   * Tests the speed of the {@link LinearSearch} implementation.
   *
   * @param args program arguments are ignored
   */
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

  // Let's try to run the GC *before* the test (no guarantee that it works)
  @SuppressWarnings({"squid:S1215", "PMD.DoNotCallGarbageCollectionExplicitly"})
  private static void runSingleTest(int iteration, boolean warmup, int arraySize) {
    System.gc();

    int[] array = ArrayUtils.createSortedArray(arraySize);
    int[] keys = ArrayUtils.pickRandomKeys(array, BATCH_SIZE);

    long localBlackhole = 0;
    long time = System.nanoTime();
    for (int i = 0; i < BATCH_SIZE; i++) {
      localBlackhole += LinearSearch.search(array, keys[i]);
    }
    time = (System.nanoTime() - time) / BATCH_SIZE;
    globalBlackhole += localBlackhole;

    System.out.printf(Locale.US, "Time for array with %,11d elements: %,11d ns", arraySize, time);

    if (!warmup) {
      List<Long> times = TIMES.computeIfAbsent(arraySize, k -> new ArrayList<>());
      times.add(time);
      long median = Statistics.median(times);
      System.out.printf(
          Locale.US, "  -->  Median after %2d iterations = %,11d ns", iteration + 1, median);
    }
    System.out.println();
  }
}
