import javax.swing.JFrame;

public class ClientTest {
	public static void main(String[] args) {
		Client charlie;
		charlie = new Client("Your laptop ip ");
		charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		charlie.startRunning();
	}
}
