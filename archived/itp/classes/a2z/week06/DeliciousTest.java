/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Del.icio.us API Demo
// http://delicious-java.sourceforge.net/

// Import the necessary libraries
import del.icio.us.*;
import del.icio.us.beans.*;
import java.util.*;

public class DeliciousTest {
    public static void main(String[] args) {
        System.out.println("Testing del.icio.us API");
        Delicious del = new Delicious("itpa2z","interact");
        List tags = del.getTags();
        Iterator i = tags.iterator();
        while (i.hasNext()) {
            Tag tag = (Tag) i.next();
            int count = tag.getCount();
            String word = tag.getTag();
            System.out.println(word + " " + count);
        }
    }
}
