/* Daniel Shiffman               */
/* Programming from A to Z       */
/* The Nature of Code            */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

package lsystem;

import processing.core.PApplet;

class Turtle {

	String todo;
	float len;
	float theta;
	PApplet parent;

	Turtle(PApplet p, String s, float l, float t) {
		parent = p;
		todo = s;
		len = l; 
		theta = t;
	} 

	void render() {
		parent.stroke(255);
		for (int i = 0; i < todo.length(); i++) {
			char c = todo.charAt(i);
			if (c == 'F' || c == 'G') {
				parent.line(0,0,len,0);
				parent.translate(len,0);
			} else if (c == '+') {
				parent.rotate(theta);
			} else if (c == '-') {
				parent.rotate(-theta);
			} else if (c == '[') {
				parent.pushMatrix();
			} else if (c == ']') {
				parent.popMatrix();
			}
		} 
	}

	void setLen(float l) {
		len = l; 
	} 

	void changeLen(float percent) {
		len *= percent; 
	}

	void setToDo(String s) {
		todo = s; 
	}

}

