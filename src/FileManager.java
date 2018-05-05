import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileManager {

	private static String fileName=".\\Assets\\saveFile.txt";
	
	public static void saveGame(Player player) throws IOException
	{
		try
		{
			FileWriter writer = new FileWriter(fileName,false);
			writer.write(String.valueOf(GameManager.Instance.currentLevel));
			writer.write("\r\n");
			
			if(player.isMale)
				writer.write("True");
			else
				writer.write("False");
				
			writer.write("\r\n");
			
			writer.write(String.valueOf(player.power));
			writer.write("\r\n");
			
			writer.write(String.valueOf(player.maximumHealth));
			writer.write("\r\n");
			
			writer.write(String.valueOf(player.moveSpeed));
			writer.write("\r\n");
			
			writer.write(String.valueOf(player.itemList.size()));
			writer.write("\r\n");
			
			for(int i=0;i<player.itemList.size();i++)
			{
				writer.write(player.itemList.get(i).name);
				writer.write("\r\n");
			}
			
			writer.write(String.valueOf(player.enchancements.size()));
			writer.write("\r\n");
			
			for(int i=0;i<player.enchancements.size();i++)
			{
				writer.write(player.enchancements.get(i).name);
				writer.write("\r\n");
			}
			writer.close();
		}catch (IOException e)
		{
			
		}
	}
	
	public static void loadGame()
	{
		Player player = new Player();
		BufferedReader bufferedReader;
		String line;
		int dif;
		
		try
		{
			FileReader reader = new FileReader(fileName);
			bufferedReader = new BufferedReader(reader);

			line =bufferedReader.readLine();
			if(line==null)
				return;
			System.out.println(line);
			dif = Integer.parseInt(line);
			

			line =bufferedReader.readLine();
			System.out.println(line);
			if(line =="True")
				player.isMale=true;
			else
				player.isMale=false;

			if(player.isMale)
				try {
					player.currentImage = ImageIO.read(new File(".\\Assets\\player.png"));
					player.originalImage = ImageIO.read(new File(".\\Assets\\player.png"));
					player.name = "Ali";
				}catch(IOException ex)
				{
			  
				}
			else
				try {
					player.originalImage = ImageIO.read(new File(".\\Assets\\female.png"));
					player.currentImage = ImageIO.read(new File(".\\Assets\\female.png"));
					player.name = "Ayþe";
				}catch(IOException ex)
				{
			  
				}
			
			player.resize(player.originalImage, 100, 100);
			player.xPos=20;
			player.yPos=100;  
			
			line =bufferedReader.readLine();
			System.out.println(line);
			player.power =Integer.parseInt(line);
			

			line =bufferedReader.readLine();
			System.out.println(line);
			player.maximumHealth=Integer.parseInt(line);
			player.healthPoints=Integer.parseInt(line);

			line =bufferedReader.readLine();
			System.out.println(line);
			player.moveSpeed=Integer.parseInt(line);

			line =bufferedReader.readLine();
			System.out.println(line);
			int size =Integer.parseInt(line);
			for(int i=0;i<size;i++)
			{
				line =bufferedReader.readLine();
				System.out.println(line);
				if(line == "food")
				{
					player.itemList.add(new Food());
				}
				else if(line == "coffee")
				{
					player.itemList.add(new Coffee());
				}
				else if(line == "energyDrink")
				{
					player.itemList.add(new EnergyDrink());
				}
			}

			line =bufferedReader.readLine();
			System.out.println(line);
			size =Integer.parseInt(line);
			for(int i=0;i<size;i++)
			{
				line =bufferedReader.readLine();
				System.out.println(line);
				if(line == "skillEnhancement")
				{
					player.enchancements.add(new SkillEnchancement());
				}
				else if(line == "statEnhancement")
				{
					player.enchancements.add(new StatEnchancement());
				}
			}
			
			reader.close();
			GameManager.Instance.currentLevel=dif;
			GameManager.Instance.player = player;
	        GameManager.Instance.createNewLevel();
			
			
		}
		catch(IOException e)
		{
			
		}
		
	}
}
