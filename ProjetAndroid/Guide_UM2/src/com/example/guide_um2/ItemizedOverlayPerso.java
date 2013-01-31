package com.example.guide_um2;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;

public class ItemizedOverlayPerso extends ItemizedOverlay<OverlayItem> {	
	private List<GeoPoint> points = new ArrayList<GeoPoint>();
	public ItemizedOverlayPerso(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

protected OverlayItem createItem(int i) {
		GeoPoint point = points.get(i);
		return new OverlayItem(point,"Titre", "Description");
	}

public int size() {
		return points.size();
	}

	public void addPoint(GeoPoint point) {
		this.points.add(point);
		populate();
	}

	public void clearPoint() {
		this.points.clear();
		populate();
	}
}