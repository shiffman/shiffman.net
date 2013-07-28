/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Google API Demo
// Created from the sample code provided by Google

// Import the necessary libraries
import com.google.soap.search.*;

public class GoogleAPIDemo
{
    
    public static void main(String args[])
    {
        String s = "INSERTKEYHERE";
        GoogleSearch googlesearch = new GoogleSearch();
        googlesearch.setKey(s);
        try
        {
            String s1 = "itp";
            googlesearch.setQueryString(s1);
            System.out.println("Searching for " + s1);
            GoogleSearchResult googlesearchresult = googlesearch.doSearch();
            int total = googlesearchresult.getEstimatedTotalResultsCount();
            System.out.println("Estimated total results: " + total);
            
            String s2 = "seperate";
            System.out.println("Checking spelling of " + s2);
            String s3 = googlesearch.doSpellingSuggestion(s2);
            System.out.println("Yeah, that should be spelled: " + s3);
        }
        catch(GoogleSearchFault googlesearchfault)
        {
            System.out.println("Yikes, something went wrong");
            System.out.println(googlesearchfault.toString());
        }
    }
}
