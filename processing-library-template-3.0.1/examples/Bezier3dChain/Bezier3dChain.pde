import macrodactyl.macrocessing.*;
import java.util.*;

Bezier3d bez = new Bezier3d(this, 0, 0, 0, 100, 0, 0, 100, 0, 0, 100, 100, 0);
ArrayDeque<Bezier3d> bez_list = new ArrayDeque<Bezier3d>();
int frameCount = 0;

float camera_angle = HALF_PI;
float camera_x = 0;
float camera_y = -300;
float camera_z = 0;
float radius = 500;
float subject_x = 0;
float subject_y = 0;
float subject_z = 0;

void setup() {
  size(500, 500, P3D);
  
  background(0);
  stroke(255);
  noFill();
  
  bez_list.add(bez);
}

void draw() {

  background(0);

  frameCount++;
  
  move_camera();
  
  //translate(width/2, height/2);
  
  if (frameCount % 100 == 0) {
    add_new_bez();
  }
  
  Iterator<Bezier3d> iter = bez_list.iterator();
  while (iter.hasNext()) {
    Bezier3d draw_this_bez = iter.next();
    draw_this_bez.draw();
  }
}

void add_new_bez() {
  Point3d current_end = bez_list.getLast().get_end();
  
  Point3d new_end = new Point3d(
  	current_end.get_x() + random(-50, 50), 
  	current_end.get_y() + random(-50, 50),
  	current_end.get_z() + random(-50, 50)
  );
  	
  Point3d new_control = new Point3d(
  	current_end.get_x() + random(-50, 50),
  	current_end.get_y() + random(-50, 50),
  	current_end.get_z() + random(-50, 50)
  );
  
  Bezier3d new_bez = new Bezier3d(this, bez_list.getLast(), new_control, new_end);
  bez_list.add(new_bez);
}

void move_camera() {
  camera_x = radius * cos(camera_angle);
  camera_z = radius * sin(camera_angle);
  
  camera(camera_x, camera_y, camera_z, // camera position
          subject_x, subject_y, subject_z, // pointing at
          0, 1, 0); // choose 'up'
          
  camera_angle += 0.003;
}