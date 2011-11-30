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
	InputStream is = null;
	
	
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
		is = kkSocket.getInputStream();
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
			//UserInput ui = new UserInput(Transport.STAIRS, Floor.FIRST, Display.MAP, ConvertAndDraw.yArraytoGPS(60), ConvertAndDraw.xArraytoGPS(23), "1142", null, null);
			
			UserInput ui = new UserInput(Transport.STAIRS, Floor.BASEMENT, Display.MAP, ConvertAndDraw.yArraytoGPS(52), ConvertAndDraw.xArraytoGPS(5), "B160", null, null);
			
			try{
				oos.writeObject(ui);
			}catch(Exception e){
				System.err.println("Error in sending ui data");
				e.printStackTrace();
				System.exit(1);
			}
			
			ArrayList<String> textDirections1 = new ArrayList<String>();
			ArrayList<Byte> images1 = new ArrayList<Byte>();
			
			ArrayList<Byte> images11 = new ArrayList<Byte>();
			ArrayList<Byte> images12 = new ArrayList<Byte>();
			ArrayList<Byte> images13 = new ArrayList<Byte>();
			ArrayList<Byte> images14 = new ArrayList<Byte>();
			ArrayList<Byte> images15 = new ArrayList<Byte>();
			
			ArrayList<Point> points1 = new ArrayList<Point>();
			
			ArrayList<String> textDirections2 = new ArrayList<String>();
			ArrayList<Byte> images2 = new ArrayList<Byte>();
			
			
			ArrayList<Byte> images21 = new ArrayList<Byte>();
			ArrayList<Byte> images22 = new ArrayList<Byte>();
			ArrayList<Byte> images23 = new ArrayList<Byte>();
			ArrayList<Byte> images24 = new ArrayList<Byte>();
			ArrayList<Byte> images25 = new ArrayList<Byte>();
			
			ArrayList<Point> points2 = new ArrayList<Point>();
			
			try{
				//images1 = (ArrayList<Byte>) ois.readObject();
				//images11 = (ArrayList<Byte>) ois.readObject();
				//images12 = (ArrayList<Byte>) ois.readObject();
				//images13 = (ArrayList<Byte>) ois.readObject();
				//images14 = (ArrayList<Byte>) ois.readObject();
				//images15 = (ArrayList<Byte>) ois.readObject();
				
				byte temp = (byte) is.read();
				
				while(temp != -1){
					images1.add(new Byte(temp));
					temp = (byte) is.read();
				}
				
				textDirections1 = (ArrayList<String>) ois.readObject();
				points1 = (ArrayList<Point>) ois.readObject();

				//images2 = (ArrayList<Byte>) ois.readObject();
				//images21 = (ArrayList<Byte>) ois.readObject();
				//images22 = (ArrayList<Byte>) ois.readObject();
				//images23 = (ArrayList<Byte>) ois.readObject();
				//images24 = (ArrayList<Byte>) ois.readObject();
				//images25 = (ArrayList<Byte>) ois.readObject();
				
				temp = (byte) is.read();
				
				while(temp != -1){
					images2.add(new Byte(temp));
					temp = (byte) is.read();
				}
				
				textDirections2 = (ArrayList<String>) ois.readObject();
				points2 = (ArrayList<Point>) ois.readObject();
			}catch(Exception e){
				System.err.println("Error in recieving data from the server");
				e.printStackTrace();
				System.exit(1);
			}
			
			/*Byte images1Array[] = new Byte[images1.size()];
			
			for(int i = 0; i < images1Array.length; i++){
				images1Array[i] = images1.get(i);
				System.out.print(images1Array[i] + ", ");
			}*/
			
			/*byte images1Array[] = new byte[images11.size() + images12.size() + images13.size() + images14.size() + images15.size()];
			int j = 0;
			
			for(int i = 0; i < images11.size(); i++){
				images1Array[j] = images11.get(i).byteValue();
				j++;
			}
			for(int i = 0; i < images12.size(); i++){
				images1Array[j] = images12.get(i).byteValue();
				j++;
			}
			for(int i = 0; i < images13.size(); i++){
				images1Array[j] = images13.get(i).byteValue();
				j++;
			}
			for(int i = 0; i < images14.size(); i++){
				images1Array[j] = images14.get(i).byteValue();
				j++;
			}
			for(int i = 0; i < images15.size(); i++){
				images1Array[j] = images15.get(i).byteValue();
				j++;
			}
			
			for(int i = 0; i < images1Array.length; i++){
				System.out.print(images1Array[i] + ", ");
			}*/
			
			byte images1Array[] = new byte[images1.size()];
			
			for(int i = 0; i < images1Array.length; i++){
				images1Array[i] = images1.get(i).byteValue();
				System.out.print(images1Array[i] + ", ");
			}
			
			System.out.println("\nCompleted");
			System.exit(0);
			
			
		}else if(fromUser.equals("Text")){
			//UserInput ui = new UserInput(Transport.STAIRS, Floor.FIRST, Display.TEXTSPEECH, ConvertAndDraw.yArraytoGPS(60), ConvertAndDraw.xArraytoGPS(23), "1142", null, null);
			UserInput ui = new UserInput(Transport.STAIRS, Floor.BASEMENT, Display.TEXTSPEECH, ConvertAndDraw.yArraytoGPS(52), ConvertAndDraw.xArraytoGPS(5), "B160", null, null);
			
			try{
				oos.writeObject(ui);
			}catch(Exception e){
				System.err.println("Error in sending ui data");
				e.printStackTrace();
				System.exit(1);
			}
			
			ArrayList<String> textDirections1 = new ArrayList<String>();
			ArrayList<Byte> images1 = new ArrayList<Byte>();
			
			ArrayList<Byte> images11 = new ArrayList<Byte>();
			ArrayList<Byte> images12 = new ArrayList<Byte>();
			ArrayList<Byte> images13 = new ArrayList<Byte>();
			ArrayList<Byte> images14 = new ArrayList<Byte>();
			ArrayList<Byte> images15 = new ArrayList<Byte>();
			
			ArrayList<Point> points1 = new ArrayList<Point>();
			
			ArrayList<String> textDirections2 = new ArrayList<String>();
			ArrayList<Byte> images2 = new ArrayList<Byte>();
			
			
			ArrayList<Byte> images21 = new ArrayList<Byte>();
			ArrayList<Byte> images22 = new ArrayList<Byte>();
			ArrayList<Byte> images23 = new ArrayList<Byte>();
			ArrayList<Byte> images24 = new ArrayList<Byte>();
			ArrayList<Byte> images25 = new ArrayList<Byte>();
			
			ArrayList<Point> points2 = new ArrayList<Point>();
			
			try{
				//images1 = (ArrayList<Byte>) ois.readObject();
				images11 = (ArrayList<Byte>) ois.readObject();
				images12 = (ArrayList<Byte>) ois.readObject();
				images13 = (ArrayList<Byte>) ois.readObject();
				images14 = (ArrayList<Byte>) ois.readObject();
				images15 = (ArrayList<Byte>) ois.readObject();
				textDirections1 = (ArrayList<String>) ois.readObject();
				points1 = (ArrayList<Point>) ois.readObject();

				//images2 = (ArrayList<Byte>) ois.readObject();
				images21 = (ArrayList<Byte>) ois.readObject();
				images22 = (ArrayList<Byte>) ois.readObject();
				images23 = (ArrayList<Byte>) ois.readObject();
				images24 = (ArrayList<Byte>) ois.readObject();
				images25 = (ArrayList<Byte>) ois.readObject();
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
