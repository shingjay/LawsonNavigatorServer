/*
Server.java
Written by George Brinzea

Handles i/o to and from the phone client
Calculates path data
*/

package com.purdue.LawsonNavigator;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.imageio.stream.FileImageInputStream;
//import javax.imageio.ImageIO;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import javax.swing.ImageIcon;
//import java.awt.Graphics2D;

public class Server {
	
	static int PORT = 9999;
	
	static Socket clientSocket = null;
	
	static class Connection extends Thread{
		public void run(){
			Socket threadedSocket = clientSocket;
			
			HashMap rooms = Mapping.getRoomHashMap();
			HashMap prooms = Mapping.getPRoomHashMap();
			
			PrintWriter out = null;
			ObjectOutputStream oos = null;
			ObjectInputStream ois = null;
			BufferedReader in = null;
			OutputStream os = null;
			try {
				out = new PrintWriter(threadedSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(threadedSocket.getInputStream()));
				oos = new ObjectOutputStream(threadedSocket.getOutputStream());
				ois = new ObjectInputStream(threadedSocket.getInputStream());
				os = threadedSocket.getOutputStream();
			} catch (IOException e) {
				System.err.println("I/O stream creation error.");
				e.printStackTrace();
				System.exit(1);
			}
	        	
	        	UserInput ui = null;
	        	
	        	//Takes in the UserInput class from the client
	        try{
	        	ui = (UserInput) ois.readObject();
	        	System.out.println("Recieved object.");
	        }catch(Exception e){
	        	System.err.println("Object input error.");
	        	e.printStackTrace();
				System.exit(1);
			}
	        	
	        System.out.println("Transport: " + ui.getTransport() + "\nFloor: " + ui.getFloor() + "\nLatitude: " + ui.getLatitude() + "\nLongitude: " + ui.getLongitude() + "\nDisplay Option: " + ui.getDisplayOption() + "\nroomNumber: " + ui.getRoomNumber() + "\nnonAcademicRoom: " + ui.getNonAcademicRoom() + "\nprofessorName: " + ui.getProfessorName() + "\n");
			
			//Process an image or test based on the user input//

			int beginFloor;
			
			if(ui.getFloor() == Floor.BASEMENT){
				beginFloor = 0;
			}else if(ui.getFloor() == Floor.FIRST){
				beginFloor = 1;
			}else if(ui.getFloor() == Floor.SECOND){
				beginFloor = 2;
			}else{
				beginFloor = 3;
			}
			
			int endFloor;
			
			if(ui.getRoomNumber() != null){
				endFloor = ((Room) rooms.get(ui.getRoomNumber())).getFloor();
			}else if(ui.getNonAcademicRoom() != null){
				endFloor = ((Room) rooms.get(ui.getNonAcademicRoom())).getFloor();
			}else{
				endFloor = ((Room) prooms.get(ui.getProfessorName())).getFloor();
			}
			
			System.out.println("Beginning floor: " + beginFloor + " Ending floor: " + endFloor);
			
			int start1;
			int start2;
			
			int end1;
			int end2;
			
			ArrayList<String> textDirections1 = new ArrayList<String>();
			ArrayList<Byte> images1 = new ArrayList<Byte>();
			ArrayList<Point> points1 = new ArrayList<Point>();
			
			ArrayList<String> textDirections2 = new ArrayList<String>();
			ArrayList<Byte> images2 = new ArrayList<Byte>();
			ArrayList<Point> points2 = new ArrayList<Point>();
			
			if(beginFloor != endFloor){
				int[] northStairs = {3,19}; //0
				int[] southStairs = {49,4}; //1
				int[] elevator = {29,11}; //2
				int choice = -1;
				
				////////////////First Floor/////////////////
				start1 = ConvertAndDraw.yGPStoArray(ui.getLatitude())-1;
				start2 = ConvertAndDraw.xGPStoArray(ui.getLongitude())-1;
				
				if(ui.getTransport() == Transport.ELEVATOR){
					end1 = elevator[0]; end2 = elevator[1];
					choice = 2;
				}else{
					if(ui.getRoomNumber() != null){
						end1 = ((Room) rooms.get(ui.getRoomNumber())).getY()-1;
						//end2 = ((Room) rooms.get(ui.getRoomNumber())).getX()-1;
					}else if(ui.getNonAcademicRoom() != null){
						end1 = ((Room) rooms.get(ui.getNonAcademicRoom())).getY()-1;
						//end2 = ((Room) rooms.get(ui.getNonAcademicRoom())).getX()-1;
					}else{
						end1 = ((Room) prooms.get(ui.getProfessorName())).getY()-1;
						//end2 = ((Room) prooms.get(ui.getProfessorName())).getX()-1;
					}
					
					if(end1 < 31){
						end1 = southStairs[0]; end2 = southStairs[1];
						choice = 1;
					}else{
						end1 = northStairs[0]; end2 = northStairs[1];
						choice = 2;
					}
				}
				
				System.out.println("\nMultiple images, First Floor\nStart1: " + start1 + " Start2: " + start2 + " End1: " + end1 + " End2: " + end2);
				
				int temp[] = RunMaze.Run(start1, start2, end1, end2, beginFloor);
				
				for(int i = 0; i < temp.length; i ++){
					System.out.print(temp[i] + ", ");
				}
				
				System.out.println();
				
				if(ui.getDisplayOption() == Display.MAP){
					ConvertAndDraw.lineDrawer(start2, start1, temp, beginFloor);
					
					System.out.println("Done with lineDrawer");
					
					String filename = "out-" + beginFloor + ".bmp";
					
					FileImageInputStream fis = null;
					
					try{
						fis = new FileImageInputStream(new File(filename));
					}catch(Exception e){
						System.err.println("Error opening fis.");
					}
					
					System.out.println("Opened the file: " + filename);
					
					byte tempByte;
					int tempInt;
					long tempLength;
					
					try{
						tempInt = fis.read();
						
						//System.out.print(tempInt + ", ");
					
						while(tempInt != -1){
							images1.add(new Byte((byte)tempInt));
							tempInt = fis.read();
							//System.out.print(tempInt + ", ");
						}
						
						System.out.println("\nimages1.size: " + images1.size());
						
					}catch(Exception e){
						System.err.println("Error reading bytes from file.");
						System.exit(1);
					}
					
				}else{
					textDirections1 = Directions.directions_to_string(temp);
					points1 = Point.getTrigger(start2, start1, temp);
					
					for(int i = 0; i < textDirections1.size(); i++){
						System.out.println(textDirections1.get(i));
					}
					System.out.println();
					
					for(int i = 0; i < points1.size(); i++){
						System.out.println(points1.get(i).getx() + " " + points1.get(i).gety());
					}
				}
				
				////////////////Second Floor/////////////////
				
				if(choice == 0){
					start1 = northStairs[0]; start2 = northStairs[1];
				}else if(choice == 1){
					start1 = southStairs[0]; start2 = southStairs[1];
				}else if(choice == 2){
					start1 = elevator[0]; start2 = elevator[1];
				}else{
					System.out.println("Something wrong in stair/elevator choice");
					System.exit(1);
				}
				
				if(ui.getRoomNumber() != null){
					end1 = ((Room) rooms.get(ui.getRoomNumber())).getY()-1;
					end2 = ((Room) rooms.get(ui.getRoomNumber())).getX()-1;
				}else if(ui.getNonAcademicRoom() != null){
					end1 = ((Room) rooms.get(ui.getNonAcademicRoom())).getY()-1;
					end2 = ((Room) rooms.get(ui.getNonAcademicRoom())).getX()-1;
				}else{
					end1 = ((Room) prooms.get(ui.getProfessorName())).getY()-1;
					end2 = ((Room) prooms.get(ui.getProfessorName())).getX()-1;
				}
					
				System.out.println("\nMultiple images, Second Floor\nStart1: " + start1 + " Start2: " + start2 + " End1: " + end1 + " End2: " + end2);
				
				int temp2[] = RunMaze.Run(start1, start2, end1, end2, endFloor);
				
				for(int i = 0; i < temp2.length; i ++){
					System.out.print(temp2[i] + ", ");
				}
				
				System.out.println();
				
				if(ui.getDisplayOption() == Display.MAP){
					ConvertAndDraw.lineDrawer(start2, start1, temp2, endFloor);
					
					System.out.println("Done with lineDrawer");
					
					String filename = "out-" + endFloor + ".bmp";
					
					FileImageInputStream fis = null;
					
					try{
						fis = new FileImageInputStream(new File(filename));
					}catch(Exception e){
						System.err.println("Error opening fis.");
					}
					
					System.out.println("Opened the file: " + filename);
					
					byte tempByte;
					int tempInt;
					long tempLength;
					
					try{
						tempInt = fis.read();
						
						//System.out.print(tempInt + ", ");
					
						while(tempInt != -1){
							images2.add(new Byte((byte)tempInt));
							tempInt = fis.read();
							//System.out.print(tempInt + ", ");
						}
						
						System.out.println("\nimages2.size: " + images2.size());
						
					}catch(Exception e){
						System.err.println("Error reading bytes from file.");
						System.exit(1);
					}
					
				}else{
					textDirections2 = Directions.directions_to_string(temp2);
					points2 = Point.getTrigger(start2, start1, temp2);
					
					for(int i = 0; i < textDirections1.size(); i++){
						System.out.println(textDirections2.get(i));
					}
					System.out.println();
					
					for(int i = 0; i < points2.size(); i++){
						System.out.println(points2.get(i).getx() + " " + points2.get(i).gety());
					}
				}
			}else{//////////Only a single floor/////////////
				start1 = ConvertAndDraw.yGPStoArray(ui.getLatitude())-1;
				start2 = ConvertAndDraw.xGPStoArray(ui.getLongitude())-1;
				
				if(ui.getRoomNumber() != null){
					end1 = ((Room) rooms.get(ui.getRoomNumber())).getY()-1;
					end2 = ((Room) rooms.get(ui.getRoomNumber())).getX()-1;
				}else if(ui.getNonAcademicRoom() != null){
					end1 = ((Room) rooms.get(ui.getNonAcademicRoom())).getY()-1;
					end2 = ((Room) rooms.get(ui.getNonAcademicRoom())).getX()-1;
				}else{
					end1 = ((Room) prooms.get(ui.getProfessorName())).getY()-1;
					end2 = ((Room) prooms.get(ui.getProfessorName())).getX()-1;
				}
				
				System.out.println("Start1: " + start1 + " Start2: " + start2 + " End1: " + end1 + " End2: " + end2);
				
				int temp[] = RunMaze.Run(start1, start2, end1, end2, beginFloor);
				
				//System.out.println("Direction array: " + temp);
				
				for(int i = 0; i < temp.length; i ++){
					System.out.print(temp[i] + ", ");
				}
				
				System.out.println();
				
				if(ui.getDisplayOption() == Display.MAP){
					ConvertAndDraw.lineDrawer(start2, start1, temp, beginFloor);
					
					System.out.println("Done with lineDrawer");
					
					String filename = "out-" + endFloor + ".bmp";
					
					FileImageInputStream fis = null;
					
					try{
						fis = new FileImageInputStream(new File(filename));
					}catch(Exception e){
						System.err.println("Error opening fis.");
					}
					
					System.out.println("Opened the file: " + filename);
					
					byte tempByte;
					int tempInt;
					long tempLength;
					
					try{
						tempInt = fis.read();
						
						//System.out.print(tempInt + ", ");
					
						while(tempInt != -1){
							images1.add(new Byte((byte)tempInt));
							tempInt = fis.read();
							//System.out.print(tempInt + ", ");
						}
						
						/*tempLength = fis.length();
						
						for(int i = 0; i < tempLength; i++){
							tempInt = fis.read();
							//System.out.print(Integer.valueOf(tempInt).byteValue() + ", ");
							images1.add(new Byte(Integer.valueOf(tempInt).byteValue()));
						}*/
						
						//System.out.println("\ntempLength: " + tempLength);
						System.out.println("\nimages1.size: " + images1.size());
						
					}catch(Exception e){
						System.err.println("Error reading bytes from file.");
						System.exit(1);
					}
					
					/*Byte images1Array[] = new Byte[images1.size()];
			
					for(int i = 0; i < images1Array.length; i++){
						images1Array[i] = images1.get(i);
						System.out.print(images1Array[i] + ", ");
					}*/
					
					/*System.out.println();
					int j = 0;
					
					System.out.println("images1 size: " + images1.size());
					System.out.println("images11 size: " + images11.size() + "images12 size: " + images12.size() + "images13 size: " + images13.size() + "images14 size: " + images14.size() + "images15 size: " + images15.size());*/
				}else{
					textDirections1 = Directions.directions_to_string(temp);
					points1 = Point.getTrigger(start2, start1, temp);
					
					for(int i = 0; i < textDirections1.size(); i++){
						System.out.println(textDirections1.get(i));
					}
					System.out.println();
					
					for(int i = 0; i < points1.size(); i++){
						System.out.println(points1.get(i).getx() + " " + points1.get(i).gety());
					}
				}
			}
	
			try{	
				oos.writeObject(images1);
				oos.writeObject(textDirections1);
				oos.writeObject(points1);
				
				oos.writeObject(images2);
				oos.writeObject(textDirections2);
				oos.writeObject(points2);
			}catch(Exception e){
				System.err.println("Error sending data to client.");
			}
			
			/*try {
				out.close();
				in.close();
				oos.close();
				ois.close();
				clientSocket.close();
			}catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}*/
			
			System.out.println("Thread has completed.");	
		}
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
        	try {
            		serverSocket = new ServerSocket(PORT);
        	} catch (IOException e) {
           		System.err.println("Could not listen on port: " + PORT + ".");
           		System.exit(1);
       		}
        
        	Connection connection = null;
        	try {
        		while(true){
        			clientSocket = serverSocket.accept();
        			connection = new Connection();
        			connection.start();
				
            			System.out.println("Made a new connetion.");
        		}
        	} catch (IOException e) {
           		System.err.println("Connection accept failed.");
            		System.exit(1);
        	}
	}
}
