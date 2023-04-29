public class Vector2D {
  private float x;
  private float y;

  Vector2D(float x_, float y_) {
    x = x_;
    y = y_;
  }

  float x() {
    return x;
  }

  float y() {
    return y;
  }

  void setX(float x_) {
    x = x_;
  }

  void setY(float y_) {
    y = y_;
  }

  public float magnitude() {
    return (float) Math.sqrt(x*x + y*y);
  }

  public void add(Vector2D v) {
    x += v.x;
    y += v.y;
  }

  public void sub(Vector2D v) {
    x -= v.x;
    y -= v.y;
  }

  public void mult(float n) {
    x *= n;
    y *= n;
  }

  public void div(float n) {
    x /= n;
    y /= n;
  }

  public void normalize() {
    div(magnitude());
  }

  public void limit(float max) {
    if (magnitude() > max) {
      normalize();
      mult(max);
    }
  }

  public float heading() {
    float angle = (float) Math.atan2(-y, x);
    return -1*angle;
  }
  
  public void copy(Vector2D v) {
     x = v.x();
     y = v.y();
  }

  public boolean equals(Vector2D v) {
    if ((x == v.x()) && (y == v.y())) {
      return true;
    } else {
      return false;
    } 
  } 
    
  public static Vector2D add(Vector2D v1, Vector2D v2) {
    Vector2D v = new Vector2D(v1.x() + v2.x(),v1.y() + v2.y());
    return v;
  }

  public static Vector2D sub(Vector2D v1, Vector2D v2) {
    Vector2D v = new Vector2D(v1.x() - v2.x(),v1.y() - v2.y());
    return v;
  }

  public static Vector2D div(Vector2D v1, float n) {
    Vector2D v = new Vector2D(v1.x()/n,v1.y()/n);
    return v;
  }

  public static Vector2D mult(Vector2D v1, float n) {
    Vector2D v = new Vector2D(v1.x()*n,v1.y()*n);
    return v;
  }

  public static float distance (Vector2D v1, Vector2D v2) {
    float dx = v1.x() - v2.x();
    float dy = v1.y() - v2.y();
    return (float) Math.sqrt(dx*dx + dy*dy);
  }



}

