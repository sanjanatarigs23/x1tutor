
/**
 * This class tests the AceBackend class
 */
public class TestProject {

  
  
  /**
   * method to check the functionality of get().
   * 
   * @return true if code is right false if there is a bug
   */
  public static boolean testGetAmountOfTutors() {
    AceBackend test3 = new AceBackend();
    test3.incrementTutor("BIOLOGY 101");
    test3.incrementTutor("COMP SCI 240");
    test3.incrementTutor("JOURN 345");
    test3.incrementTutor("PHYSICS 104");
    return (test3.numTutors("PHYSICS 104") == (4));
  }

  
  
  /**
   * method for testing add tutor functioning
   * 
   * @return true if code is right false if there is a bug
   */
  public static boolean testAddTutor() {
    AceBackend test6 = new AceBackend();
    System.out.println(test6.numTutors("GEOSCI 100"));
    test6.incrementTutor("GEOSCI 100");
    test6.incrementTutor("JOURN 345");
    test6.incrementTutor("BIOLOGY 101");
    test6.incrementTutor("GEOSCI 100");

    return (test6.numTutors("GEOSCI 100") == (16));
  }

  
  
  /**
   * method for testing remove tutor functioning
   * 
   * @return true if code is right false if there is a bug
   */
  public static boolean testRemoveTutor() {
    AceBackend test7 = new AceBackend();
    test7.decrementTutor("ECON 101");
    test7.incrementTutor("JOURN 345");
    test7.incrementTutor("BIOLOGY 101");
    test7.incrementTutor("GEOSCI 100");
  
    return (test7.numTutors("ECON 101") == (12));
  }

  
  
  public static void main(String[] args) {
    System.out.println(testGetAmountOfTutors());
    System.out.println(testAddTutor());
    System.out.println(testRemoveTutor());


  }

}
