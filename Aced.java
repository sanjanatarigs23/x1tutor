

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This class manipulates the given data file and stores key-value pairs into a hash table.
 * 
 * @author Sanjana Tarigoppula
 */

public class Aced {

  public static HashTableMap<String, Integer> tutorHash = new HashTableMap(200);

  
  /**
   * This method takes the text file with data (data.txt) and reads through it, categorizing the 
   * content as either key or value. Then it puts the key-value pairs in the hash table.
   *  
   */
  public static void data() {

    String key = "";
    String value = "";
    String[] pair = new String[2]; // stores the subject and name after splitting each line

    try {
      File file = new File("data.txt"); // create a new file holding the data text file
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) { // go through the text file
        String str = sc.nextLine();
        pair = str.split(", "); // split to get key and value
        key = pair[0];
        value = pair[1];
        tutorHash.put(key, Integer.valueOf(value)); // call the put method to put data in hash table
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error - file not found");
      e.printStackTrace();
    }

  }
  
  
  
  /**
   * This method updates the hash table when called from another class, so that backend has access
   * to the hash table with all of the information in it. 
   * 
   * @return the hash table which has all info stored in it
   *  
   */
  public static HashTableMap getHT() { 
    data();
    return tutorHash;
  }



}
