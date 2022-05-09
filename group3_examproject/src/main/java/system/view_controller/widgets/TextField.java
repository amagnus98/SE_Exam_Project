package system.view_controller.widgets;
import javax.swing.*;

import java.awt.*;


// TextField Widget // Responsible - Marcus Nielsen - (s204126)
public class TextField {
    
    public JTextField textField;
    public JPanel textFieldPanel;

    public TextField(String name, String placeholder, Color bgcolor) {

        this.textFieldPanel = new JPanel();
        this.textFieldPanel.setBackground(bgcolor);

        this.textField = new JTextField();
        this.textField.setText(placeholder);
        
        this.textField.setPreferredSize(new Dimension(150, 50));
        this.textFieldPanel.setPreferredSize(new Dimension(150, 50));

        this.textFieldPanel.add(textField);
    }

    public TextField getTextField() {
        return this;
    }

}
