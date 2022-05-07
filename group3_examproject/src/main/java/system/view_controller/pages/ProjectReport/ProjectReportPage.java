package system.view_controller.pages.ProjectReport;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import system.model.domain.Activity;
import system.model.domain.Developer;
import system.model.domain.Project;
import system.model.domain.ProjectReport;
import system.view_controller.constants.Constants;
import system.view_controller.widgets.Container;

public class ProjectReportPage {

    ProjectReport projectReport;
    Project project;
    Constants constants = new Constants();

    public ProjectReportPage(ProjectReport projectReport, Project project) {
        this.projectReport = projectReport;
        this.project = project;
    }

    public JPanel draw() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(constants.boxColor);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(constants.backgroundColor);
        headerPanel.setBorder(new EmptyBorder(50,0,25,0));
        JLabel header = new JLabel("Project Report");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        headerPanel.add(header);
        panel.add(headerPanel);


        JPanel InformationPanel = new JPanel(new GridLayout(0,2,5,2));
        InformationPanel.setBorder(new EmptyBorder(25,25,25,25));
        InformationPanel.setBackground(constants.boxColor);


        InformationPanel.add(new JLabel("Project Name:"));
        JLabel nameLabel = new JLabel("" + project.getName());
        if (project.getName().equals("")) {
            nameLabel = new JLabel("Undefined");
        }
        InformationPanel.add(nameLabel);
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);


        InformationPanel.add(new JLabel("Project Number:"));
        JLabel numberLabel = new JLabel("" + project.getProjectNumber());
        InformationPanel.add(numberLabel);
        numberLabel.setHorizontalAlignment(JLabel.RIGHT);


        InformationPanel.add(new JLabel("Estimated project work hours:"));
        JLabel estimatedHoursLabel = new JLabel("" + projectReport.getEstimatedHoursForProject());
        InformationPanel.add(estimatedHoursLabel);
        estimatedHoursLabel.setHorizontalAlignment(JLabel.RIGHT);
        

        InformationPanel.add(new JLabel("Total registered hours to project:"));
        JLabel totalHoursLabel = new JLabel("" + projectReport.getTotalHoursRegisteredToProject());
        InformationPanel.add(totalHoursLabel);
        totalHoursLabel.setHorizontalAlignment(JLabel.RIGHT);



        InformationPanel.add(new JLabel(""));
        InformationPanel.add(new JLabel(""));




        InformationPanel.add(new JLabel("Project Developers:"));
        ArrayList<Developer> developers = project.getDevelopers();

        for (int i = 0; i < developers.size(); i++) {
            
            JLabel developerLabel = new JLabel(developers.get(i).getInitials());
            InformationPanel.add(developerLabel);
            developerLabel.setHorizontalAlignment(JLabel.RIGHT);
            if (!(i == developers.size()-1)) {
                InformationPanel.add(new JLabel(""));
            }
        }


        
        InformationPanel.add(new JLabel(""));
        InformationPanel.add(new JLabel(""));



        InformationPanel.add(new JLabel("Project Activities:"));
        ArrayList<Activity> activities = project.getActivities();

        if (activities.size() == 0) {
            InformationPanel.add(new JLabel("No Activities."));
        } else {
            InformationPanel.add(new JLabel(""));
        }
        
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);

            JLabel activityNameLabel = new JLabel(activity.getName());
            InformationPanel.add(activityNameLabel);
            activityNameLabel.setHorizontalAlignment(JLabel.LEFT);

            JPanel activityPanel = new JPanel(new GridLayout(0,2,0,0));
            activityPanel.setBackground(constants.boxColor);

            JLabel activityEstLabel = new JLabel("Est. hours: " + activity.getEstimatedWorkHours());
            activityPanel.add(activityEstLabel);
            activityEstLabel.setHorizontalAlignment(JLabel.LEFT);

            JLabel activityTotalLabel = new JLabel("Reg. hours: " + activity.getTotalHoursRegistered());
            activityPanel.add(activityTotalLabel);
            activityTotalLabel.setHorizontalAlignment(JLabel.RIGHT);
            
            InformationPanel.add(activityPanel);
        }



        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(450, 500));

        panel.add(InformationScrollPanel);
        



        JPanel container = new Container(panel).getContainer();
        return container;
    }
    
}
