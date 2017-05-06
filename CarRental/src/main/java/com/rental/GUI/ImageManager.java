package com.rental.GUI;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageManager {

	public static ImageIcon getImage(String imagePath, int dimX, int dimY){
		ImageIcon imageIcon = new ImageIcon(imagePath); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(dimX, dimY,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return new ImageIcon(newimg);  // transform it back
	}
	
}
