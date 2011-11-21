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
//import javax.imageio.ImageIO;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import javax.swing.ImageIcon;
//import java.awt.Graphics2D;

public class Server {
	
	static int PORT = 4444;
	
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
			try {
				out = new PrintWriter(threadedSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(threadedSocket.getInputStream()));
				oos = new ObjectOutputStream(threadedSocket.getOutputStream());
				ois = new ObjectInputStream(threadedSocket.getInputStream());
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
	        	
	        	System.out.println("Transport: " + ui.getTransport() + "\nFloor: " + ui.getFloor() + "\nLatitude: " + ui.getLatitude() + "\nLongitude: " + ui.getLongitude() + "\nroomNumber: " + ui.getRoomNumber() + "\nnonAcademicRoom: " + ui.getNonAcademicRoom() + "\nprofessorName: " + ui.getProfessorName() + "\n");
	        	/*ui.setTransport(Transport.ELEVATOR);
	        	ui.setFloor(Floor.SECOND);
	        	ui.setLatitude(100);
	        	ui.setLongitude(200);
	        	
	        	try{
	        		oos.writeObject(ui);
	        	}catch(Exception e){
				System.exit(1);
			}*/
			
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
			
			//int directions[][] = new int [2];
			//String[][] textDirections = new String[2];
			//byte images[][] = new byte[2];
			//Point points[][] = new Point[2];
			
			//ArrayList<Integer> directions1 = null;
			ArrayList<String> textDirections1 = new ArrayList<String>();
			ArrayList<Byte> images1 = new ArrayList<Byte>();
			ArrayList<Point> points1 = new ArrayList<Point>();
			
			//ArrayList<Integer> directions2 = null;
			ArrayList<String> textDirections2 = new ArrayList<String>();
			ArrayList<Byte> images2 = new ArrayList<Byte>();
			ArrayList<Point> points2 = new ArrayList<Point>();
			
			if(beginFloor != endFloor){
				//nothing right now
			}else{
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
					
					FileInputStream fis = null;
					
					try{
						fis = new FileInputStream(new File(filename));
					}catch(Exception e){
						System.err.println("Error opening fis.");
					}
					
					System.out.println("Opened the file: " + filename);
					
					byte tempByte;
					
					try{
						tempByte = (byte) fis.read();
						
						System.out.print(tempByte + ", ");
					
						while(tempByte != -1){
							images1.add(new Byte(tempByte));
							tempByte = (byte) fis.read();
						}
					}catch(Exception e){
						System.err.println("Error reading bytes from file.");
						System.exit(1);
					}
					
					Byte images1Array[] = new Byte[images1.size()];
			
					for(int i = 0; i < images1Array.length; i++){
						images1Array[i] = images1.get(i);
						System.out.print(images1Array[i] + ", ");
					}
					
					System.out.println();
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
