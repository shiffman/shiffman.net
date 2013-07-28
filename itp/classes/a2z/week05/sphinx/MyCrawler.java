package sphinx;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import websphinx.Crawler;
import websphinx.Link;
import websphinx.Page;


// Our own Crawler class extends the WebSphinx Crawler
public class MyCrawler extends Crawler {

   
    MyCrawler() {
        super();  // Do what the parent  crawler would do
    }
    
    // We could choose not to visit a link based on certain circumstances
    // For now we always visit the link
    public boolean shouldVisit(Link l) {
        //String host = l.getHost();
        return true; // always visit a link
    }
    
    // What to do when we visit the page
    public void visit(Page page) {
        //System.out.println("Visiting: " + page.getTitle());
        String content = page.getContent();
        
        // Trivial example, searching for the word valentine
        Pattern p = Pattern.compile("valentine",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        if (m.find()) {
            System.out.println(page.getTitle() + ", would you be my valentine?");
        }
        
        // Since we don't need to retain the pages
        // This code helps with memory management
        page.getOrigin().setPage(null);
        page.discardContent();
        
        // Print out some stats about the crawler every 10 pages visited
        int n = this.getPagesVisited();
        if (n % 10 == 0) System.out.println(this.getPagesVisited() + " pages visited.  " + this.getPagesLeft() + " pages left.  " + this.getActiveThreads() + " active threads.");
                
    }
    
}
