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


	public static void main(String[] args) {
	
		
		
		Room room[] = new Room[89];
		HashMap prooms = new HashMap<String, Room>();
		
		// (int, int, int, string, string)
		// room (x-coordinate,y-coordinate,floor,roomName,roomType)
		// roomType = professor / class / other 
		
		// restroom = special case
		// consider for each floor, if user wants to go to a restroom, choose the restroom on the user's current floor
		// nonAcademic.txt
		room[0] = new Room( 15,44,0,"B128","other");
		room[1] = new Room( 15,40,0,"B130","other");
		room[2] = new Room( 15,33,0,"B136","other");
		room[3] = new Room( 15,32,0,"B138","other");
		room[4] = new Room( 14,55,1,"1114","other");
		room[5] = new Room( 24,55,1,"1118","other");
		room[6] = new Room( 14,34,1,"1137","other");
		room[7] = new Room( 14,35,1,"1151","other");
		room[8] = new Room( 18,53,3,"Topolka_Terrace","other");
		room[9] = new Room( 5,52,3,"3102","other");
		room[31] = new Room (14, 48, 1, "1123", "other");
		
		room[10] = new Room (10, 52, 0, "B105", "class");
		room[11] = new Room (11, 52, 0, "B107", "class");
		room[12] = new Room (17, 53, 0, "B116", "class");
		room[13] = new Room (14, 43, 0, "B129", "class");
		room[14] = new Room (14, 44, 0, "B131", "class");
		room[15] = new Room (15, 38, 0, "B132", "class");
		room[16] = new Room (15, 34, 0, "B134", "class");
		room[17] = new Room (15, 27, 0, "B146", "class");
		room[18] = new Room (15, 25, 0, "B148", "class");
		room[19] = new Room (14, 24, 0, "B151", "class");
		room[20] = new Room (14, 21, 0, "B155", "class");
		room[21] = new Room (15, 15, 0, "B158", "class");
		room[22] = new Room (15, 7, 0, "B160", "class");
		room[23] = new Room (11, 55, 1, "1106", "class");
		room[24] = new Room (17, 30, 1, "1142", "class");
		room[25] = new Room (13, 49, 2, "2121", "class");
		room[26] = new Room (15, 22, 2, "2149", "class");
		room[27] = new Room (15, 13, 2, "2161", "class");
		room[28] = new Room (15, 37, 3, "3133", "class");
		room[29] = new Room (15, 20, 3, "3151", "class");
		room[30] = new Room (15, 17, 3, "3155", "class");
		
		room[32] = new Room (9, 14, 1, "1171" , "professor");
		room[33] = new Room (17,  7, 1, "1189" , "professor");
		room[34] = new Room (22, 18, 1, "1209", "professor");
		room[35] = new Room (22, 15, 1, "1207" , "professor");
		room[36] = new Room (9, 5, 1, "1179" , "professor");
		room[37] = new Room (21, 18, 1, "1208" , "professor");
		room[38] = new Room (15, 7, 1, "1187" , "professor");
		room[39] = new Room (22, 10, 1, "1203" , "professor");
		room[40] = new Room (9, 6, 1, "1177" , "professor");
		room[41] = new Room (22, 19, 1, "1211" , "professor");
		room[42] = new Room (9, 9, 1, "1175" , "professor");
		room[43] = new Room (22, 11, 1, "1205" , "professor");
		room[44] = new Room (10,  6, 1, "1183" , "professor");
		room[45] = new Room (23, 10, 1, "1201" , "professor");
		room[46] = new Room (9, 18, 1, "1167" , "professor");
		room[47] = new Room (9, 10, 1, "1173" , "professor");
		room[48] = new Room (8, 18, 1, "1165" , "professor");
		room[49] = new Room (9, 19, 1, "1169" , "professor");
		room[50] = new Room (20, 21, 2, "2142E" , "professor");
		room[51] = new Room (20, 52, 2, "2116D" , "professor");
		room[52] = new Room (20, 16, 2, "2142G" , "professor");
		room[53] = new Room (17, 55, 2, "2116F" , "professor");
		room[54] = new Room (20, 19, 2, "2142F" , "professor");
		room[55] = new Room (20, 29, 2, "2142A" , "professor");
		room[56] = new Room (20, 15, 2, "2142H" , "professor");
		room[57] = new Room (19, 53, 2, "2116E" , "professor");
		room[58] = new Room (20, 10, 2, "2142K" , "professor");
		room[59] = new Room (20, 24, 2, "2142D" , "professor");
		room[60] = new Room (20, 12, 2, "2142J" , "professor");
		room[61] = new Room (17, 56, 2, "2116G" , "professor");
		room[62] = new Room (20, 25, 2, "2142C" , "professor");
		room[63] = new Room (20, 8, 2, "2142L" , "professor");
		room[64] = new Room (20, 27, 2, "2142B" , "professor");
		room[65] = new Room (9, 9, 3, "3177" , "professor");
		room[66] = new Room (20, 24, 3, "3154N" , "professor");
		room[67] = new Room (20, 10, 3, "3154G" , "professor");
		room[68] = new Room (9 , 5, 3 , "3183" , "professor");
		room[69] = new Room (20, 19, 3, "3154L" , "professor");
		room[70] = new Room (20, 15, 3, "3154J" , "professor");
		room[71] = new Room (20, 11, 3, "3154H" , "professor");
		room[72] = new Room (9, 8, 3, "3179" , "professor");
		room[73] = new Room (9, 6, 3, "3181" , "professor");
		room[74] = new Room (20, 13, 3, "3154B" , "professor");
		room[75] = new Room (20, 8, 3, "3154F" , "professor");
		room[76] = new Room (25, 20, 3, "3154P" , "professor");
		room[77] = new Room (20, 20, 3, "3154M" , "professor");
		room[78] = new Room (20, 16, 3, "3154K" , "professor");
		
		
		prooms.put("Douglas Comer",32);
		prooms.put("Buster Dunsmore",33);
		prooms.put("Assefaw Gebremedhin",34);
		prooms.put("David Gleich",35);
		prooms.put("Susanne Hambrusch",36);
		prooms.put("Daisuke Kihara",37);
		prooms.put("Charles Killian",38);
		prooms.put("Ramana Kompella",39);
		prooms.put("Aditya Mathur",40);
		prooms.put("Kihong Park",41);
		prooms.put("Vernon Rego",42);
		prooms.put("Robert Skeel",43);
		prooms.put("Eugene Spafford",44);
		prooms.put("Wojciech Szpankowski",45);
		prooms.put("Samuel Wagstaff",46);
		prooms.put("Dongyan Xu",47);
		prooms.put("David Yau",48);
		prooms.put("Gustavo Rodriguez Rivera",49);
		prooms.put("Walif Aref",50);
		prooms.put("Mikhail Atallah",51);
		prooms.put("Elisa Bertino",52);
		prooms.put("Bharat Bhargava",53);
		prooms.put("Chris Clifton",54);
		prooms.put("Ahmed Elmagarmid",55);
		prooms.put("Sonia Fahmy",56);
		prooms.put("Greg Frederickson",57);
		prooms.put("Ninghui Li",58);
		prooms.put("Jennifer Neville",59);
		prooms.put("Cristina Nita-Rotaru",60);
		prooms.put("Alex Pothen",61);
		prooms.put("Sunil Prabhakar",62);
		prooms.put("Yuan Qi",63);
		prooms.put("Luo Si",64);
		prooms.put("Daniel Aliaga",65);
		prooms.put("Patrik Eugster",66);
		prooms.put("Ananth Grama",67);
		prooms.put("Chris Hoffman",68);
		prooms.put("Tony Hosking",69);
		prooms.put("Suresh Jagannathan",70);
		prooms.put("Zhiyuan Li",71);
		prooms.put("Voicu Popescu",72);
		prooms.put("Elisha Sacks",73);
		prooms.put("Faisal Saied",74);
		prooms.put("Ahmed Sameh",75);
		prooms.put("Xavier Tricoche",76);
		prooms.put("Jan Vitek",77);
		prooms.put("Xiangyu Zhang",78);
		
		
		
		
		
		
	}
}

