// Decompiled by Jad v1.5.8c. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GoogleAPIDemo.java

import com.google.soap.search.*;
import java.io.PrintStream;

public class GoogleAPIDemo
{

    public GoogleAPIDemo()
    {
    }

    public static void main(String args[])
    {
        String s = "C+zZ7UZQFHLNOyUNUCT2uphJ3a6WxQZF";
        GoogleSearch googlesearch = new GoogleSearch();
        googlesearch.setKey(s);
        try
        {
            String s1 = "itp";
            googlesearch.setQueryString(s1);
            System.out.println("Searching for " + s1);
            GoogleSearchResult googlesearchresult = googlesearch.doSearch();
            System.out.println(googlesearchresult.toString());
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
