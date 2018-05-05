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
    	int key = event.getKeyCode();
    	switch(key)
		{
			case KeyEvent.VK_A:
				GameManager.Instance.levelController.move("Left");
				break;
			case KeyEvent.VK_S:
				GameManager.Instance.levelController.move("Down");
				break;
			case KeyEvent.VK_D:
				GameManager.Instance.levelController.move("Right");
				break;
			case KeyEvent.VK_W:
				GameManager.Instance.levelController.move("Up");
				break;
			case KeyEvent.VK_P:
				GameManager.Instance.levelController.Pause();
				break;
			case KeyEvent.VK_I:
				GameManager.Instance.levelController.ItemMenu();
				break;
			default:
				break;
		}
    }
	
	@Override
    public void keyReleased(KeyEvent event) {
		GameManager.Instance.levelController.resetMoveChanges();
    }
	
}