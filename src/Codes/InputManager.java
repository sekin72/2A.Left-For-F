/**
 * 
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * @author Kaan
 *
 */
public class InputManager extends KeyAdapter{

	@Override
    public void keyPressed(KeyEvent event) {
		System.out.println("keypressed");
    	char ch = event.getKeyChar();
    	switch(ch)
		{
			case 'A':
			case 'a':
				GameManager.Instance.levelController.move("Left");
				break;
			case 'S':
			case 's':
				GameManager.Instance.levelController.move("Down");
				break;
			case 'D':
			case 'd':
				GameManager.Instance.levelController.move("Right");
				break;
			case 'W':
			case 'w':
				GameManager.Instance.levelController.move("Up");
				break;
			case 'P':
			case 'p':
				GameManager.Instance.levelController.Pause();
				break;
			default:
				break;
		}
    	System.out.println(event.getKeyChar());
    }
	
}