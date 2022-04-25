package system.view_controller.widgets;
import javax.swing.*;
import java.awt.*;

public class Button {

    JButton button;
    JPanel buttonPanel;
    
    public Button(String name, Color bgcolor, String size, AbstractAction action) {

        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(bgcolor);

        this.button = new JButton(name);

        if (size == "small") {
            this.buttonPanel.setPreferredSize(new Dimension(200, 75));
            this.button.setFont(new Font("Arial", Font.PLAIN, 15));
            this.button.setPreferredSize(new Dimension(150, 50));
        } else if (size == "micro") {
            this.buttonPanel.setPreferredSize(new Dimension(150, 50));
            this.button.setFont(new Font("Arial", Font.PLAIN, 15));
            this.button.setPreferredSize(new Dimension(150, 50));
        } else {
            this.buttonPanel.setPreferredSize(new Dimension(300, 100));
            this.button.setFont(new Font("Arial", Font.PLAIN, 20));
            this.button.setPreferredSize(new Dimension(250, 75));
        }

        this.button.setAction(action);
        this.buttonPanel.add(this.button);

    }

    public JPanel getButton() {
        return this.buttonPanel;
    }
}
