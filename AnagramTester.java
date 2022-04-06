import java.io.*;
import java.util.*;

// simple client to test that Anagrams methods can be called in any order
public class AnagramTester {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("dict1.txt"));
      Set<String> dictionary = new TreeSet<String>();
      while (input.hasNextLine()) {
          dictionary.add(input.nextLine());
      }
      dictionary = Collections.unmodifiableSet(dictionary);   // read-only

      // create Anagrams object for, well, solving anagrams
      Anagrams solver = new Anagrams(dictionary);

      // Need to be able to call methods in ANY order ANY # of times

      String s = null;
      solver.print(s);

      Set<String> allWords = solver.getWords("barbara bush");
      System.out.println("Test 1");
      System.out.println(allWords);

      System.out.println("Test 2");
      solver.print("hairbrush");
      System.out.println();

      System.out.println("Test 3");
      solver.print("george bush", -5);
      System.out.println();

      System.out.println("Test 4");
      solver.print("hairbrush", 2);
      System.out.println();

      Set<String> allWords2 = solver.getWords("george bush");
      System.out.println("Test 5");
      System.out.println(allWords2);
      System.out.println();

      Set<String> allWords3 = solver.getWords("robert adams");
      System.out.println("Test 6");
      System.out.println(allWords3);
      System.out.println();

      System.out.println("Test 7");
      solver.print("george bush", 3);
      System.out.println();

      System.out.println("Test 8");
      solver.print("hairbrush");
      System.out.println();

      System.out.println("Test 9");
      solver.print("barbara bush");
      System.out.println();

      System.out.println("Test 10");
      solver.print("");
      System.out.println();

      System.out.println("Test 11");
      solver.print("barbara bush", 3);
      System.out.println();


   }
}