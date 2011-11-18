package com.purdue.LawsonNavigator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.*; // For JPanel, etc.
import java.awt.*;           // For Graphics, etc.
import java.awt.geom.*;      // For Ellipse2D, etc.
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

class ConvertAndDraw {
	public static void main (String args[]){
		System.out.printf("%d\n", xGPStoArray(-86.91722517212231));
		System.out.printf("%d\n", yGPStoArray(40.4281221357864));
		System.out.printf("%.20f\n", xArraytoGPS(1));
		System.out.printf("%.20f\n",yArraytoGPS(5));
		
		
		int startingX = 13; 
		int startingY = 10;
		int []directionArray = {0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3};
		int floor = 1;
		lineDrawer(startingX, startingY, directionArray, floor);
		
		
		
	}
	public static int xGPStoArray(double x){
		double x1 = -86.91724196076393;
		double x2 = -86.91678866744041;
		int xA = 27;
		double xT = Math.abs(x1 - x2) / xA;
		double xG = (x - x1) / xT;
		return (int)Math.round(xG);
	}
	public static int yGPStoArray(double y){
		double y1 = 40.42818497851696;
		double y2 = 40.427418297204184;
		int yA = 61;
		double yT = Math.abs(y1 - y2) / yA;
		double yG = Math.abs((y - y1) / yT);
		return (int)Math.round(yG);
	}
	public static double xArraytoGPS(int x){
		double x1 = -86.91724196076393;
		double x2 = -86.91678866744041;
		int xA = 27;
		double xX = (double)x;
		double xT = Math.abs(x1 - x2) / xA;
		xX = x1 + (xT * xX);
		return xX;
	}
	public static double yArraytoGPS(int y){
		double y1 = 40.42818497851696;
		double y2 = 40.427418297204184;
		int yA = 61;
		double yY = (double)y;
		double yT = Math.abs(y1 - y2) / yA;
		yY = y1 - (yT * yY);
		return yY;
	}
	
	public static void  lineDrawer(int startingX, int startingY, int directionArray[], int floor){
		// Direction!!!!!!!
		//	     0
		// 3	Current  1
		//	     2
		//Floor  : 0, 1, 2, 3;
	
		int []dirY = {-1, 0, 1, 0};
		int []dirX = {0, 1, 0, -1};
		FileInputStream fis;
		int x = 0, y = 0;
		int xA = 27; // Image x, and y size
		int yA = 69; // This is 69 cuz there was a title on the image and we ignored that part for griding part.
		Graphics g = null;
		BufferedImage image = null;
		String fileName = "lwsn-" + floor + ".jpg";
		try {
			File originalImage = new File(fileName);
			fis = new FileInputStream(originalImage);
			image = ImageIO.read(fis); 
			x = image.getWidth();
			y = image.getHeight();
	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		BufferedImage bi = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);	
		bi = image;
		g = bi.getGraphics();

		x = x / xA; // One cell X size on the image
		y = y / yA; // One cell Y size on the image
		startingX = x * startingX;
		startingY = y * startingY;
			System.out.printf("%d, %d\n", x, y);
		System.out.printf("%d, %d\n", startingX, startingY);
		for (int i = 0; i < directionArray.length; i ++){
			if (i == 0){
				g.setColor( Color.BLUE );
			} else if (i == directionArray.length - 1){
				g.setColor( Color.GREEN );
			} else{
				g.setColor( Color.RED );
			}
		
			startingX = dirX[directionArray[i]] * x + startingX;
			startingY = dirY[directionArray[i]] * y + startingY;
				g.fillOval( startingX, startingY, 20, 20);
		}
		String outFileName = "out-" + floor + ".jpg";
		try {
			ImageIO.write(bi, "jpg",new File(outFileName)); 
		} catch(Exception E){		}
	}	
}
