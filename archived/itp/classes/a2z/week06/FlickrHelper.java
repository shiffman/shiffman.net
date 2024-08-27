/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2008                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* A Helper class to grab and parse
 * data from the Flickr API
 * This example has little to know functionality
 * and just serves as a starting framework.
 */

package flickr;

import java.io.IOException;
import org.xml.sax.SAXException;
import com.aetrion.flickr.*;
import com.aetrion.flickr.photos.*;

public class FlickrHelper {

	// A Flickr object for making requests
	Flickr f;

	public FlickrHelper() {
		// GET YOUR OWN KEY!!!!
		f = new Flickr("dfe5a1b38d0496277c8ab9b4b2ce8a8b");
	}

	public String[] findPhotos(String tag, int n) {
		// Interface with Flickr photos
		PhotosInterface photos = f.getPhotosInterface();
		// Create a search parameters object to control the search
		SearchParameters sp = new SearchParameters();
		// Simple example, just looking for a single tag
		sp.setTags(new String[] {"itp"});
		try {
			// We're looking for n images, starting at "page" 0
			PhotoList list = photos.search(sp, n,0);
			// Grab all the image paths and store in String array
			String[] smallURLs = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				// We can get a lot more info from a Photo beyond simply a urlpath
				Photo p = (Photo) list.get(i);
				smallURLs[i] = p.getSmallUrl();
			}
			return smallURLs;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		return null;
	}

}
