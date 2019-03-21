package scm.model;

import java.io.Serializable;

/**
 * 
 * 对应权限表systemmodel
 * 
 */
public class Jurisdiction_c implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String modeCode;
	private String modeName;
	
	public Jurisdiction_c() {}

	public String getModeCode() {
		return modeCode;
	}

	public void setModeCode(String modeCode) {
		this.modeCode = modeCode;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	
	
}
