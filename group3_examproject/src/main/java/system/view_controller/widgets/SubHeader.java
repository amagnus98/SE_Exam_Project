package system.view_controller.widgets;
import javax.swing.*;
import java.awt.*;
import system.view_controller.constants.Constants;

public class SubHeader {

    Constants constants = new Constants();
    
    public SubHeader(String titleText, Color bgcolor,JPanel panel) {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridBagLayout());
        JLabel label = new JLabel(titleText);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        labelPanel.setBackground(bgcolor);
        labelPanel.setPreferredSize(new Dimension(250, 30));
        labelPanel.add(label);
        panel.add(labelPanel);
    }
}
