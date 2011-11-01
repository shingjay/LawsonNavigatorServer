import java.net.*;
import java.io.*;


public class Server {
	
	static Socket clientSocket = null;
	//static boolean creatingNewThread = false;
	
	//static Object synch = new Object();
	
	static int threadID = 0;
	
	static class Connection extends Thread{
		public void run(){
			int ID = threadID;
			Socket threadedSocket = clientSocket;
			PrintWriter out = null;
			try {
				out = new PrintWriter(threadedSocket.getOutputStream(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(threadedSocket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String inputLine = null, outputLine = null;
			
	        //synchronized(synch){
	        	//creatingNewThread = false;
	        //}
	        
			while(true){
	        	try {
					inputLine = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	System.out.println("Input from connection " + ID + ": " + inputLine);
	        	
	        	if(inputLine.equals("exit")){
	        		break;
	        	}else if(inputLine.equals("getID")){
	        		outputLine = Integer.toString(ID);
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
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				clientSocket.close();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
        	while(true){
        		clientSocket = serverSocket.accept();
        		connection = new Connection();
        		connection.start();
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
