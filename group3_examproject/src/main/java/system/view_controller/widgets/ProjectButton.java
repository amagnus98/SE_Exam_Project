package system.view_controller.widgets;
import javax.swing.*;

import system.view_controller.constants.Constants;

import java.awt.*;

public class ProjectButton {

    JButton button;
    JPanel buttonPanel;
    
    public ProjectButton(String ProjectNumber, String ProjectName, Color bgcolor, AbstractAction action) {
        Constants constants = new Constants();
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(bgcolor);

        this.button = new JButton(ProjectNumber + "; " + ProjectName);

        this.buttonPanel.setMaximumSize(new Dimension(250, 60));
        this.button.setFont(new Font("Arial", Font.PLAIN, 15));
        this.button.setPreferredSize(new Dimension(200, 50));
        this.button.setBorder(constants.buttonBorder);
        this.button.setAction(action);
        this.buttonPanel.add(this.button);

    }

    public JPanel getButton() {
        return this.buttonPanel;
    }
}
