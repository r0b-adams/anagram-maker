// Anagrams stores a Set of words from a given dictionary, determines which words from the
// dictionary can be formed from the letters of a given phrase, and prints anagrams of that
// phrase. It can either print all anagrams of the given phrase, or anagrams that include at
// most a maximum number of words. The anagrams are printed in alphabetical order.

import java.util.*;

public class Anagrams {
   private Set<String> dictionaryWords; // all words from given dictionary

   // Param: dictionary: Alphabetized set of words that will be used to create anagrams from
   //   Pre: dictionary is not null, else throws IllegalArgumentException
   //  Post: Constructs a new Anagrams object storing all words from the given dictionary
   public Anagrams(Set<String> dictionary) {
      if (dictionary == null) {
         throw new IllegalArgumentException();
      }
      this.dictionaryWords = new TreeSet<String>();
      this.dictionaryWords.addAll(dictionary);
   }

   // Param: phrase: Given String used to find all words in dictionary given in constructor
   //                whose letters occur in phrase
   //   Pre: phrase is not null, else throws IllegalArgumentException
   //  Post: Returns an alphabetized Set of all words in dictionary given in constructor
   //        that can be formed using all or some of the letters in phrase
   public Set<String> getWords(String phrase) {
      phraseCheck(phrase);
      return phraseContains(new LetterInventory(phrase));
   }

   // Param: phrase: Given String to create anagrams from
   //   Pre: phrase is not null, else throws IllegalArgumentException
   //  Post: Prints all anagrams, using the words from dictionary given in constructor,
   //        that can be formed from the given phrase
   //        Anagrams are printed in alphabetical order
   public void print(String phrase) {
      print(phrase, 0);
   }

   // Param: phrase: Given String to create anagrams from
   //           max: maximum number of words to appear in the anagram
   //   Pre: phrase is not null, else throws IllegalArgumentException
   //        max > 0, else throws IllegalArgumentException
   //  Post: If max == 0, prints all anagrams of the given phrase.
   //        If max > 0, prints all anagrams that include up to as many words as max
   //        Uses words from dictionary given in constructor in both cases
   //        Anagrams are printed in alphabetical order
   public void print(String phrase, int max) {
      phraseCheck(phrase);
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory letters = new LetterInventory(phrase);
      print(letters, new Stack<String>(), phraseContains(letters), max);
   }

   // Param: letters: LetterInventory storing the letters of a phrase in alphabetical order
   //        anagram: Stores words that make up an anagram
   //        choices: Set of words that can be formed using letters in a phrase, using words
   //                 from dictionary given in constructor
   //            max: the maximum number of words that can appear in an anagram
   //  Post: If max == 0, prints all anagrams of the given phrase.
   //        If max > 0, prints all anagrams that include up to as many words as max
   //        Uses words from dictionary given in constructor in both cases
   //        Anagrams are printed in alphabetical order
   private void print(LetterInventory letters, Stack<String> anagram,
                                                      Set<String> choices, int max) {
      if (letters.isEmpty()) {
         System.out.println(anagram);
      } else if (max == 0 || anagram.size() < max) {
         for (String word : choices) {
            if (letters.contains(word)) {              // make sure we can make a word out of the remaining letters
               anagram.push(word);                     // save a word
               letters.subtract(word);                 // subtract that word's letters from inventory
               print(letters, anagram, choices, max);  // keep exploring for more words to try
               anagram.pop();                          // can't make successful anagram, remove the last word tried
               letters.add(word);                      // add the tried word's letters back to inventory
            }
         }
      }
   }

   // Param: letters: LetterInventory storing all the letters of a phrase in alphabetical order
   //  Post: Creates and returns a new alphabetized Set of words from dictionary given in
   //        constructor whose letters appear in the given LetterInventory
   private Set<String> phraseContains(LetterInventory letters) {
      Set<String> wordOccurs = new TreeSet<String>();
      for (String word : this.dictionaryWords) {
         if (letters.contains(word)) {
            wordOccurs.add(word);
         }
      }
      return wordOccurs;
   }

   // Param: phrase: Given String to check
   //   Pre: phrase is not null, else throws IllegalArgumentException
   private void phraseCheck(String phrase) {
      if (phrase == null) {
         throw new IllegalArgumentException();
      }
   }
}