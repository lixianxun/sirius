package com.sap.tut.gcp;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Kayak {
	
	public Kayak() {
		
	}
	
	public Kayak(String n, String o, Number v, String m) {
		this.name = n;
		this.owner = o;
		this.value = v;
		this.makeModel = m;
	}

    private String name;

    private String owner;

    private Number value;

    private String makeModel;

}
