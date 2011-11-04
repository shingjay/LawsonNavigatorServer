/*
ServerTest.java
Written by George Brinzea

Used for testing and understanding server-side Java networking code

This code is not a part of the final project build
*/

import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;

public class Server {
	
	static Socket clientSocket = null;
	//static boolean creatingNewThread = false;
	
	static Object synch = new Object();
	
	static int threadID = 0;
	static int activeThreads = 0;
	
	static class Connection extends Thread{
		public void run(){
			int ID = threadID;
			Socket threadedSocket = clientSocket;
			PrintWriter out = null;
			try {
				out = new PrintWriter(threadedSocket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        	BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(threadedSocket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(threadedSocket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	       		 String inputLine = null, outputLine = null;
			
	        	//synchronized(synch){
	        		activeThreads++;
	        	//}
	        
			ImageIcon image = null;
			//File file = null;
			
			while(true){
	        		try {
					inputLine = in.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        		System.out.println("Input from connection " + ID + ": " + inputLine);
	        	
	        		if(inputLine.equals("exit")){
	        			break;
	        		}else if(inputLine.equals("getID")){
	        			outputLine = Integer.toString(ID);
	        		}else if(inputLine.equals("getData")){
					Data data = new Data(57, false, 'c');
					
					try{
						oos.writeObject(data);
					}catch(Exception e){
						System.exit(1);
					}
					System.out.println("Output to connection " + ID + ": " + "data object");
					continue;
				}else if(inputLine.equals("getImage1")){
					try{
						//file = new File("lwsn-0.jpg");
						//System.out.println("1");
						
						image = new ImageIcon("lwsn-1.jpg");
						System.out.println("2");
						
						oos.writeObject(image);
						System.out.println("3");
						
						System.out.println("Output to connection " + ID + ": " + "lwsn-1.jpg");
						System.out.println("4");
						
						continue;
					}catch(Exception e){
						System.exit(1);
					}
				}else{
	        		outputLine = inputLine + " lol";
	        	}
	        	
	        	out.println(outputLine);
	        	
        		System.out.println("Output to connection " + ID + ": " + outputLine);
        		System.out.println();
	        	}
			
			out.close();
		    try {
				in.close();
				oos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		    try {
				clientSocket.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			//synchronized(synch){
	        	activeThreads--;
	        	//}
		}
	}
	
	static class ServerCommands extends Thread{
		public void run(){
			String serverInput = null;
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				try{
				serverInput = stdIn.readLine();
				}catch(Exception e){
					System.exit(1);
				}
				
				if(serverInput.equals("threads")){
					//synchronized(synch){
						System.out.println("Threads running: " + activeThreads);
					//}
				}else{
					System.out.println("Invalid command");
					serverInput = null;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
        	try {
            		serverSocket = new ServerSocket(4444);
        	} catch (IOException e) {
           		System.err.println("Could not listen on port: 4444.");
           		System.exit(1);
       		}
        
        	try {
        		Connection connection;
			ServerCommands sc;
        		while(true){
        			clientSocket = serverSocket.accept();
        			connection = new Connection();
        			connection.start();
				sc = new ServerCommands();
				sc.start();
				
        			//synchronized(synch){
    	        			//creatingNewThread = true;
        			//}
        		
        			//while(creatingNewThread){
        				//waiting
        			//}
        		
        			threadID++;
            			System.out.println("Made a new connetion");
        		}
        	} catch (IOException e) {
           		System.err.println("Accept failed.");
            		System.exit(1);
        	}
	}
}
