package system.view_controller.actions;
import javax.swing.*;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;
import system.model.domain.Project;

public class ProjectButtonAction extends AbstractAction {

    JTextField textField;
    Main main;
    Project project;
    String previousPage;

    public ProjectButtonAction(String projectNumber, String projectName, Project project, String previousPage, Main main) {
        if (projectName.equals("")) {
            putValue(NAME, "Unnamed " + "(" +projectNumber + ")");
        } else {
            putValue(NAME, projectName + " (" +projectNumber + ")");
        }
        this.main = main;
        this.project = project;
        this.previousPage = previousPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.viewProject(project, this.previousPage);
    }
}