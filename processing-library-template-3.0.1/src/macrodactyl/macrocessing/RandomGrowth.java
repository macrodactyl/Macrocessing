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

/**
 * @example RandomGrowth 
 */

public class RandomGrowth {
	
	// myParent is a reference to the parent sketch
	PApplet myParent;
	ArrayDeque<Point> body;

	public RandomGrowth(PApplet theParent) {
		myParent = theParent;
		body = new ArrayDeque<Point>();
		Point start = new Point(0,0);
		body.add(start);
	}
	
	public void move() {
		Point next = body.getLast().get_nearby_point();
		body.add(next);
	}
	
	public void draw() {
		
		Point prev = null;
		Iterator<Point> iter = body.iterator();
		
		while (iter.hasNext()) {
			
			Point current = iter.next();
						
			if (prev != null) {
				// draw body part
				myParent.line(prev.get_x(), prev.get_y(), current.get_x(), current.get_y());
			}
			
			prev = current;
		}
	}
	
}

