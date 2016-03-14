import macrodactyl.macrocessing.*;
import java.util.*;

ArrayDeque<Point3d> body = new ArrayDeque<Point3d>();
Bezier3d prev_bez = null;

float camera_angle = HALF_PI;
float camera_x = 0;
float camera_y = -300;
float camera_z = 0;
float radius = 500;
float subject_x = 0;
float subject_y = 0;
float subject_z = 0;

void setup() {
  add_bez_points_to_body();
  add_bez_points_to_body();
  
  size(500, 500, P3D);
  
  background(0);
  stroke(255);
  noFill();
}

void draw() {

  background(0);
  
  move_camera();

  Point3d prev = null;
  Iterator<Point3d> iter = body.iterator();
  int count = 0;
  while (iter.hasNext()) {
    Point3d current = iter.next();
    if (prev != null) {
      line(
        prev.get_x(), 
        prev.get_y(), 
        prev.get_z(),
        current.get_x(),
        current.get_y(),
        current.get_z()
      );
    }
	prev = current;
	count++;
	if (count >= 50) { break; }
  }
  
  body.removeFirst();
  
  if (body.size() < 75) {
  	add_bez_points_to_body();
  }
}

void add_bez_points_to_body() {

  Bezier3d new_bez;

  if (prev_bez == null) {
    new_bez = new Bezier3d(this, 0, 0, 0, 50, 0, 0, 50, 0, 0, 50, 50, 0);
  } else {
    Point3d new_end = new Point3d(
      prev_bez.get_end().get_x() + random(-50, 50), 
      prev_bez.get_end().get_y() + random(-50, 50),
      prev_bez.get_end().get_z() + random(-50, 50)
    );
  	
    Point3d new_control = new Point3d(
      prev_bez.get_end().get_x() + random(-50, 50),
      prev_bez.get_end().get_y() + random(-50, 50),
      prev_bez.get_end().get_z() + random(-50, 50)
    );
  
    new_bez = new Bezier3d(this, prev_bez, new_control, new_end);
  }
  
  for (int i = 0, j = 50; i < j; i++) {
    float t = i / float(j);
    
    float x = bezierPoint(
      new_bez.get_start().get_x(),
      new_bez.get_start_control().get_x(),
      new_bez.get_end_control().get_x(),
      new_bez.get_end().get_x(),
      t
    );
    
    float y = bezierPoint(
      new_bez.get_start().get_y(),
      new_bez.get_start_control().get_y(),
      new_bez.get_end_control().get_y(),
      new_bez.get_end().get_y(),
      t
    );
    
    float z = bezierPoint(
      new_bez.get_start().get_z(),
      new_bez.get_start_control().get_z(),
      new_bez.get_end_control().get_z(),
      new_bez.get_end().get_z(),
      t
    );
    
    body.add(new Point3d(x,y,z));
  }
  
  prev_bez = new_bez;
}

void move_camera() {
  camera_x = radius * cos(camera_angle);
  camera_z = radius * sin(camera_angle);
  
  camera(camera_x, camera_y, camera_z, // camera position
          subject_x, subject_y, subject_z, // pointing at
          0, 1, 0); // choose 'up'
          
}