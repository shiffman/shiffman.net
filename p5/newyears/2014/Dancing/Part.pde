

class Part {
	PImage img;
	Particle a;
	Particle b;

	PVector dir;

	String what;


	Part(Particle a_, Particle b_, String s, String w) {
		a = a_;
		b = b_;
		if (s != null) {
		  img = loadImage("http://shiffman.net/p5/newyears/2014/"+s);
		}
		dir = new PVector();
		what = w;
	}



	Part(Particle a_, Particle b_, String s) {
		a = a_;
		b = b_;
		img = loadImage(s);
		dir = new PVector();
		what = "nothing";
	}


	void display() {

		dir.x = b.p.x - a.p.x;
		dir.y = b.p.y - a.p.y;
			float angle = dir.heading2D();

		pushMatrix();
		if (what.equals("head")) {
			translate(a.p.x,a.p.y);
			imageMode(CENTER);
			rotate(angle-PI/2);
			image(img,0,0);
		} else if (what.equals("sign")) {
			/*translate(b.p.x,b.p.y);
			stroke(0);
			rotate(angle);
			strokeWeight(8);
			line(0,0,0,-50);
			rectMode(CENTER);
			fill(255);
			strokeWeight(1);
			rect(0,-50,100,50);
			fill(0);
			textAlign(LEFT);
			textSize(24);
			text("2014",0,-50);*/
		}
		popMatrix();

	}

}