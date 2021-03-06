/*
 *
 * (c)2010 Lein-Mathisen Digital
 * http://lmdig.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.  
 *
 */



package com.lmdig.android.tutorial.oglbox2dbasics.geometry;

import org.jbox2d.common.Vec2;


import com.kristianlm.robotanks.box2dbridge.IBody;
import com.kristianlm.robotanks.box2dbridge.IShape;

public class GameShapeRectangle extends GameShape {
	
	
	GLRectangle rect;

	
	
	public GameShapeRectangle(GLRectangle r) {
		rect = r;
	}
	
	
	/**
	 * Attach the shape to the body
	 * 
	 * @param body
	 * @param position Shape's placment relative to body. 0,0 is at 
	 * the body's center, which is what you usually want. Exceptions 
	 * include the ground-body, which doesn't move @ 0,0.  
	 */
	public IShape attachToBody(IBody body, Vec2 position, float density) {
		
		if(this.shape != null) {
			throw new RuntimeException("Shape already attached");
		}
		
		if(position == null)
			position = new Vec2(0, 0);
		
		shapePosition = position;
		
		this.body = body;
		shape = body.createBox(
					rect.getHalfWidth(), 
					rect.getHalfHeight(), 
					position.x, 
					position.y, 
					density, 
					0
				);
		

		body.setMassFromShapes();
		return shape;
	}
	
	
	@Override
	public void draw() {
		Vec2 p = body.getWorldCenter().add(shapePosition);
		
		rect.draw(p.x, p.y, body.getAngle());
	}

}
