package eu.happycoders.binarysearch;

/**
 * Implementation of the Binary Search algorithm for int arrays - recursively and iteratively.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public final class BinarySearch {

  private BinarySearch() {}

  /**
   * Searches the given key in the given array applying a recursive binary search.
   *
   * @param array the array in which to search the key
   * @param key the key to search
   * @return the position of the key in the array; or -1 if the key doesn't exist in the array
   */
  public static int binarySearchRecursively(int[] array, int key) {
    return binarySearchRecursively(array, 0, array.length - 1, key);
  }

  /**
   * Searches the given key in the specified range of the given array applying a recursive binary
   * search.
   *
   * @param array the array in which to search the key
   * @param low the first index (inclusive) of the range to search
   * @param high the last index (inclusive) of the range to search
   * @param key the key to search
   * @return the position of the key in the array; or -1 if the key doesn't exist in the array
   */
  public static int binarySearchRecursively(int[] array, int low, int high, int key) {
    if (high < low) {
      return -1;
    }

    // "(low + high) / 2" won't work if the sum is higher than Integer.MAX_VALUE.
    // That would cause an overflow / roll over, and result in a negative value!
    int mid = (low + high) >>> 1;
    int midVal = array[mid];

    if (key == midVal) {
      return mid;
    } else if (key < midVal) {
      return binarySearchRecursively(array, low, mid - 1, key);
    } else {
      return binarySearchRecursively(array, mid + 1, high, key);
    }
  }

  /**
   * Searches the given key in the given array applying an iterative binary search.
   *
   * @param array the array in which to search the key
   * @param key the key to search
   * @return the position of the key in the array; or -1 if the key doesn't exist in the array
   */
  public static int binarySearchIteratively(int[] array, int key) {
    return binarySearchIteratively(array, 0, array.length, key);
  }

  /**
   * Searches the given key in the specified range of the given array applying an iterative binary
   * search.
   *
   * @param array the array in which to search the key
   * @param fromIndex the start (inclusive) of the range to search
   * @param toIndex the end (exclusive) of the range to search
   * @param key the key to search
   * @return the position of the key in the array; or -1 if the key doesn't exist in the array
   */
  public static int binarySearchIteratively(int[] array, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
      // "(low + high) / 2" won't work if the sum is higher than Integer.MAX_VALUE.
      // That would cause an overflow / roll over, and result in a negative value!
      int mid = (low + high) >>> 1;
      int midVal = array[mid];

      if (key == midVal) {
        return mid;
      } else if (key < midVal) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return -1;
  }
}
