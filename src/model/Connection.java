package model;

public class Connection {

	private String id1;
	private String id2;
	
	public Connection(String id1, String id2){
		this.id1 = id1;
		this.id2 = id2;
	}
	
	public String getID1(){
		return id1;
	}
	
	public String getID2(){
		return id2;
	}
}
