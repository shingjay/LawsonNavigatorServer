/*
Data.java
Written by George Brinzea

Used to test sending data to and from a server
Not a part of the final project build
*/

package com.purdue.LawsonNavigator;

import java.io.Serializable;

public class Data implements Serializable{
	private int value1;
	private boolean value2;
	private char value3;
	
	
	public Data(int v1, boolean v2, char v3){
		value1 = v1;
		value2 = v2;
		value3 = v3;
	}
	
	public int getV1(){
		return value1;
	}
	
	public boolean getV2(){
		return value2;
	}
	
	public char getV3(){
		return value3;
	}
	
	/*private void writeObject(java.io.ObjectOutputStream out) throws IOException{
		out.defaultWriteObject();
	}
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
	}
	private void readObjectNoData() throws ObjectStreamException{
	}*/
}
