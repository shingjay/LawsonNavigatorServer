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
			
			int start1;
			int start2;
			
			int end1;
			int end2;
			
			//int directions[][] = new int [2];
			//String[][] textDirections = new String[2];
			//byte images[][] = new byte[2];
			//Point points[][] = new Point[2];
			
			ArrayList<Integer> directions1 = null;
			ArrayList<String> textDirections1 = null;
			ArrayList<Byte> images1 = null;
			ArrayList<Point> points1 = null;
			
			ArrayList<Integer> directions2 = null;
			ArrayList<String> textDirections2 = null;
			ArrayList<Byte> images2 = null;
			ArrayList<Point> points2 = null;
			
			if(beginFloor != endFloor){
				//nothing right now
			}else{
				start1 = ConvertAndDraw.yGPStoArray(ui.getLatitude());
				start2 = ConvertAndDraw.xGPStoArray(ui.getLongitude());
				
				if(ui.getRoomNumber() != null){
					end1 = ((Room) rooms.get(ui.getRoomNumber())).getY();
					end2 = ((Room) rooms.get(ui.getRoomNumber())).getX();
				}else if(ui.getNonAcademicRoom() != null){
					end1 = ((Room) rooms.get(ui.getNonAcademicRoom())).getY();
					end2 = ((Room) rooms.get(ui.getNonAcademicRoom())).getX();
				}else{
					end1 = ((Room) prooms.get(ui.getProfessorName())).getY();
					end2 = ((Room) prooms.get(ui.getProfessorName())).getX();
				}
				
				int temp[] = RunMaze.Run(start1, start2, end1, end2);
				
				if(ui.getDisplayOption() == Display.MAP){
					ConvertAndDraw.lineDrawer(start2, start1, temp, beginFloor);
					
					String filename = "out-" + endFloor + ".jpg";
					
					FileInputStream fis = null;
					
					try{
						fis = new FileInputStream(new File(filename));
					}catch(Exception e){
						System.err.println("Error opening fis.");
						System.exit(1);
					}
					
					try{
						byte tempByte = (byte) fis.read();
					
						while(tempByte != -1){
							images1.add(tempByte);
							tempByte = (byte) fis.read();
						}
					}catch(Exception e){
						System.err.println("Error reading bytes from file.");
						System.exit(1);
					}
				}else{
					textDirections1 = Directions.directions_to_string(temp);
					points1 = Point.getTrigger(start2, start1, temp);
				}
			}
	
			try{
				oos.writeObject(images1);
	        		oos.writeObject(images2);
	        		oos.writeObject(textDirections1);
	        		oos.writeObject(points1);
	        		oos.writeObject(textDirections2);
	        		oos.writeObject(points2);
	        	}catch(Exception e){
	        		System.err.println("Error sending data to client.");
				System.exit(1);
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
