package system.view_controller.actions;
import javax.swing.*;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;
import system.model.domain.Project;


// Action to go to a specific project // Responsible - Mads Ringsted (s204144)
public class ProjectButtonAction extends AbstractAction {

    JTextField textField;
    Main main;
    Project project;
    String previousPage;

    public ProjectButtonAction(String projectNumber, String projectName, Project project, String previousPage, Main main) {
        if (project.isNonWorkActivityProject()){
            putValue(NAME, projectName);
        } else {
            if (projectName.equals("")) {
                putValue(NAME, "Unnamed " + "(" +projectNumber + ")");
            } else {
                putValue(NAME, projectName + " (" +projectNumber + ")");
            }
        }
        
        this.main = main;
        this.project = project;
        this.previousPage = previousPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (project.isNonWorkActivityProject()) {
            main.viewNonWorkActivityProject(project, this.previousPage);
        } else {
            main.viewProject(project, this.previousPage);
        }
    }
}