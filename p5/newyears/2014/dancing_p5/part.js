



function Part(a_, b_, s, w) {
	this.a = a_;
	this.b = b_;
	console.log(s,w);
	if (s !== null) {
	  this.img = loadImage('../'+s);
	} else {
		this.img = null;
	}
	this.dir = createVector();
	this.what = w || 'nothing';
}

Part.prototype.display = function() {

	this.dir.x = this.b.p.x - this.a.p.x;
	this.dir.y = this.b.p.y - this.a.p.y;
	var angle = this.dir.heading();

	push();
	if (this.what === "head") {
		translate(this.a.p.x,this.a.p.y);
		imageMode(CENTER);
		rotate(angle-PI/2);
		image(this.img,0,0);
	} else if (this.what === "sign") {
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
	pop();

}

