// WordNet Synonym Replacing Example
// Daniel Shiffman
// Programming from A to Z, Spring 2007
// http://www.shiffman.net/a2z

package wordnet;

import java.util.*;

import net.didion.jwnl.*;
import net.didion.jwnl.data.*;
import net.didion.jwnl.data.relationship.*;
import net.didion.jwnl.dictionary.Dictionary;

public class SynReplace {

    public static void main(String[] args) throws JWNLException {

        // Initialize the database
        // You must configure the properties file to point to your dictionary files
        WordNetHelper.initialize("file_properties.xml");

        System.out.println("This program will take the following paragraph and replace" +
        "\nall words with a synonym, if it can find one.\n\n");

        String test = "It was a dark and stormy night; the rain fell in torrents, " +
        "\nexcept at occasional intervals, when it was checked by a violent " +
        "\ngust of wind which swept up the streets (for it is in London that our scene lies), " +
        "\nrattling along the housetops, and fiercely agitating the scanty flame of " +
        "\nthe lamps that struggled against the darkness.";


        System.out.println(test + "\n\n");

        String[] tokens = test.split("\\b");
        String newSentence = "";

        // Walk through all tokens
        for (int i = 0; i < tokens.length; i++) {
            // This will our replace word, if we don't find anything to replace it with
            // we just use the same word
            String newWord = tokens[i];
            // LookUp all IndexWords and store in an array
            IndexWordSet set = Dictionary.getInstance().lookupAllIndexWords(tokens[i]);
            IndexWord[] words = set.getIndexWordArray();
            // Try to get a Synonym for any IndexWord, first come first serve!
            for (int j = 0; j < words.length; j++) {
                String found = getSynonym(words[j]);
                // If we found something let's get out of here
                if (found != null) {
                    newWord = found;
                    break;
                }
            }
            // Rebuild new sentence
            newSentence += newWord;

        }

        System.out.println("\n\nHere is the revised paragraph: ");
        System.out.println("\n" + newSentence);
    }

    public static String getSynonym(IndexWord w) throws JWNLException {
        // Use the helper class to get an ArrayList of similar Synsets for an IndexWord
        ArrayList a = WordNetHelper.getRelated(w,PointerType.SIMILAR_TO);
        // As long as we have a non-empty ArrayList
        if (a != null && !a.isEmpty()) {
            System.out.println("Found a synonym for " + w.getLemma() + ".");
            // Pick a random Synset
            int rand = (int) (Math.random() * a.size());
            Synset s = (Synset) a.get(rand);
            // Pick a random Word from that Synset
            Word[] words = s.getWords();
            rand = (int) (Math.random() * words.length);
            return words[rand].getLemma();
        }
        return null;
    }
}