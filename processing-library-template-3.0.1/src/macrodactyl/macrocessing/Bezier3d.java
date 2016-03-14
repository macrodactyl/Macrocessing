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

public class Bezier3d { // should this inherit from Bezier? Seems problematic. Bezier is based on Points, not Point3d's.
	
	PApplet myParent;
	Point3d start, start_control, end_control, end;

	public Bezier3d(PApplet theParent, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4) {
		myParent = theParent;
		
		start = new Point3d(x1, y1, z1);
		start_control = new Point3d(x2, y2, z2);
		end_control = new Point3d(x3, y3, z3);
		end = new Point3d(x4, y4, z4);
	}
	
	/**
	 * Constructs a Bezier3d based on Point3d's. 
	 * 
	 * @param theParent
	 */
	public Bezier3d(PApplet theParent, Point3d start, Point3d start_control, Point3d end_control, Point3d end) {
		
		myParent = theParent;
		
		this.start = start;
		this.start_control = start_control;
		this.end_control = end_control;
		this.end = end;
	}

	/**
	 * Constructs a Bezier3d whose start point, and start-control point, are based
	 * on the tail end of a previous Bezier3d. 
	 * 
	 * @example Bezier3dChain
	 * @param theParent
	 */
	public Bezier3d(PApplet theParent, Bezier3d previous, Point3d end_control, Point3d end) {
		
	    this(
    		theParent,
    		previous.get_end(),

    		new Point3d(
    			get_mirrored_coord(previous.get_end_control().get_x(), previous.get_end().get_x()),
    			get_mirrored_coord(previous.get_end_control().get_y(), previous.get_end().get_y()),
    			get_mirrored_coord(previous.get_end_control().get_z(), previous.get_end().get_z())
    		),

    		end_control,
    		end
	    );
	}
	
	public void draw() {
		myParent.bezier(
			start.get_x(), start.get_y(), start.get_z(),
			start_control.get_x(), start_control.get_y(), start_control.get_z(),
			end_control.get_x(), end_control.get_y(), end_control.get_z(),
			end.get_x(), end.get_y(), end.get_z()
		);
	}
	
	private static float get_mirrored_coord(float coord_to_mirror, float end_coord) {
	    float control_delta = coord_to_mirror - end_coord;
	    
	    float control_coord = end_coord + (-control_delta);

		return control_coord;
	}
	
	public Point3d get_start() {
		return start;
	}
	
	public Point3d get_start_control() {
		return start_control;
	}
	
	public Point3d get_end() {
		return end;
	}
	
	public Point3d get_end_control() {
		return end_control;
	}

}

