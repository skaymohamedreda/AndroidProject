package com.example.chemin;

import java.util.Iterator;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Path;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class RouteOverlay extends Overlay {
	
	private Route route;	
	
	private int colour;
	
	private static int ALPHA = 120;
	
	private static float STROKE = 3.5f;
	
	private Path path;
	
	private Point point;
	
	private Paint paint;
	

	public RouteOverlay(Route route, int defaultColour) {
		super();
		this.route=route;		
		colour = defaultColour;
		path = new Path();
		point = new Point();
		paint = new Paint();
	}

	@Override
	public void draw(Canvas c, MapView mv,
			boolean shadow) {
		super.draw(c, mv, shadow);
		paint.setColor(colour);
		paint.setAlpha(ALPHA);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(STROKE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(4);
		c.drawPath(path, paint);
		drawRoute(mv);
	}
	
	public void setColour(int c) {
		colour = c;
	}

	/** * Clear the route overlay. */
	public void clear() {
		route.getPoints().clear();
	}	
	
	private void drawRoute(MapView mv) {
		Projection prj = mv.getProjection();
		path.rewind();
		List<GeoPoint> rPoints=route.getPoints();
		Iterator<GeoPoint> it = rPoints.iterator();

		prj.toPixels(it.next(), point);

		path.moveTo(point.x, point.y);
		while (it.hasNext()) {
			prj.toPixels(it.next(), point);
			path.lineTo(point.x, point.y);
		}
		path.setLastPoint(point.x, point.y);
	}
}