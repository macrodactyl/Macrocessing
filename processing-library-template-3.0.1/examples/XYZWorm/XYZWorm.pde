import macrodactyl.macrocessing.*;

int creatureCount = 100;
float speed = 5;
XYZWorm[] creatures = new XYZWorm[creatureCount];

float camera_angle = HALF_PI;
float camera_x = 0;
float camera_y = -300;
float camera_z = 0;
float radius = 500;

float subject_x = 0;
float subject_y = 0;
float subject_z = 0;

void setup() {
  //size(1000, 500, P3D);
  fullScreen(P3D);
  
  for (int i = 0; i < creatureCount; i++) {
    creatures[i] = new XYZWorm(this);
  }
}

void draw() {
  //delay(125);
  
  move_camera();
  
  background(0);
  
  for (int i = 0; i < creatureCount; i++) {
    creatures[i].move();
    creatures[i].draw();
  }  
  
  draw_cubes();
}

void move_camera() {
  camera_x = radius * cos(camera_angle);
  camera_z = radius * sin(camera_angle);
  
  camera(camera_x, camera_y, camera_z, // camera position
          subject_x, subject_y, subject_z, // pointing at
          0, 1, 0); // choose 'up'
          
  camera_angle += 0.003;
}

void draw_cubes() {
  stroke(255);
  noFill();
  box(200);
  
  translate(100, 100, 100);
  box(50);
  translate(-200, -200, -200);
  box(75);
}