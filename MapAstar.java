/*	Written by Junyoung Justin Kim		*/
/* 	lawsonNavigator-server				*/
/* 	Map file reading					*/
/* 	Last Update : Nov, 1				*/

//Modifed to test and understand A* by George Brinzea

import java.io.*;
import java.util.*;

public class MapAstar{
	public static int[][] generateGrid(int choice, int col, int row){
		//int col = 27;	// There are 27 columns in the map file
		//int row = 61;	// There is 61 rows in the map file
		int floorB[][] = new int[row][col];	// basement floor
		int floor1[][] = new int[row][col];	// 1st floor
		int floor2[][] = new int[row][col];	// 2nd floor
		int floor3[][] = new int[row][col];	// 3rd floor
		int i, j, temp;	// i, j for array index to reading file
		
		File fileB = new File ("floorB.txt");
		File file1 = new File ("floor1.txt");
		File file2 = new File ("floor2.txt");
		File file3 = new File ("floor3.txt");
		Scanner scanB;
		Scanner scan1;
		Scanner scan2;
		Scanner scan3;
		try {
			scanB = new Scanner (fileB);
			scan1 = new Scanner (file1);
			scan2 = new Scanner (file2);
			scan3 = new Scanner (file3);
			// Reading Basement floor
			i = 0; j = 0;
			while (scanB.hasNextInt()) {
				temp = scanB.nextInt();
				floorB[j][i] = temp;	i++;
				if (i == col){	j++;	i = 0;	}
			}
			// Reading 1st Floor
			i = 0; j = 0;
			while (scan1.hasNextInt()) {
				temp = scan1.nextInt();
				floor1[j][i] = temp;	i++;
				if (i == col){	j++;	i = 0;	}
			}
			// Reading 2nd Floor
			i = 0; j = 0;
			while (scan2.hasNextInt()) {
				temp = scan2.nextInt();
				floor2[j][i] = temp;	i++;
				if (i == col){	j++;	i = 0;	}
			}
			// Reading 3rd Floor
			i = 0; j = 0;
			while (scan3.hasNextInt()) {
				temp = scan3.nextInt();
				floor3[j][i] = temp;	i++;
				if (i == col){	j++;	i = 0;	}
			}
		} catch (Exception e){
			System.err.println("I/O Error.");
			e.printStackTrace();
			System.exit(1);
		}
		
		if(choice == 0){
			return floorB;
		}else if(choice == 1){
			return floor1;
		}else if(choice == 2){
			return floor2;
		}else if(choice == 3){
			return floor3;
		}
		
		return null;
	}
}

