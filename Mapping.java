package com.purdue.LawsonNavigator;

import java.io.*;
import java.util.*;

public class Mapping{

/**
 * 
 */
/**
 * @author sgarriso
 *
 */
	public static HashMap<String, Room> getRoomHashMap(){
		HashMap rooms = new HashMap<String, Room>();
		
		rooms.put("B128", new Room( 15,44,0,"B128","other"));
		rooms.put("B130", new Room( 15,40,0,"B130","other"));
		rooms.put("B136", new Room( 15,33,0,"B136","other"));
		rooms.put("B138", new Room( 15,32,0,"B138","other"));
		rooms.put("1114", new Room( 14,55,1,"1114","other"));
		rooms.put("1118", new Room( 24,55,1,"1118","other"));
		rooms.put("1137", new Room( 14,34,1,"1137","other"));
		rooms.put("1151", new Room( 14,35,1,"1151","other"));
		rooms.put("Topolka_Terrace", new Room( 18,53,3,"Topolka_Terrace","other"));
		rooms.put("3102", new Room( 5,52,3,"3102","other"));
		rooms.put("1123", new Room (14, 48, 1, "1123", "other"));
		
		rooms.put("B105", new Room (10, 52, 0, "B105", "class"));
		rooms.put("B107", new Room (11, 52, 0, "B107", "class"));
		rooms.put("B116", new Room (17, 53, 0, "B116", "class"));
		rooms.put("B129", new Room (14, 43, 0, "B129", "class"));
		rooms.put("B131", new Room (14, 44, 0, "B131", "class"));
		rooms.put("B132", new Room (15, 38, 0, "B132", "class"));
		rooms.put("B134", new Room (15, 34, 0, "B134", "class"));
		rooms.put("B146", new Room (15, 27, 0, "B146", "class"));
		rooms.put("B148", new Room (15, 25, 0, "B148", "class"));
		rooms.put("B151", new Room (14, 24, 0, "B151", "class"));
		rooms.put("B155", new Room (14, 21, 0, "B155", "class"));
		rooms.put("B158", new Room (15, 15, 0, "B158", "class"));
		rooms.put("B160", new Room (15, 7, 0, "B160", "class"));
		rooms.put("1106", new Room (11, 55, 1, "1106", "class"));
		rooms.put("1142", new Room (17, 30, 1, "1142", "class"));
		rooms.put("2121", new Room (13, 49, 2, "2121", "class"));
		rooms.put("2149", new Room (15, 22, 2, "2149", "class"));
		rooms.put("2161", new Room (15, 13, 2, "2161", "class"));
		rooms.put("3133", new Room (15, 37, 3, "3133", "class"));
		rooms.put("3151", new Room (15, 20, 3, "3151", "class"));
		rooms.put("3155", new Room (15, 17, 3, "3155", "class"));
		
		return rooms;
	}
	
	public static HashMap<String, Room> getPRoomHashMap(){
		HashMap prooms = new HashMap<String, Room>();
		
		
		prooms.put("Douglas Comer", new Room (9, 14, 1, "1171" , "professor"));
		prooms.put("Buster Dunsmore", new Room (17,  7, 1, "1189" , "professor"));
		prooms.put("Assefaw Gebremedhin", new Room (22, 18, 1, "1209", "professor"));
		prooms.put("David Gleich", new Room (22, 15, 1, "1207" , "professor"));
		prooms.put("Susanne Hambrusch", new Room (9, 5, 1, "1179" , "professor"));
		prooms.put("Daisuke Kihara", new Room (21, 18, 1, "1208" , "professor"));
		prooms.put("Charles Killian", new Room (15, 7, 1, "1187" , "professor"));
		prooms.put("Ramana Kompella", new Room (22, 10, 1, "1203" , "professor"));
		prooms.put("Aditya Mathur", new Room (9, 6, 1, "1177" , "professor"));
		prooms.put("Kihong Park", new Room (22, 19, 1, "1211" , "professor"));
		prooms.put("Vernon Rego", new Room (9, 9, 1, "1175" , "professor"));
		prooms.put("Robert Skeel", new Room (22, 11, 1, "1205" , "professor"));
		prooms.put("Eugene Spafford", new Room (10,  6, 1, "1183" , "professor"));
		prooms.put("Wojciech Szpankowski", new Room (23, 10, 1, "1201" , "professor"));
		prooms.put("Samuel Wagstaff", new Room (9, 18, 1, "1167" , "professor"));
		prooms.put("Dongyan Xu", new Room (9, 10, 1, "1173" , "professor"));
		prooms.put("David Yau",new Room (8, 18, 1, "1165" , "professor"));
		prooms.put("Gustavo Rodriguez Rivera", new Room (9, 19, 1, "1169" , "professor"));
		prooms.put("Walif Aref", new Room (20, 21, 2, "2142E" , "professor"));
		prooms.put("Mikhail Atallah", new Room (20, 52, 2, "2116D" , "professor"));
		prooms.put("Elisa Bertino", new Room (20, 16, 2, "2142G" , "professor"));
		prooms.put("Bharat Bhargava", new Room (17, 55, 2, "2116F" , "professor"));
		prooms.put("Chris Clifton", new Room (20, 19, 2, "2142F" , "professor"));
		prooms.put("Ahmed Elmagarmid", new Room (20, 29, 2, "2142A" , "professor"));
		prooms.put("Sonia Fahmy", new Room (20, 15, 2, "2142H" , "professor"));
		prooms.put("Greg Frederickson", new Room (19, 53, 2, "2116E" , "professor"));
		prooms.put("Ninghui Li", new Room (20, 10, 2, "2142K" , "professor"));
		prooms.put("Jennifer Neville", new Room (20, 24, 2, "2142D" , "professor"));
		prooms.put("Cristina Nita-Rotaru", new Room (20, 12, 2, "2142J" , "professor"));
		prooms.put("Alex Pothen", new Room (17, 56, 2, "2116G" , "professor"));
		prooms.put("Sunil Prabhakar", new Room (20, 25, 2, "2142C" , "professor"));
		prooms.put("Yuan Qi", new Room (20, 8, 2, "2142L" , "professor"));
		prooms.put("Luo Si", new Room (20, 27, 2, "2142B" , "professor"));
		prooms.put("Daniel Aliaga", new Room (9, 9, 3, "3177" , "professor"));
		prooms.put("Patrik Eugster", new Room (20, 24, 3, "3154N" , "professor"));
		prooms.put("Ananth Grama", new Room (20, 10, 3, "3154G" , "professor"));
		prooms.put("Chris Hoffman", new Room (9 , 5, 3 , "3183" , "professor"));
		prooms.put("Tony Hosking", new Room (20, 19, 3, "3154L" , "professor"));
		prooms.put("Suresh Jagannathan", new Room (20, 15, 3, "3154J" , "professor"));
		prooms.put("Zhiyuan Li", new Room (20, 11, 3, "3154H" , "professor"));
		prooms.put("Voicu Popescu", new Room (9, 8, 3, "3179" , "professor"));
		prooms.put("Elisha Sacks", new Room (9, 6, 3, "3181" , "professor"));
		prooms.put("Faisal Saied", new Room (20, 13, 3, "3154B" , "professor"));
		prooms.put("Ahmed Sameh", new Room (20, 8, 3, "3154F" , "professor"));
		prooms.put("Xavier Tricoche", new Room (25, 20, 3, "3154P" , "professor"));
		prooms.put("Jan Vitek", new Room (20, 20, 3, "3154M" , "professor"));
		prooms.put("Xiangyu Zhang", new Room (20, 16, 3, "3154K" , "professor"));
		
		
		return prooms;
	}
}

