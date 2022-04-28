package system.view_controller.messageWindows;
import javax.swing.JOptionPane;

public class SuccessWindow {

    String successMessage;

    public SuccessWindow(String successMessage) {
        this.successMessage = successMessage;
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(null, successMessage, "Error", JOptionPane.PLAIN_MESSAGE);
    }
}
