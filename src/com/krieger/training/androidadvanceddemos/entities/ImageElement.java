package com.krieger.training.androidadvanceddemos.entities;

public class ImageElement{
	private String titulo = "";
	private String url = "";
	
	public ImageElement(String titulo, String url) {
		super();
		this.titulo = titulo;
		this.url = url;
	}

	public String getTitulo(){
		return this.titulo;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url=url;
	}
	
	public String getImage(){
		return this.url;
	}
}
