package edu.bionic.model;

import java.io.Serializable;

public interface DirtyHackForPaging<PK extends Serializable> {

	public PK getId();
	public void setId(PK id);

}
