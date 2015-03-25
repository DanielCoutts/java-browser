import javax.swing.*;
import java.awt.*;


public class Browser {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());

		Pane pane = new Pane("http://www2.warnerbros.com/spacejam/movie/jam.htm");
		window.add(pane, BorderLayout.CENTER);
		
		window.add(new Toolbar(), BorderLayout.NORTH);
		
		window.setMinimumSize(new Dimension(700,400));
		window.setSize(new Dimension(1000, 1000));
		window.setVisible(true);
	}
}
