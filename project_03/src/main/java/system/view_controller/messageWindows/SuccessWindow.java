package system.view_controller.messageWindows;
import javax.swing.JOptionPane;

// Activity for viewing activity // Responsible - Andreas Bigom (s200925)
public class SuccessWindow {

    String successMessage;

    public SuccessWindow(String successMessage) {
        this.successMessage = successMessage;
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.PLAIN_MESSAGE);
    }
}
