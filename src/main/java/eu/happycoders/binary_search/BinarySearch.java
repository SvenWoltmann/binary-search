package eu.happycoders.binary_search;

/**
 * Implementation of the Binary Search algorithm for int arrays - recursively and iteratively.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BinarySearch {

  public static int binarySearchRecursively(int[] array, int key) {
    return binarySearchRecursively(array, 0, array.length, key);
  }

  public static int binarySearchRecursively(int[] array, int fromIndex, int toIndex, int key) {
    if (toIndex <= fromIndex) return -1;

    // "(fromIndex + toIndex) / 2" won't work if the sum is higher than Integer.MAX_VALUE;
    // That would cause an overflow / roll over, and result in a negative value!
    int mid = (fromIndex + toIndex) >>> 1;
    int midVal = array[mid];

    if (key == midVal) {
      return mid;
    } else if (key < midVal) {
      return binarySearchRecursively(array, fromIndex, mid, key);
    } else {
      return binarySearchRecursively(array, mid + 1, toIndex, key);
    }
  }

  public static int binarySearchIteratively(int[] array, int key) {
    return binarySearchIteratively(array, 0, array.length, key);
  }

  public static int binarySearchIteratively(int[] array, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex;

    while (low < high) {
      // "(fromIndex + toIndex) / 2" won't work if the sum is higher than Integer.MAX_VALUE;
      // That would cause an overflow / roll over, and result in a negative value!
      int mid = (low + high) >>> 1;
      int midVal = array[mid];

      if (key == midVal) {
        return mid;
      } else if (key < midVal) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return -1;
  }
}
