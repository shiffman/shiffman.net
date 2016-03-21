/* Asterisk and Java
 * Daniel Shiffman
 * Big Screens
 * ITP, Fall 2007
 * Thanks to Shawn Van Every!
 */
 
import processing.net.*; 

// Declare a client 
Client client; 

// A String to hold whatever the server says 
String messageFromServer = "Waiting for call.";
PFont f; 

void setup() { 
  size(600,200); 
  frameRate(30); 
  // Create the Client 
  client = new Client(this, "yourserver", 9001);  // Port is whatever you set it up to be
  background(0); 
  f = createFont("Georgia",64,true);
} 

void draw() 
{ 
  background(0); 
  // Display message from server 
  fill(255); 
  textFont(f); 
  textAlign(CENTER); 
  textFont(f,32); 
  text("Call ###-###-#### ext. ###",width/2,height/4);
  textFont(f); 
  text(messageFromServer,width/2,height/2+32); 

  // If there is information available to read from the Server 
  if (client.available() > 0) { 
    messageFromServer = client.readString();   // Read it as a String 
  } 
} 
