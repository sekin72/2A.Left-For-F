/**
 * 
 */
import java.util.Scanner;
/**
 * @author Kaan
 *
 */
public class InputManager {

	/**
	 * 
	 */
	
	private Scanner keyboard;

	public InputManager() {
		// TODO Auto-generated constructor stub
		keyboard = new Scanner(System.in);
	}
	
	public void Update()
	{
		System.out.println("a");
		while(true)
		{
			String newcommand = keyboard.next();
			switch(newcommand)
			{
				case "A":
					LevelControl.Instance.move("Left");
					break;
				case "S":
					LevelControl.Instance.move("Down");
					break;
				case "D":
					LevelControl.Instance.move("Right");
					break;
				case "W":
					LevelControl.Instance.move("Up");
					break;
				case "P":
					LevelControl.Instance.Pause();
					break;
				default:
					break;
			}
		}
	}

}
