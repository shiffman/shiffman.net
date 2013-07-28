// Class with some static WordNet helper functions
// Daniel Shiffman
// Programming from A to Z, Spring 2007
// http://www.shiffman.net/a2z

package wordnet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;


import net.didion.jwnl.*;
import net.didion.jwnl.data.*;
import net.didion.jwnl.data.list.*;
import net.didion.jwnl.data.relationship.*;
import net.didion.jwnl.dictionary.*;

public class WordNetHelper {

    // Dictionary object
    public static Dictionary wordnet;

    // Initialize the database!
    public static void initialize(String propsFile) {
        //String propsFile = "file_properties.xml";
        try {
            JWNL.initialize(new FileInputStream(propsFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JWNLException e) {
            e.printStackTrace();
        }
        // Create dictionary object
        wordnet = Dictionary.getInstance();
    }


    // Return array of POS objects for a given String
    public static POS[] getPOS(String s) throws JWNLException {
        // Look up all IndexWords (an IndexWord can only be one POS)
        IndexWordSet set = wordnet.lookupAllIndexWords(s);
        // Turn it into an array of IndexWords
        IndexWord[] words = set.getIndexWordArray();
        // Make the array of POS
        POS[] pos = new POS[words.length];
        for (int i = 0; i < words.length; i++) {
            pos[i] = words[i].getPOS();
        }
        return pos;
    }

    // Just gets the related words for first sense of a word
    // Revised to get the list of related words for the 1st Synset that has them
    // We might want to try all of them
    public static ArrayList getRelated(IndexWord word, PointerType type) throws JWNLException {
        try {
            Synset[] senses = word.getSenses();
            // Look for the related words for all Senses
            for (int i = 0; i < senses.length; i++) {
                ArrayList a = getRelated(senses[i],type);
                // If we find some, return them
                if (a != null && !a.isEmpty()) {
                    return a;
                }
            }
        } catch (NullPointerException e) {
            // System.out.println("Oops, NULL problem: " + e);
        }
        return null;
    }

    // Related words for a given sense (do synonyms by default)
    // Probably should implement all PointerTypes
    public static ArrayList getRelated (Synset sense, PointerType type) throws JWNLException, NullPointerException {
        PointerTargetNodeList relatedList;
        // Call a different function based on what type of relationship you are looking for
        if (type == PointerType.HYPERNYM) {
            relatedList = PointerUtils.getInstance().getDirectHypernyms(sense);
        } else if (type == PointerType.HYPONYM){
            relatedList = PointerUtils.getInstance().getDirectHyponyms(sense);
        } else {
            relatedList = PointerUtils.getInstance().getSynonyms(sense);
        }
        // Iterate through the related list and make an ArrayList of Synsets to send back
        Iterator i = relatedList.iterator();
        ArrayList a = new ArrayList();
        while (i.hasNext()) {
            PointerTargetNode related = (PointerTargetNode) i.next();
            Synset s = related.getSynset();
            a.add(s);
        }
        return a;
    }

    // Just shows the Tree of related words for first sense
    // We may someday want to the Tree for all senses
    public static void showRelatedTree(IndexWord word, int depth, PointerType type) throws JWNLException {
        showRelatedTree(word.getSense(1), depth, type);
    }

    public static void showRelatedTree(Synset sense, int depth, PointerType type) throws JWNLException {
        PointerTargetTree relatedTree;
        // Call a different function based on what type of relationship you are looking for
        if (type == PointerType.HYPERNYM) {
            relatedTree = PointerUtils.getInstance().getHypernymTree(sense,depth);
        } else if (type == PointerType.HYPONYM){
            relatedTree = PointerUtils.getInstance().getHyponymTree(sense,depth);
        } else {
            relatedTree = PointerUtils.getInstance().getSynonymTree(sense,depth);
        }
        // If we really need this info, we wil have to write some code to Process the tree
        // Not just display it  
        relatedTree.print();
    }

    // This method looks for any possible relationship
    public static Relationship getRelationship (IndexWord start, IndexWord end, PointerType type) throws JWNLException {
        // All the start senses
        Synset[] startSenses = start.getSenses();
        // All the end senses
        Synset[] endSenses = end.getSenses();
        // Check all against each other to find a relationship
        for (int i = 0; i < startSenses.length; i++) {
            for (int j = 0; j < endSenses.length; j++) {
                RelationshipList list = RelationshipFinder.getInstance().findRelationships(startSenses[i], endSenses[j], type);
                if (!list.isEmpty())  {
                    return (Relationship) list.get(0);
                }
            }
        }
        return null;
    }

    // If you have a relationship, this function will create an ArrayList of Synsets
    // that make up that relationship
    public static ArrayList getRelationshipSenses (Relationship rel) throws JWNLException {
        ArrayList a = new ArrayList();
        PointerTargetNodeList nodelist = rel.getNodeList();
        Iterator i = nodelist.iterator();
        while (i.hasNext()) {
            PointerTargetNode related = (PointerTargetNode) i.next();
            a.add(related.getSynset());
        }
        return a;
    }

    // Get the IndexWord object for a String and POS
    public static IndexWord getWord(POS pos, String s) throws JWNLException {
        IndexWord word = wordnet.getIndexWord(pos,s);
        return word;
    }
}
