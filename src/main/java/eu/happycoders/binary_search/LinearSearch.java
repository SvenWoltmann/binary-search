package eu.happycoders.binary_search;

/**
 * Implementation of linear search for int arrays.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class LinearSearch {

  public static int search(int[] array, int key) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == key) return i;
    }
    return -1;
  }
}
