/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package macrodactyl.macrocessing;

import java.util.*;
import processing.core.*;
import shapes3d.*;
import shapes3d.animation.*;
import shapes3d.utils.*;

public class XYZWorm {
	
	PApplet myParent;
	ArrayDeque<Point3d> body;
	
	public XYZWorm(PApplet theParent) {
		myParent = theParent;
		this.body = new ArrayDeque<Point3d>();
		this.fillWithPoints();
	}
	
	private void fillWithPoints() {
		for (int i = 0, j = 10; i < j; i++) {
			this.body.add(new Point3d(0,0,0));
		}
	}
	
	public void move() {
		this.body.removeFirst();
		this.body.add(this.get_next(body.getLast()));
	}
	
	private Point3d get_next(Point3d from_here) {
		
		final int DIRECTIONS = 6;
		
		float speed = 10;
		
		float x = from_here.get_x();
		float y = from_here.get_y();
		float z = from_here.get_z();
		
		int direction = java.util.concurrent.ThreadLocalRandom.current().nextInt(DIRECTIONS);
		
		switch (direction) {
			case 0:
		        x += speed;
		        break;
			case 1:
				x -= speed;
			    break;      
			case 2:
			    y += speed;
			    break;      
			case 3:
			    y -= speed;
			    break;      
			case 4:
			    z += speed;
			    break;      
			case 5:
			    z -= speed;
			    break;
		}
		
		return new Point3d(x,y,z);
	}
	
	public void draw() {
		Point3d prev = null;
		Iterator<Point3d> iter = body.iterator();
		while (iter.hasNext()) {
			Point3d current = iter.next();
			if (prev != null) {
				Tube tube = new Tube(myParent, 1, 3);
				tube.setSize(1,1,1,1);
				tube.fill(myParent.color(255, 0, 0));
				tube.drawMode(Shape3D.SOLID);
				tube.setWorldPos(prev.get_x(), prev.get_y(), prev.get_z(), current.get_x(), current.get_y(), current.get_z());
				tube.draw();
			}
			prev = current;
		}
	}
	
}