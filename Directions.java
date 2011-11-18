package com.purdue.LawsonNavigator;

import java.io.*;
import java.util.*;

public class Directions{

/**
 * 
 */
/**
 * @author sgarriso
 *
 */


	public static void main(String[] args) {
	int magic[] = {0,0,1,1};
	ArrayList<String> dir = new ArrayList<String>();
	 dir = directions_to_string(magic);
	for(int i = 0; i < dir.size(); i++)
	System.out.println(dir.get(i));
	}
	
	public static ArrayList<String> directions_to_string( int[] directions){
	ArrayList<String> direction = new ArrayList<String>(); 
	boolean S = false;
	boolean R = false;
	boolean L = false;
	boolean B = false;
	
		for(int i = 0; i < directions.length -1; i++){
			if(directions[i] == directions[i+1] && !S){
				// go stright
				direction.add("Go Straight");
				S = true;
				R = false;
				L = false;
				B = false;
			}
			else if(directions[i] == 0 && directions[i+1] == 1 && !R){
				// go right
				direction.add("Turn Right");
				S = false;
				R = true;
				L = false;
				B = false;
				
			} 
			else if(directions[i] == 0 && directions[i+1] == 3 && !L){
				// go left
				direction.add("Turn Left");
				S = false;
				R = false;
				L = true;
				B = false;
			}
			else if(directions[i] == 1 && directions[i+1] == 2 && !R){
				// go right
				direction.add("Turn Right");
				S = false;
				R = true;
				L = false;
				B = false;
			} 
			else if(directions[i] == 1 && directions[i+1] == 0 && !L){
				// go left
				direction.add("Turn Left");
				S = false;
				R = false;
				L = true;
				B = false;
			}
			else if(directions[i] == 2 && directions[i+1] == 3 && !R){
				// go right
				direction.add("Turn Right");
				S = false;
				R = true;
				L = false;
				B = false;
			} 
			else if(directions[i] == 2 && directions[i+1] == 1 && !L){
				// go left
				direction.add("Turn Left");
				S = false;
				R = true;
				L = false;
				B = false;
			}
			else if(directions[i] == 3 && directions[i+1] == 0 && !R){
				// go right
				direction.add("Turn Right");
				S = false;
				R = true;
				L = false;
				B = false;
			} 
			else if(directions[i] == 3 && directions[i+1] == 2 && !L){
				// go left
				direction.add("Turn Left");
				S = false;
				R = false;
				L = true;
				B = false;
			}
			else if(directions[i] == 0 && directions[i+1] == 2 && !B){
				// go back
				direction.add("Go Back");
				S = false;
				R = false;
				L = false;
				B = true;
			}
			else if(directions[i] == 3 && directions[i+1] == 1  && !B){
				// go back
				direction.add("Go Back");
				S = false;
				R = false;
				L = false;
				B = true;
			}
			else if(directions[i] == 2 && directions[i+1] == 0 && !B){
				// go back
				direction.add("Go Back");
				S = false;
				R = false;
				L = false;
				B = true;
			}
			else if(directions[i] == 1 && directions[i+1] == 3 && !B){
				// go back
				direction.add("Go Back");
				S = false;
				R = false;
				L = false;
				B = true;
			}
		}
	return direction;
	} 
}
