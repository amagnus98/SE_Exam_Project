package system.view_controller.actions;
import javax.swing.*;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AssignProjectDeveloperAction extends AbstractAction {

    JTextField textField;
    Main main;
    Project project;
    String previousPage;

    public AssignProjectDeveloperAction(String name, String previousPage, JTextField textField, Project project, Main main) {
        putValue(NAME, name);
        this.textField = textField;
        this.main = main;
        this.project = project;
        this.previousPage = previousPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Boolean hasError = false;

        String initials = textField.getText().trim();

        if (!initials.equals("")) {
            try {
            main.app.addDeveloperToProject(initials, project.getProjectNumber());
            } catch (OperationNotAllowedException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            }

            if (!hasError) {
                SuccessWindow errorWindow = new SuccessWindow("Developer successfully assigned.");
                errorWindow.showMessage();
                main.viewProject(project, previousPage);
            }
        } else {
            ErrorWindow errorWindow = new ErrorWindow("Please insert initials.");
            errorWindow.showMessage();
        }
    }
}