

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class implements a user interface to print and handle user's inputs.
 */

public class Run {
  private static AceBackend backEnd = new AceBackend();

  public static void main(String[] args) {
    System.out.println("Welcome to Aced It!");
    System.out.println();
    System.out.println("Menu:");
    System.out.println("Type 'Help' if you need assistance");
    System.out.println("Type 'Courses' if you want to see courses offered");
    System.out.println("Type 'Number of Tutors' to see available tutors for a course");
    System.out.println("Type 'Quit' if you want to exit this application");
    System.out.println(
        "***************************************************************************************");
    role(); // show the possible options to choose from: student, tutor, or owner
  }

  
  
  /**
   * Method that checks whether a user is a student, tutor, or owner and calls different methods
   * depending on the role of the user
   * 
   */

  public static void role() {
    Scanner scnr = new Scanner(System.in);
    System.out.println("Enter your role, 'Student','Tutor' or 'Owner': ");
    String role = scnr.nextLine().toUpperCase().trim();

    if (role.equals("STUDENT")) {
      student();
    } else if (role.equals("TUTOR")) {
      tutor();
    } else if (role.equals("OWNER")) {
      owner();
    } else {
      basicMenu(role); // take user back to basic menu which gives different tabs to navigate to
    }
  }

  
  
  /**
   * Method that prints out information for students and removes a tutor from the specified course
   * when student books a tutoring session for that course
   * 
   */
  public static void student() {
    Scanner scnr = new Scanner(System.in);
    System.out.println(
        "Please enter which course you want to be tutored for (Enter as it appears in your "
            + "schedule):");
    String studentCourse = scnr.nextLine().toUpperCase().trim(); // adjusting user input 

    try {
      if (backEnd.equalsCourses(studentCourse)) { // if there is a course matching student's input
        backEnd.decrementTutor(studentCourse); // decrement number of tutors available
      } else {
        basicMenu(studentCourse);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Sorry, a problem occurred on our end! Please try again later");
    }

    if (backEnd.decrementTutor(studentCourse)) {

      System.out.println("You have successfully signed up for a tutoring session for "
          + studentCourse);
      menu();
    } else {
      System.out.println("No tutors available for this course");
      role();
    }
  }

  
  
  /**
   * Method that prints out information for tutors and adds a tutor from the specified course when
   * tutor wants to sign up to teach a course
   * 
   */
  public static void tutor() {
    Scanner scnr = new Scanner(System.in);
    System.out.println(
        "Please enter which course you want to tutor (Enter as it appears on students' schedule):");
    String tutorCourse = scnr.nextLine().toUpperCase().trim();

    try {
      if (backEnd.equalsCourses(tutorCourse)) {
        backEnd.incrementTutor(tutorCourse); // increment because tutor is adding themself to list
      } else {
        basicMenu(tutorCourse);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Sorry, a problem occurred on our end! Please try again later");
    }

    System.out.println("Thank you for signing to be a tutor for " + tutorCourse);

    menu(); // take them back to main menu
  }

  public static void owner() {
    Scanner scnr = new Scanner(System.in);
    System.out.println("What course would you like to add:");
    String addCourse = scnr.nextLine().toUpperCase().trim();

    if (addCourse.equals("HELP") || addCourse.equals("COURSES") || addCourse.equals("QUIT")) {
      basicMenu(addCourse);
    } else if (!backEnd.addSubject(addCourse)){
      System.out.println("This course already exists!");
      menu();
    } else {
      backEnd.addSubject(addCourse);
      System.out.println("You have successfully added " + addCourse);
    }


  }


  /**
   * Method that deals with any of the basic menu words that the user enters. If the user enters an
   * invalid input, it will be handled here.
   * 
   */
  public static void basicMenu(String input) {
    if (input.equals("HELP")) {
      System.out.println(
          "Enter 'Courses' if you want a list of courses available. Enter 'Quit' if you want to "
              + "exit this application.");
      role();
    } else if (input.equals("COURSES")) {
      backEnd.listSubjects();
      role();
    } else if (input.equals("NUMBER OF TUTORS")) {
      Scanner scnr = new Scanner(System.in);
      System.out.println("Which class do you want to view number of tutors for?: ");
      String course = scnr.nextLine().toUpperCase().trim();
      System.out.println(backEnd.numTutors(course));
      role();
    }

    else if (input.equals("QUIT")) {
      System.exit(0);
    } else {
      System.out.println("You have entered an incorrect input. Please try again.");

      role();
    }
  }

  public static void menu() {
    System.out.println("Menu:");
    System.out.println("Type 'Help' if you need assistance");
    System.out.println("Type 'Courses' if you want to see courses offered");
    System.out.println("Type 'Number of Tutors' to see available tutors for a course");
    System.out.println("Type 'Quit' if you want to exit this application");

    Scanner scnr = new Scanner(System.in);
    String input = scnr.nextLine().toUpperCase().trim();
    System.out.println("Enter:");
    basicMenu(input);
  }



}
