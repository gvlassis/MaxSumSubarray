import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageInput {
	private BufferedImage img;
	private double[][] scoreMatrix;
	
	public ImageInput(String name) {
		try {
			img= ImageIO.read( getClass().getResourceAsStream( name ) );
		} catch(IOException ex) {
			System.out.println("Could not read image!");
		}
		
		int height=img.getHeight();
		int width=img.getWidth();
		
		scoreMatrix= new double[height][width];
		int temp;
		
		Color grass=new Color(139,195,74,255);
		int grassColorInt=grass.getRGB();
		
		Color tree=new Color(51,105,30,255);
		int treeColorInt=tree.getRGB();
		
		Color water=new Color(3,169,244,255);
		int waterColorInt=water.getRGB();
		
		Color animal=new Color(236,64,122,255);
		int animalColorInt=animal.getRGB();
		
		Color endangered=new Color(245,127,23,255);
		int endangeredColorInt=endangered.getRGB();
		
		for( int y=0; y< height; y++ ) {
			for( int x=0; x< width; x++ ) {
				temp=img.getRGB(x, y);
				
				if(temp==grassColorInt) {
					scoreMatrix[y][x]=+1;
				}else if(temp==treeColorInt) {
					scoreMatrix[y][x]= Double.NEGATIVE_INFINITY;
				}else if(temp==waterColorInt) {
					scoreMatrix[y][x]= Double.NEGATIVE_INFINITY;
				}else if(temp==animalColorInt) {
					scoreMatrix[y][x]= Double.NEGATIVE_INFINITY;
				}else if(temp==endangeredColorInt) {
					scoreMatrix[y][x]= Double.NEGATIVE_INFINITY;
				}
				
			}
		}
		
		
	}
	
	public double[][] getScoreMatrix(){
		return scoreMatrix;
	}
	
	public BufferedImage getImg(){
		return img;
	}
}
