package com.essentailab.training.androidadvanceddemos.entities;

import android.graphics.drawable.Drawable;

public class ListElementCookie {

	private int elementId;
	private Drawable thumb;
	private String title;
	private String description;
	
	public ListElementCookie(int elementId, Drawable thumb, String title,
			String description) {
		super();
		this.elementId = elementId;
		this.thumb = thumb;
		this.title = title;
		this.description = description;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public Drawable getThumb() {
		return thumb;
	}

	public void setThumb(Drawable thumb) {
		this.thumb = thumb;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
