/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2008                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* A Simple Processing example that grabs
 * photos from Flickr tagged with "ITP"
 * 
 */
package flickr;

import processing.core.PApplet;
import processing.core.PImage;

public class FlickrProcessing extends PApplet {

	public static void main(String[] args) {
		PApplet.main( new String[] {"flickr.FlickrProcessing"});
	}
	
	PImage[] images;
	
	public void setup() {
		size(800,600);
		
		FlickrHelper f = new FlickrHelper();
		// Get photo urls as String array
		String[] photos = f.findPhotos("itp",30);
		images = new PImage[photos.length];
		for (int i = 0; i < photos.length; i++) {
			// Load each photo into a PImage object
			System.out.println("Loading: " + photos[i]);
			images[i] = loadImage(photos[i]);
		}
	}

	public void draw() {
		background(0);
		// Display all photos at random locations
		for (int i = images.length-1; i >= 0; i--) {
			image(images[i],random(width)-images[i].width/2,random(height)-images[i].height/2);
		}
		noLoop();
	}
}
