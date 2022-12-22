package com.app.ems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ems_files")
public class EmsFiles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int file_id;

	private String fileName;

	@Column(columnDefinition = "bytea")
	private byte[] base64image;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return fileName;
	}

	public void setFile_name(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBase64image() {
		return base64image;
	}

	public void setBase64image(byte[] base64image) {
		this.base64image = base64image;
	}

}
