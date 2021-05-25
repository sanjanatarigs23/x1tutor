
/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * HashTableMap.
 * 
 * @author Sanjana Tarigoppula
 *
 */

public class TestHashTable {


  /**
   * Checks for the correctness of HashTableMap.put() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test1() {

    HashTableMap one = new HashTableMap(10); // creating a new hash table object with capacity 10
    one.put(4, 8); // adding two pairs - (4,8) and (3, 7) to the hash table
    one.put(3, 7);

    if (one.size() != 2) { // ensuring that method executed correctly by checking that the size of
                           // the array is 2
      return false;
    }

    one.put(2, 6); // put another pair

    if (one.size() != 3) { // ensure size is 3
      return false;
    }

    one.put(7, 9); // put another pair

    if (one.size() != 4) { // ensure size is 4
      return false;
    }

    return true;
  }



  /**
   * Checks for the correctness of HashTableMap.get() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test2() {
    HashTableMap two = new HashTableMap(8);

    two.put(3, 8);
    two.put(4, 7);

    if (!(two.get(3).equals(8))) { // the key (3) is mapped to value (8), so method should return 8
      return false;
    }

    two.put(6, 3);

    if (!(two.get(6).equals(3))) {
      return false;
    }

    try {
      two.get(5); // this key hasn't been added so it should not work
    } catch (java.util.NoSuchElementException e) {
      if (e.getMessage() == null || !(e.getMessage().toLowerCase().contains("values exist"))) {
        System.out.println("Test 3 failed");
        return false;
      }
    }

    return true;
  }



  /**
   * Checks for the correctness of HashTableMap.remove() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test3() {
    HashTableMap three = new HashTableMap(8);

    if (three.remove(4) != null) { // since we didn't put in any pairs, this should return null
      return false;
    }

    three.put(4, 8);
    three.put(7, 14);

    if (!(three.remove(4).equals(8))) { // method should return key's value so 8 should be returned
      return false;
    }

    if (three.remove(4) != null) { // if you try removing the same pair it shouldn't work since it's
                                   // already been removed
      return false;
    }



    return true;
  }



  /**
   * Checks for the correctness of HashTableMap.rehash() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test4() { // rehash test
    HashTableMap four = new HashTableMap(5);

    four.put(1, 2);
    four.put(2, 3);
    four.put(3, 4);
    four.put(4, 5);
    four.put(5, 6);
    four.put(6, 7); // putting one more value in the hash table than the capacity allows


    if (!(four.get(6).equals(7))) { // should work because the array should've rehashed after 4th //
                                    // input
      return false;
    }


    if (four.capacity() != 10) { // capacity should've doubled from 5 to 10
      return false;
    }


    four.put(7, 8);
    four.put(8, 9);
    four.put(9, 10);
    four.put(10, 10);
    four.put(11, 12);

    if (four.capacity() != 20) { // making sure that the hashtable rehashed after adding more pairs.
      return false;
    }


    return true;
  }



  /**
   * Checks that the program is handling collisions properly
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test5() {
    HashTableMap five = new HashTableMap(10);

    five.put(17, 2);
    five.put(27, 3); // 17 and 27 both have the hash code of 7

    if (!(five.get(17).equals(2))) {
      return false;
    }

    if (!(five.get(27).equals(3))) { // if the value is 2, then the program is not handling
                                     // collisions properly
      return false;
    }


    five.put(5, 3); // just checking again with hash code 0
    five.put(2, 10);

    if (!(five.get(5).equals(3))) {
      return false;
    }

    if (!(five.get(2).equals(10))) {
      return false;
    }


    return true;
  }



  /**
   * Main method testing out the tests
   * 
   */
  public static void main(String[] args) {
    System.out.println(test1());
    System.out.println(test2());
    System.out.println(test3());
    System.out.println(test4());
    System.out.println(test5());

  }

}
