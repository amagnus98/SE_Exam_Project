package system.view_controller.actions;
import javax.swing.*;

import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;


// Create New Project Action // Responsible - Andreas Bigom (s200925)
public class CreateNewProjectAction extends AbstractAction {

    JTextField textField;
    Main main;
    String previousPage;

    public CreateNewProjectAction(String name, String previousPage, JTextField textField, Main main) {
        putValue(NAME, name);
        this.textField = textField;
        this.main = main;
        this.previousPage = previousPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean hasError = false;

        String projectName = textField.getText().trim();

        if (textField.getText() == "") {
            main.app.addProject();
        } else {
            main.app.addProject(projectName);
        }
        Project project = main.app.getProjects().get(main.app.getProjects().size()-1);;
        main.viewProject(project, this.previousPage);

        if (!hasError) {
            SuccessWindow successWindow = new SuccessWindow("Project successfully created.");
            successWindow.showMessage();
        }
    }
}