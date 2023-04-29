void drawVector(Vector3D v, Vector3D loc, float scayl) {
  push();
  if (v.magnitude() > 0.0) {
    float arrowsize = 5;
    //translate to location to render vector
    translate(loc.x(),loc.y());
    stroke(255);
    //call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
    rotate(v.heading2D());
    //calculate length of vector & scale it to be bigger or smaller if necessary
    float len = v.magnitude()*scayl;
    //draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
    line(0,0,len,0);
    line(len,0,len-arrowsize,+arrowsize/2);
    line(len,0,len-arrowsize,-arrowsize/2);
  }
  pop();
}

void drawWanderStuff(Vector3D loc, Vector3D circle, Vector3D target, float R) {
  stroke(255); noFill();
  ellipseMode(CENTER);
  ellipse(circle.x(),circle.y(),R*2,R*2);
  ellipse(target.x(),target.y(),4,4);
  line(loc.x(),loc.y(),circle.x(),circle.y());
  line(circle.x(),circle.y(),target.x(),target.y());
}
