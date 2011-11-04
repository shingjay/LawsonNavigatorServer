import java.io.*;
import java.net.*;
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
        	}else if(fromUser.equals("getData")){
			out.println(fromUser);
				
			try{
				data = (Data) ois.readObject();
			}catch(ClassNotFoundException e){
				System.err.println("Data error.");
				System.exit(1);
			}
			System.out.println("Data from server: ");
			System.out.println("Value 1 = " + data.getV1() + "\nValue 2 = " + data.getV2() + "\nValue3 = " + data.getV3()); 
		}else if(fromUser.equals("getImage1")){
			out.println(fromUser);
			
			try{
				image = (ImageIcon) ois.readObject();
				System.out.println("1");
				image2 = image.getImage();
				file = new File("lwsn-1.jpg");
				System.out.println("2");
				
				file.createNewFile();
				System.out.println("3");
				
				
				BufferedImage bi = new BufferedImage(image2.getWidth(null),image2.getHeight(null) ,BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = bi.createGraphics();
				g2.drawImage(image2, 0, 0, null);
				g2.dispose();
				ImageIO.write(bi, "jpg", file);
				
				System.out.println("4");
			}catch(Exception e){
				System.err.println("Image error.");
				System.exit(1);
			}
		
		}else if(fromUser.equals("UserInput")){
			//out.println(fromUser);
			Transport t = Transport.STAIRS;
			Floor f = Floor.FIRST;
			UserInput ui = new UserInput(t, f, 50, 75, "150");
			
			try{
				oos.writeObject(ui);
			}catch(Exception e){
				System.out.println("oos error");
				e.printStackTrace();
				System.exit(1);
			}
			
			try{
				ui = (UserInput) ois.readObject();
			}catch(Exception e){
				System.out.println("ois error");
				System.exit(1);
			}
			
			System.out.println("Transport: " + ui.getTransport() + "\nFloor: " + ui.getFloor() + "\nLatitude: " + ui.getLatitude() + "\nLongitude: " + ui.getLongitude());
		}else {
        	
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
