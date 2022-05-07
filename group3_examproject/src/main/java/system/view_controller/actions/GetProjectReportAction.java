package system.view_controller.actions;
import javax.swing.*;
import java.awt.*;

import system.model.domain.Activity;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.model.domain.ProjectReport;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;
import system.view_controller.pages.ProjectReport.ProjectReportPage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GetProjectReportAction extends AbstractAction {

    Main main;
    Project project;

    public GetProjectReportAction(String name, Project project, Main main) {
        putValue(NAME, name);
        this.main = main;
        this.project = project;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Boolean hasError = false;

        try {
            main.app.generateProjectReport(project.getProjectNumber());
            ProjectReport projectReport = main.app.getCurrentProjectReport();
            JFrame frame = new JFrame("Project Report of " + project.getName() + " (" + project.getProjectNumber() + ")");
            frame.setSize(500,700);
            frame.setResizable(false);
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
            frame.setLocation(x, y);
            frame.setVisible(true);

            frame.getContentPane().removeAll();
            JPanel projectReportPage = new ProjectReportPage(projectReport, project).draw();
            frame.getContentPane().add(projectReportPage);
            frame.validate();
            frame.repaint();


        } catch (OperationNotAllowedException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
            hasError = true;
        }


        if (!hasError) {
            SuccessWindow successWindow = new SuccessWindow("Report generated successfully.");
            successWindow.showMessage();
           }


    }
}