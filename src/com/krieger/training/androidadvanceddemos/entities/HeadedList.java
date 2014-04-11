package com.krieger.training.androidadvanceddemos.entities;

import java.util.ArrayList;

public class HeadedList <HeaderType, ItemType>{
	private HeaderType header;
	private ArrayList<ItemType> contents;
	
	public HeadedList(){}
	
	public HeadedList(HeaderType header, ArrayList<ItemType> contents){
		this.header=header;
		this.contents=contents;
	}
	
	public void setHeader(HeaderType header) {
		this.header = header;
	}

	public void setContents(ArrayList<ItemType> contents) {
		this.contents = contents;
	}

	public HeaderType getHeader() {
		return header;
	}
	
	public ArrayList<ItemType> getContents() {
		return contents;
	}
}
