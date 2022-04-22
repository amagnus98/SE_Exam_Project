package system.view_controller.messageWindows;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorWindow {

    String errorMessage;

    public ErrorWindow(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
