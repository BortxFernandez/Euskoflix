package Modelo;

public class Tag{
	
	private int nveces;
	private String desc;

	public Tag (int pN,String pDesc) {
		
		nveces=pN;
		desc=pDesc;
	}
	
	public int getNVeces(){
		return nveces;
	}
	
	public String getDesc(){
		return desc;
	}

}

