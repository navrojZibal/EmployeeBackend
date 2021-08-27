package com.example.demo;

import org.springframework.data.annotation.Id;

public class ImageModel {

	@Id
	private String id;
	
	private String type;
	
	private String name;
	
	private byte[] picByte;
	
	public ImageModel() {}
	
	public ImageModel(String name, String type,byte[] picByte) {
		super();
		
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
