// Demo basic WordNet functionality
// Daniel Shiffman
// Programming from A to Z, Spring 2007
// http://www.shiffman.net/a2z

package wordnet;

import java.util.*;

import net.didion.jwnl.*;
import net.didion.jwnl.data.*;
import net.didion.jwnl.data.relationship.*;

public class WordNetDemo {

    public static void main(String[] args) throws JWNLException {
        
        // Initialize the database
        // You must configure the properties file to point to your dictionary files
        WordNetHelper.initialize("file_properties.xml");
        
        // Demo finding parts of speech
        findPartsOfSpeech("run");

        // Demo listing "glosses" (i.e. definitions)
        IndexWord w = WordNetHelper.getWord(POS.VERB, "eat");
        listGlosses(w);
        
        // Demo finding a list of related words (synonyms)
        w = WordNetHelper.getWord(POS.ADJECTIVE, "tired");
        findRelatedWordsDemo(w,PointerType.SIMILAR_TO);

        // Demo finding a list of related words
        // X is Hypernym of Y if every Y is of type X
        // Hyponym is the inverse
        w = WordNetHelper.getWord(POS.NOUN, "chair");
        findRelatedWordsDemo(w,PointerType.HYPONYM);
        findRelatedWordsDemo(w,PointerType.HYPERNYM);

        // Example of Symmetric Relationship (Similar to, i.e. Synonym)
        IndexWord start = WordNetHelper.getWord(POS.ADJECTIVE, "funny");
        IndexWord end = WordNetHelper.getWord(POS.ADJECTIVE, "droll");
        findRelationshipsDemo(start,end,PointerType.SIMILAR_TO);

        // Example of Asymmetric Relationship (HYPERNYM)
        start = WordNetHelper.getWord(POS.NOUN, "dog");
        end = WordNetHelper.getWord(POS.NOUN, "giraffe");
        findRelationshipsDemo(start,end,PointerType.HYPERNYM);

        // Some other types of relationships
        //findRelationshipsDemo(start,end,PointerType.ANTONYM);
        //findRelationshipsDemo(start,end,PointerType.ENTAILMENT);
        //findRelationshipsDemo(start,end,PointerType.HYPONYM);
        
        System.out.println("Demonstrating a Tree operation");
        WordNetHelper.showRelatedTree(start,3,PointerType.HYPERNYM);

    }

    public static void findPartsOfSpeech(String word) throws JWNLException {
        System.out.println("\nFinding parts of speech for " + word + ".");
        // Get an array of parts of speech
        POS[] pos = WordNetHelper.getPOS(word);
        // If we found at least one we found the word
        if (pos.length > 0) {
            // Loop through and display them all
            for (int i = 0; i < pos.length; i++) {
                System.out.println("POS: " + pos[i].getLabel());
            }
        } else {
            System.out.println("I could not find " + word + " in WordNet!");
        }
    }

    
    public static void listGlosses(IndexWord word) throws JWNLException {
        System.out.println("\n\nDefinitions for " + word.getLemma() + ":");
        // Get an array of Synsets for a word
        Synset[] senses = word.getSenses();
        // Display all definitions
        for (int i = 0; i < senses.length; i++) {
            System.out.println(word + ": " + senses[i].getGloss());
        }    
    }

    // This function lists related words of type of relation for a given word
    public static void findRelatedWordsDemo(IndexWord w, PointerType type) throws JWNLException {
        System.out.println("\n\nI am now going to find related words for " + w.getLemma() + ", listed in groups by sense.");
        System.out.println("We'll look for relationships of type " + type.getLabel() + ".");
        
        // Call a function that returns an ArrayList of related senses
        ArrayList a = WordNetHelper.getRelated(w,type);
        if (a.isEmpty()) {
            System.out.println("Hmmm, I didn't find any related words.");
        } else {
            // Display the words for all the senses
            for (int i = 0; i < a.size(); i++) {
                Synset s = (Synset) a.get(i);
                Word[] words = s.getWords();
                for (int j = 0; j < words.length; j++ ) {
                    System.out.print(words[j].getLemma());
                    if (j != words.length-1) System.out.print(", ");
                }
                System.out.println();
            }
        }
    }


    public static void findRelationshipsDemo(IndexWord start, IndexWord end, PointerType type) throws JWNLException {

        System.out.println("\n\nTrying to find a relationship between \"" + start.getLemma() + "\" and \"" + end.getLemma() +"\".");
        System.out.println("Looking for relationship of type " + type.getLabel() + ".");

        // Ask for a Relationship object
        Relationship rel = WordNetHelper.getRelationship(start, end, type);
        // If it's not null we found the relationship
        if (rel != null) {
            // Display depth
            System.out.println("The depth of this relationship is: " + rel.getDepth());
            System.out.println("Here is how the words are related: ");
            // Get a list of the words that make up the relationship
            ArrayList a = WordNetHelper.getRelationshipSenses(rel);
            System.out.println("Start: " + start.getLemma());
            // Display all senses
            for (int i = 0; i < a.size(); i++) {
                Synset s = (Synset) a.get(i);
                Word[] words = s.getWords();
                System.out.print(i + ": ");
                for (int j = 0; j < words.length; j++ ) {
                    System.out.print(words[j].getLemma());
                    if (j != words.length-1) System.out.print(", ");
                }
                System.out.println();
            }

        } else {
            System.out.println("I could not find a relationship between these words!");
        }



    }


}