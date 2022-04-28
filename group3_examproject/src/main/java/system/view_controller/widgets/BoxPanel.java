package system.view_controller.widgets;
import javax.swing.*;
import java.awt.*;
import system.view_controller.constants.Constants;

public class BoxPanel {

    JPanel panel;
    Constants constants = new Constants();

    public BoxPanel() {
        this.panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setBackground(constants.backgroundColor);
        panel.setLayout(layout);
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
