package system.view_controller.widgets;
import system.view_controller.constants.Constants;
import javax.swing.*;
import java.awt.*;

// Button Widget // Responsible - Mads Ringsted - (s204144)
public class Button {

    JButton button;
    JPanel buttonPanel;
    
    public Button(String name, Color bgcolor, String size, AbstractAction action) {
        Constants constants = new Constants();
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(bgcolor);
        this.button = new JButton(name);

        if (size == "small") {
            this.buttonPanel.setMaximumSize(new Dimension(600, 75));
            this.button.setFont(new Font("Arial", Font.PLAIN, 15));
            this.button.setPreferredSize(new Dimension(175, 50));
        } else if (size == "micro") {
            this.buttonPanel.setMaximumSize(new Dimension(600, 55));
            this.button.setFont(new Font("Arial", Font.PLAIN, 15));
            this.button.setPreferredSize(new Dimension(150, 50));
        } else {
            this.buttonPanel.setMaximumSize(new Dimension(600, 100));
            this.button.setFont(new Font("Arial", Font.PLAIN, 20));
            this.button.setPreferredSize(new Dimension(250, 75));
        }
        this.button.setBorder(constants.buttonBorder);
        if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
            this.button.setBackground(constants.buttonBackgroundColor);
            this.button.setOpaque(true);
        }
        this.button.setAction(action);
        this.buttonPanel.add(this.button);

    }

    public JPanel getButton() {
        return this.buttonPanel;
    }
}
