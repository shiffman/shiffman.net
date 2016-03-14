  double xmin = -2.5; double ymin = -2; double wh = 4;

  //double xmin = -1.5; double ymin = -.1; double wh = 0.15;

void setup() {
  size(800,600);
}

void draw() {
  int tmax = 200;

  double xmax = xmin + wh;
  double ymax = ymin + wh;

  double dy = (ymax - ymin) / (height);
  double dx = (xmax - xmin) / (width);

  boolean rev = false;
  double y = ymin;
  for(int j = 0; j < height; j++) {
    double x = xmin;
    for(int i = 0;  i < width; i++) {
      double a = x;
      double b = y;
      int n = 0;
      while (n < tmax) {
        double aa = a * a;
        double bb = b * b;
        double twoab = 2.0 * a * b;
        a = aa - bb + x;
        b = twoab + y;
        rev = false;
        if(aa + bb > 16.0f) {
          break;
        }
        n++;
      }
      
      if (n == tmax)       pixels[i+j*width] = 0;
      else pixels[i+j*width] = color((n * 2) % 255,(n * 9) % 255,(n * 64) % 255);
      x += dx;
    }
    y += dy;
  }
  noLoop();

}

