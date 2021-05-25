
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * This class contains methods to connect the front end interface with the data stored in the hash
 * table.
 * 
 * 
 */
public class AceBackend {

  static HashTableMap<String, Integer> tutorHash = Aced.getHT(); // Access Hashtable from
                                                                 // Aced.Java


  /**
   * Decreases amount of tutors of a given subject by 1
   * 
   * @param key - given subject
   * @return true if tutor amount was decremented successfully, false otherwise 
   */
  public boolean decrementTutor(String key) {
    int amt = tutorHash.get(key); // first get the number of tutors for the given subject

    if (amt == 0) {
      return false; // cannot decrement if there are no tutors to begin with
    } else {
      amt = amt - 1;
      tutorHash.remove(key); // first remove the key-value pair and re-add with correct value
      tutorHash.put(key, amt);
      return true;
    }

  }



  /**
   * Increases amount of tutors by a given subject by 1
   * 
   * @param key - given subject
   */
  public void incrementTutor(String key) {
    int amt = tutorHash.get(key);
    amt = amt + 1;
    tutorHash.remove(key);
    tutorHash.put(key, amt);

  }



  /**
   * Adds a new subject to the hash table and updates the Courses.txt file directly by writing to it
   * 
   * @param key - given subject
   * @return true if the subject was added, false otherwise
   */
  public boolean addSubject(String key) {
    String addCourse = key;
    if (!tutorHash.containsKey(key)) { // if subject doesn't exist in our database already, continue
      tutorHash.put(key, 0);
      try (FileWriter fw = new FileWriter("Courses.txt", true); // updating the Courses.txt file
          BufferedWriter bw = new BufferedWriter(fw);
          PrintWriter pw = new PrintWriter(bw)) {
        pw.println(addCourse); // add the course to Courses.txt directly
        return true;
      } catch (IOException e) {
        System.out.println("Sorry, an error occurred on our end. Please try again later!");
        return false;
      }
    } else {
      return false; // if subject already exists, return false
    }
  }



  /**
   * Checks if a subject is in the Courses.txt file
   * 
   * @param key - subject
   * @return true if found, false otherwise
   * @throws FileNotFoundException
   */
  public boolean equalsCourses(String key) throws FileNotFoundException {

    File file = new File("Courses.txt"); 
    Scanner sc = new Scanner(file); // will read through the Courses.txt file to find matching class

    if (file.exists() == false) {
      throw new FileNotFoundException("file not found");
    }

    while (sc.hasNextLine()) { // go through file, if it contains the given key then return true
      if (sc.nextLine().contains(key)) {
        return true;
      }
    }

    return false;
  }



  /**
   * Lists Subjects from Courses.txt
   * 
   */
  public void listSubjects() {
    Scanner sc = null;;
    try {
      File file = new File("Courses.txt");
      sc = new Scanner(file);
      while (sc.hasNextLine()) { // Basically reading through the file and printing everything out
        System.out.println(sc.nextLine()); 
      }
    } catch (FileNotFoundException e) {
      System.out.print("file not found.");
    }
  }


  
  /**
   * Gets the number of tutors associated with the given key
   * 
   * @return number of tutors of particular subject
   * 
   */
  public int numTutors(String key) {
    return tutorHash.get(key); // get method returns the value of key which equals numTutors
  }

}

