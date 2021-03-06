package system.view_controller.pages.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.SubHeader;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.constants.Constants;
import system.model.domain.DeveloperCalendar;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import java.util.*;


// View Calendar // Responsible - Kasper Petersen (s203294)
public class ViewCalendar {

    JFrame frame;
    Main main;
    Constants constants = new Constants();
    
    public ViewCalendar(JFrame frame, Main main) {
        this.frame = frame;
        this.main = main;
    }

    public JPanel draw() {
        JPanel BoxPanel = new BoxPanel().getPanel();
        BoxPanel.setPreferredSize(new Dimension(350, 700));

        JLabel subHeader = new JLabel("My Calendar");
        subHeader.setFont(new Font("Arial", Font.BOLD, 25));
        subHeader.setBorder(new EmptyBorder(75,0,25,0));
        BoxPanel.add(subHeader);
        subHeader.setAlignmentX(BoxPanel.CENTER_ALIGNMENT);
        subHeader.setAlignmentY(BoxPanel.CENTER_ALIGNMENT);

        JPanel InformationPanel = new JPanel();
        InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.Y_AXIS));
        InformationPanel.setBackground(constants.boxColor);


        DeveloperCalendar developerCalendar = main.app.getCurrentUser().getDeveloperCalendar();
        HashMap<String, HashMap<String, HashMap<String, Double>>> calendar = developerCalendar.getCalendar();


        if (calendar.keySet().size() < 1) {
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new GridBagLayout());
            JLabel label = new JLabel("<html>You currently have no registered hours for any activities. Register hours on an assigned activity to view them here.</html>");
            label.setPreferredSize(new Dimension(250, 200));
            labelPanel.setBackground(constants.boxColor);
            labelPanel.add(label);
            labelPanel.setPreferredSize(new Dimension(350, 700));
            BoxPanel.add(labelPanel);

        } else {

            JPanel ProjectViewPanel = new JPanel();
            ProjectViewPanel.setLayout(new BoxLayout(ProjectViewPanel, BoxLayout.Y_AXIS));
            ProjectViewPanel.setBackground(constants.boxColor);
            
            // sort date keys
            // get date keys from the calendar for the days with registered hours
            ArrayList<String> dateKeys = new ArrayList<>(developerCalendar.getCalendar().keySet());
            // convert from string to int
            ArrayList<Integer> sortedDateKeysInts = new ArrayList<>();
            for(String s : dateKeys) sortedDateKeysInts.add(Integer.valueOf(s));
            // sort datekeys
            Collections.sort(sortedDateKeysInts);
            // convert back to string
            ArrayList<String> sortedDateKeys = new ArrayList<>();
            for(Integer i : sortedDateKeysInts) sortedDateKeys.add(Integer.toString(i));
            

            for ( String key : sortedDateKeys ) {

                JPanel DayPanelBorder = new JPanel();
                DayPanelBorder.setLayout(new BoxLayout(DayPanelBorder, BoxLayout.Y_AXIS));
                DayPanelBorder.setBackground(constants.boxColor);
                DayPanelBorder.setBorder(new EmptyBorder(20,10,10,10));

                JPanel DayPanel = new JPanel();
                DayPanel.setLayout(new BoxLayout(DayPanel, BoxLayout.Y_AXIS));
                DayPanel.setBackground(constants.calendarColor);
                DayPanel.setBorder(new EmptyBorder(10,10,10,10));

                int keyLength = key.length();
                String year = key.substring(0,keyLength-4);
                String week = key.substring(keyLength-4,keyLength-2);
                String day = key.substring(keyLength-2,keyLength);
    
                JLabel dateHeader = new JLabel("Day: " + day + ", Week: " + week + ", Year: " + year);
                dateHeader.setFont(new Font("Arial", Font.BOLD, 20));
                dateHeader.setBorder(new EmptyBorder(10,0,0,0));
                dateHeader.setBackground(constants.secondBoxColor);
                DayPanel.add(dateHeader);
                dateHeader.setAlignmentX(DayPanel.CENTER_ALIGNMENT);
                dateHeader.setAlignmentY(DayPanel.CENTER_ALIGNMENT);

                for ( String projectNumber : calendar.get(key).keySet() ) {

                    try {
                        Project project = main.app.getProject(projectNumber);
                        JLabel projectHeader = new JLabel(project.getName() + " (" + projectNumber + ")");
                        if (project.isNonWorkActivityProject()){
                            projectHeader = new JLabel(project.getName());
                        }
                        projectHeader.setBorder(new EmptyBorder(20,0,10,0));
                        projectHeader.setFont(new Font("Arial", Font.BOLD, 15));
                        projectHeader.setBackground(constants.secondBoxColor);
                        DayPanel.add(projectHeader);
                        projectHeader.setAlignmentX(DayPanel.CENTER_ALIGNMENT);
                        projectHeader.setAlignmentY(DayPanel.CENTER_ALIGNMENT);
                    } catch (OperationNotAllowedException e) {
                        JLabel projectHeader = new JLabel(projectNumber);
                        projectHeader.setFont(new Font("Arial", Font.BOLD, 15));
                        DayPanel.add(projectHeader);
                        projectHeader.setAlignmentX(DayPanel.CENTER_ALIGNMENT);
                        projectHeader.setAlignmentY(DayPanel.CENTER_ALIGNMENT);
                    }

                    for ( String activityKey : calendar.get(key).get(projectNumber).keySet() ) {

                        JLabel activityHeader = new JLabel(activityKey + ": " + calendar.get(key).get(projectNumber).get(activityKey) + " hours");
                        activityHeader.setFont(new Font("Arial", Font.BOLD, 10));
                        activityHeader.setBackground(constants.secondBoxColor);
                        DayPanel.add(activityHeader);
                        activityHeader.setAlignmentX(DayPanel.CENTER_ALIGNMENT);
                        activityHeader.setAlignmentY(DayPanel.CENTER_ALIGNMENT);
                        DayPanelBorder.add(DayPanel);
                        ProjectViewPanel.add(DayPanelBorder);

                    }

                }

                
            }

            JScrollPane scrollPanel = new JScrollPane(ProjectViewPanel);
            scrollPanel.setBackground(constants.boxColor);
            scrollPanel.setPreferredSize(new Dimension(350, 700));
            BoxPanel.add(scrollPanel);
            
        }


        AbstractAction backToMainMenuAction = new MainMenuAction("Back", "Navigator", main);
        JPanel backToMainMenuButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainMenuAction).getButton();
        backToMainMenuButtonPanel.setBorder(new EmptyBorder(10,0,10,0));
        BoxPanel.add(backToMainMenuButtonPanel);

        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}
