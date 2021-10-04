package eu.happycoders.binarysearch;

/**
 * Implementation of linear search for int arrays.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public final class LinearSearch {

  private LinearSearch() {}

  /**
   * Searches the given key in the given array applying a linear search.
   *
   * @param array the array in which to search the key
   * @param key the key to search
   * @return the position of the key in the array; or -1 if the key doesn't exist in the array
   */
  public static int search(int[] array, int key) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == key) {
        return i;
      }
    }
    return -1;
  }
}
