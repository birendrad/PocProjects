package com.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
public class Hub {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long hubId;
	@Column
	private String hubName;
	@Column
	private String address;
	
}
