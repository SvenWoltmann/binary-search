package eu.happycoders.binary_search;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BinarySearchTest {

  private static final int NUMBER_OF_SORTED_ARRAYS = 50;
  private static final int MIN_LENGTH_OF_ARRAY = 1;
  private static final int MAX_LENGTH_OF_ARRAY = 100;

  private static List<Arguments> getSortedArrays() {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    List<Arguments> list = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_SORTED_ARRAYS; i++) {
      int arrayLength = random.nextInt(MIN_LENGTH_OF_ARRAY, MAX_LENGTH_OF_ARRAY + 1);
      int[] array = new int[arrayLength];
      for (int j = 0; j < arrayLength; j++) {
        array[j] = random.nextInt(-1_000_000, 1_000_000);
      }
      Arrays.sort(array);
      list.add(Arguments.of(array));
    }
    return list;
  }

  @ParameterizedTest
  @MethodSource("getSortedArrays")
  void binarySearchRecursively_arrayAndKeyContainedInArray_returnsIndexOfKey(int[] array) {
    // Get key from a random position
    int randomIndex = ThreadLocalRandom.current().nextInt(0, array.length);
    int randomKey = array[randomIndex];

    int index = BinarySearch.binarySearchRecursively(array, randomKey);

    // Don't assert that index == randomIndex.
    // That's not necessarily the case if random key exists more than once in the array.
    assertThat(array[index], is(randomKey));
  }

  @ParameterizedTest
  @MethodSource("getSortedArrays")
  void binarySearchRecursively_arrayAndKeyNotContainedInArray_returnsMinus1(int[] array) {
    int index = BinarySearch.binarySearchRecursively(array, 2_000_000);
    assertThat(index, is(-1));
  }

  @Test
  void binarySearchRecursively_emptyArray_returnsMinus1() {
    int[] array = new int[0];
    int index = BinarySearch.binarySearchRecursively(array, 42);
    assertThat(index, is(-1));
  }

  @ParameterizedTest
  @MethodSource("getSortedArrays")
  void binarySearchIteratively_arrayAndKeyContainedInArray_returnsIndexOfKey(int[] array) {
    // Get key from a random position
    int randomIndex = ThreadLocalRandom.current().nextInt(0, array.length);
    int randomKey = array[randomIndex];

    int index = BinarySearch.binarySearchIteratively(array, randomKey);

    // Don't assert that index == randomIndex.
    // That's not necessarily the case if random key exists more than once in the array.
    assertThat(array[index], is(randomKey));
  }

  @ParameterizedTest
  @MethodSource("getSortedArrays")
  void binarySearchIteratively_arrayAndKeyNotContainedInArray_returnsMinus1(int[] array) {
    int index = BinarySearch.binarySearchIteratively(array, 2_000_000);
    assertThat(index, is(-1));
  }

  @Test
  void binarySearchIteratively_emptyArray_returnsMinus1() {
    int[] array = new int[0];
    int index = BinarySearch.binarySearchIteratively(array, 42);
    assertThat(index, is(-1));
  }
}
