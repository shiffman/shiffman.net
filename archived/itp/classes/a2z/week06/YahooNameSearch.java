//Yahoo Search Example
//Daniel Shiffman
//Programming from A to Z, Spring 2007
//Based on example code from Yahoo by Ryan Kennedy

import java.io.IOException;
import java.math.BigInteger;

import com.yahoo.search.*;

public class YahooNameSearch {
    public static void main(String[] args) throws IOException, SearchException {
        // Create the search client. Pass it your application ID.
        SearchClient client = new SearchClient("Jm3V0PbV34GKpO58IjWbVvW26XjoKlrkriC2D4idXRBm8No3VDoCCjQLhBqsjJ9wRVI");

        // Create the web search request. In this case we're searching for
        // java-related hits.

        String[] names = { "Benjamin","Christopher","Corrine","Daniel","Dean","James","Jennifer","Kate","Kazuhiro","Kyveli","Nick","Ran","Raymond","Shlomit","Tamara","Tim","Vaibhav","Zach"}; 
        int[] counts = new int[names.length];


        for (int i = 0; i < names.length; i++) {
            WebSearchRequest request = new WebSearchRequest(names[i]);
            // Execute the search.
            System.out.println("Searching for " + names[i]);
            WebSearchResults results = client.webSearch(request);
            BigInteger count = results.getTotalResultsAvailable();
            
            counts[i] = count.intValue();
            // If we wanted to look at the results
            /*for (int i = 0; i < results.listResults().length; i++) {
                WebSearchResult result = results.listResults()[i];
                System.out.println("   " + (i + 1) + ": " + result.getTitle() + " - " + result.getUrl());
            }*/
        }
        // Sort the arrays
        sort(counts,names);
        
        System.out.println("\n\n");
        for (int i = 0; i < names.length; i++) {
            System.out.println(counts[i] + ":  " + names[i]);
        }
    }


    // A simple selection sort
    // This is a bit silly and unecessary, but what the hey
    public static void sort(int[] nums, String[] names) {
        for (int i = 0; i < nums.length; i++) {
            int largestIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > nums[largestIndex]) {
                    largestIndex = j;
                }
            }
            // swap
            int num = nums[i];
            nums[i] = nums[largestIndex];
            nums[largestIndex] = num;
            // oh swap the strings also
            String name = names[i];
            names[i] = names[largestIndex];
            names[largestIndex] = name;
        }
    }
}
