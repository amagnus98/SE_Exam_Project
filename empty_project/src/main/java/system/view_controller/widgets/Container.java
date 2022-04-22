package system.view_controller.widgets;
import javax.swing.*;
import java.awt.*;
import system.view_controller.constants.Constants;

public class Container {

    JPanel container;
    Constants constants = new Constants();

    public Container(JPanel panel) {
        this.container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        container.setBackground(constants.backgroundColor);
        container.add(panel);
    }

    public JPanel getContainer() {
        return this.container;
    }
}