
//an array of shapes (we can put any type of shape in here!)
Shape[] s = new Shape[25];

void setup() {
  size(320,240);
  colorMode(RGB,255,255,255,100);
  smooth();
  
  for (int i = 0; i < s.length; i++) {
   int r = int(random(2));
   //randomly put either circles or squares in our array
   if (r == 0) {
     s[i] = new Circle(160,120,10,color(0,0,200));
   } else {
     s[i] = new Square(160,120,10);
   }
  }
}

void draw() {
  background(0);
  //the magic of polymorphism, we can treat circles and squares 
  //together as shapes, and the right "render" method will be called!
  for (int i = 0; i < s.length; i++) {
    Shape ashape = s[i];
    ashape.jiggle();
    ashape.render();
  }
}

