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


import processing.core.*;

public class Bezier {
	
	PApplet myParent;
	Point start, start_control, end_control, end;

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the library.
	 * 
	 * @param theParent
	 */
	public Bezier(PApplet theParent, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		myParent = theParent;
		
		start = new Point(x1, y1);
		start_control = new Point(x2, y2);
		end_control = new Point(x3, y3);
		end = new Point(x4, y4);
	}
	
	public Bezier(PApplet theParent, Point start, Point start_control, Point end_control, Point end) {
		myParent = theParent;
		
		this.start = start;
		this.start_control = start_control;
		this.end_control = end_control;
		this.end = end;
	}

	/**
	 * Constructs a Bezier whose start point, and start-control point, are based
	 * on the tail end of a previous Bezier. 
	 * 
	 * @param theParent
	 */
	public Bezier(PApplet theParent, Bezier previous, float x3, float y3, float x4, float y4) {
		
	    this(
    		theParent,
    		previous.get_end().get_x(), previous.get_end().get_y(),

    		get_mirrored_coord(previous.get_end_control().get_x(), previous.get_end().get_x()),
    		get_mirrored_coord(previous.get_end_control().get_y(), previous.get_end().get_y()),

    		x3, y3,
    		x4, y4
	    );
	}
	
	private static float get_mirrored_coord(float coord_to_mirror, float end_coord) {
	    float control_delta = coord_to_mirror - end_coord;
	    
	    float control_coord = end_coord + (-control_delta);

		return control_coord;
	}
	
	public Point get_start() {
		return start;
	}
	
	public Point get_start_control() {
		return start_control;
	}
	
	public Point get_end() {
		return end;
	}
	
	public Point get_end_control() {
		return end_control;
	}
	
	public void draw() {
		myParent.bezier(
			start.get_x(), start.get_y(),
			start_control.get_x(), start_control.get_y(),
			end_control.get_x(), end_control.get_y(),
			end.get_x(), end.get_y()
		);
	}
}

