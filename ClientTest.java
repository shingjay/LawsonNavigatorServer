/*
ClientTest.java
Written by George Brinzea

Used for testing and understanding client-side Java networking code

This code is not a part of the final project build
*/

package com.purdue.LawsonNavigator;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;

public class ClientTest {
    public static void main(String[] args) throws IOException {

        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	
	
	//The IP address of moore01	
	byte[] IP = new byte[4];
	IP[0] = (byte) 128;
	IP[1] = (byte) 10;
	IP[2] = (byte) 12;
	IP[3] = (byte) 131;

        try {
            kkSocket = new Socket(InetAddress.getByAddress(IP), 4444);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
		ois = new ObjectInputStream(kkSocket.getInputStream());
		oos = new ObjectOutputStream(kkSocket.getOutputStream());
        } catch (UnknownHostException e) {
            System.err.println("Cannot find the host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        
        /*fromUser = "getID";
        out.println(fromUser);
        fromServer = in.readLine();
        System.out.println("Connection #" + fromServer + " established");
        System.out.println();*/
        
		
	Data data = null;
	ImageIcon image = null;
	Image image2;
	File file = null;
        while(true){
        	System.out.print("Enter input: ");
        	fromUser = stdIn.readLine();
        	
        	if(fromUser.equals("exit")){
				out.println(fromUser);
        		break;
        	}else if(fromUser.equals("Image")){
			UserInput ui = new UserInput(Transport.STAIRS, Floor.FIRST, Display.MAP, ConvertAndDraw.yArraytoGPS(60), ConvertAndDraw.xArraytoGPS(23), "1142", null, null);
			
			try{
				oos.writeObject(ui);
			}catch(Exception e){
				System.err.println("Error in sending ui data");
				e.printStackTrace();
				System.exit(1);
			}
			
			ArrayList<Byte> images1 = null;
			ArrayList<String> textDirections1 = null;
			ArrayList<Point> points1 = null;

			ArrayList<Byte> images2 = null;
			ArrayList<String> textDirections2 = null;
			ArrayList<Point> points2 = null;
			
			try{
				images1 = (ArrayList<Byte>) ois.readObject();
				textDirections1 = (ArrayList<String>) ois.readObject();
				points1 = (ArrayList<Point>) ois.readObject();

				images2 = (ArrayList<Byte>) ois.readObject();
				textDirections2 = (ArrayList<String>) ois.readObject();
				points2 = (ArrayList<Point>) ois.readObject();
			}catch(Exception e){
				System.err.println("Error in recieving data from the server");
				e.printStackTrace();
				System.exit(1);
			}
			
			Byte images1Array[] = new Byte[images1.size()];
			
			for(int i = 0; i < images1Array.length; i++){
				images1Array[i] = images1.get(i);
				System.out.print(images1Array[i] + ", ");
			}
			
			System.out.println("\nCompleted");
			System.exit(0);
			
			
		}else if(fromUser.equals("Text")){
			UserInput ui = new UserInput(Transport.STAIRS, Floor.FIRST, Display.TEXTSPEECH, ConvertAndDraw.yArraytoGPS(60), ConvertAndDraw.xArraytoGPS(23), "1142", null, null);
			
			try{
				oos.writeObject(ui);
			}catch(Exception e){
				System.err.println("Error in sending ui data");
				e.printStackTrace();
				System.exit(1);
			}
			
			ArrayList<Byte> images1 = null;
			ArrayList<String> textDirections1 = null;
			ArrayList<Point> points1 = null;

			ArrayList<Byte> images2 = null;
			ArrayList<String> textDirections2 = null;
			ArrayList<Point> points2 = null;
			
			try{
				images1 = (ArrayList<Byte>) ois.readObject();
				textDirections1 = (ArrayList<String>) ois.readObject();
				points1 = (ArrayList<Point>) ois.readObject();

				images2 = (ArrayList<Byte>) ois.readObject();
				textDirections2 = (ArrayList<String>) ois.readObject();
				points2 = (ArrayList<Point>) ois.readObject();
			}catch(Exception e){
				System.err.println("Error in recieving data from the server");
				e.printStackTrace();
				System.exit(1);
			}
			
			for(int i = 0; i < textDirections1.size(); i++){
				System.out.println(textDirections1.get(i));
			}
			System.out.println();
			
			for(int i = 0; i < points1.size(); i++){
				System.out.println(points1.get(i).getx() + " " + points1.get(i).gety());
			}
			
			System.out.println("\nCompleted");
			System.exit(0);
		}else{
        	
			out.println(fromUser);
			fromServer = in.readLine();
        	
			System.out.println("Output from server: " + fromServer);
			System.out.println();
		}
        }
        
	ois.close();
        in.close();
        out.close();
        stdIn.close();
        kkSocket.close();

    }
}
