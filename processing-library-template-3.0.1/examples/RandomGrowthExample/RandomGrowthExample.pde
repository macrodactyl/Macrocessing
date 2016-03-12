import macrodactyl.macrocessing.*;
RandomGrowth grower;

void setup() {
  size(500, 500);
  background(0);
  stroke(255);
  
  grower = new RandomGrowth(this);
}

void draw() {
  translate(width/2, height/2);
    
  grower.move();
  grower.draw();
}