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


import java.util.concurrent.ThreadLocalRandom;
import processing.core.*;

public class Point {
	
	float xpos, ypos;	

	public Point(float x, float y) {
		xpos = x;
		ypos = y;
	}
	
	public float get_x() {
		return xpos;
	}
	
	public float get_y() {
		return ypos;
	}
	
	public Point get_nearby_point() {
		
		float min_delta = -10;
		float max_delta = 10;
		
		float new_x = this.xpos + (ThreadLocalRandom.current().nextFloat() * (max_delta - min_delta) + min_delta);
		float new_y = this.ypos + (ThreadLocalRandom.current().nextFloat() * (max_delta - min_delta) + min_delta);
		
		return new Point(new_x, new_y);
	}
}

