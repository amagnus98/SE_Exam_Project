package system.view_controller.widgets;
import javax.swing.*;
import java.awt.*;
import system.view_controller.constants.Constants;

public class Header {

    Constants constants = new Constants();
    
    public Header(String titleText,JPanel panel) {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridBagLayout());
        JLabel label = new JLabel(titleText);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        labelPanel.setBackground(constants.backgroundColor);
        labelPanel.setPreferredSize(new Dimension(350, 150));
        labelPanel.add(label);
        panel.add(labelPanel);
    }
}
