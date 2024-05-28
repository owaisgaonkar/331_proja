package proj_A;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class mymain {
	 public static short [][] grayImage;
	    public static int width;
	    public static int height;
	    private static BufferedImage image;
	
	    public static void main(String[] args) throws IOException {
    	    String fileNameInp="TenCardG.jpg";
    	    File inp = new File(fileNameInp);
    	    BufferedImage image_source = ImageIO.read(inp);
    	    int width_source = image_source.getWidth();
    	    int height_source = image_source.getHeight();
    	    short [][] source_img =readColourImage(fileNameInp);
    	    
    	    System.out.println("Dimension of the image: WxH = "+ width_source + "x" + height_source +"");
    	    
    	    
    	    String fileNameTemplate="Template.jpg";
    	    File inp2 = new File(fileNameTemplate);
    	    BufferedImage image_template = ImageIO.read(inp2);
    	    int width_template = image_template.getWidth();
    	    int height_template = image_template.getHeight();
    	    
    	    System.out.println("Dimension of the image: WxH = "+ width_template + "x" + height_template+"");
}

	    public static short[][] readColourImage(String fileName) {
	          
            try
            {
             // RGB pixel values
             byte[] pixels;
  
             File inp=new File("fileName");
             image = ImageIO.read(inp);
             width = image.getWidth();
             height = image.getHeight();          
            
             
             pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
             System.out.println("Dimension of the image: WxH= " + width + " x "+height+" "+ "| num of pixels: "+ pixels.length);
         
         
        
             //rgb2gray in a 2D array grayImage                 
             int pr;// red
             int pg;//  green
             int pb;// blue     
   
            grayImage =new short [height][width];
            int coord;
            for (int i=0; i<height;i++)
         	   for(int j=0;j<width;j++)
         	   {        		     
         		   coord= 3*(i*width+j);
         		   pr= ((short) pixels[coord] & 0xff); // red
                    pg= (((short) pixels[coord+1] & 0xff));//  green
                    pb= (((short) pixels[coord+2] & 0xff));// blue                
                    
         		   grayImage[i][j]=(short)Math.round(0.299 *pr + 0.587 * pg  + 0.114 * pb);         
         		   
         	   }  
            }
            catch (IOException e) {
                e.printStackTrace();
                } 
            
            return grayImage;

    }
       
       
     
    /**
     * 
     * @param fileName
     * @param xCoord
     * @param yCoord
     * @param rectWidth
     * @param rectHeight
     * @return 
     */
 	public static short[][] writeColourImage(String fileName,short xCoord, short yCoord, short rectWidth, short rectHeight) {   
     try {                   
       
         Image scaledImage = image.getScaledInstance(-1,-1, 0);
         // rectangle coordinates and dimension to superimpose on the image
         ImageIO.write(
                 add_Rectangle(scaledImage,xCoord,yCoord,rectWidth,rectHeight),
                 "jpg",
                 new File(fileName));
      
        } catch (IOException e) {
          e.printStackTrace();
          }       
     return grayImage;
 }


    
    /**
     * 
     * @param img
     * @param xCoord
     * @param yCoord
     * @param rectWidth
     * @param rectHeight
     * @return
     */
    public static BufferedImage add_Rectangle(Image img, short xCoord, short yCoord, short rectWidth, short rectHeight) {

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
  
        Graphics2D g2D = bi.createGraphics();
        g2D.drawImage(img, 0, 0, null);
        g2D.setColor(Color.RED);
        g2D.drawRect(xCoord, yCoord, rectWidth, rectHeight);              
        g2D.dispose();
        return bi;
    }
}