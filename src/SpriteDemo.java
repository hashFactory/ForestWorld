

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpriteDemo extends JPanel {


	private JFrame frame;
	
	private Image waterSprite;
	private Image grassSprite;
	private Image treeSprite;
	
	private int spriteLength = 32;
	
	private int[][] myWorld;

	public SpriteDemo()
	{
		try
		{
			waterSprite = ImageIO.read(new File("images/water.png"));
			treeSprite = ImageIO.read(new File("images/tree.png"));
			grassSprite = ImageIO.read(new File("images/grass.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		frame.setSize(300,300);
		frame.setVisible(true);
		
		myWorld = new int[8][8];
		
		for ( int i = 0 ; i != 8 ; i++ )
			for ( int j = 0 ; j != 8 ; j++ )
				myWorld[i][j] = (int)(Math.random()*3.0);
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < myWorld.length ; i++ )
			for ( int j = 0 ; j < myWorld[0].length ; j++ )
			{
				if ( myWorld[i][j] == 0 )
					g2.drawImage(waterSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				else
					if ( myWorld[i][j] == 1 )
						g2.drawImage(treeSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					else	
						g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					
			}
	}

	public static void main(String[] args) {
		new SpriteDemo();
	}
}