package system.view_controller.pages;
import javax.swing.*;
import java.awt.*;
import system.view_controller.constants.Constants;
import system.view_controller.pages.Activity.ActivityPage;
import system.view_controller.pages.Calender.ViewCalender;
import system.view_controller.pages.DeveloperOverview.ChooseOverviewDate;
import system.view_controller.pages.DeveloperOverview.DeveloperOverviewPage;
import system.view_controller.pages.LogIn.LogInPage;
import system.view_controller.pages.ManageProjects.ViewProjectsPage;
import system.view_controller.pages.Navigator.NavigatorPage;
import system.view_controller.pages.Project.NonWorkActivityProjectPage;
import system.view_controller.pages.Project.ProjectPage;
import system.view_controller.pages.TimeRegistration.ChooseActivityPage;
import system.view_controller.pages.TimeRegistration.RegisterTimePage;
import system.view_controller.pages.ManageProjects.CreateNewProjectPage;
import system.model.domain.Activity;
import system.model.domain.App;
import system.model.domain.Developer;
import system.model.domain.Project;
import java.util.*;

public class Main {

    public App app = new App();

    Constants constants = new Constants();
    JFrame frame = new JFrame(constants.softwareName);
    NavigatorPage navigatorPage = new NavigatorPage(frame, this);


    ViewProjectsPage viewProjectsPage = new ViewProjectsPage(frame, this);
    CreateNewProjectPage createNewProjectPage = new CreateNewProjectPage(frame, this);
    ChooseActivityPage timeRegistrationPage = new ChooseActivityPage(frame, this);
    ViewCalender ViewCalender = new ViewCalender(frame, this);
    ChooseOverviewDate chooseOverviewDate = new ChooseOverviewDate(frame, this);



    LogInPage logInPage = new LogInPage(this);

    public Main() {
        prepareFrame();
    }

    public void prepareFrame() {
        frame.setSize(600,800);
        frame.setResizable(false);
        frame.setLocation(500,200);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    public void runApp() {
        JPanel loginPagePanel = logInPage.draw();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(loginPagePanel);
        frame.validate();
        frame.repaint();
    }

    public void viewProject(Project project, String previousPage) {
        frame.getContentPane().removeAll();
        ProjectPage projectPage = new ProjectPage(frame, this, previousPage, project);
        JPanel projectPagePanel = projectPage.draw();
        frame.getContentPane().add(projectPagePanel);
        frame.validate();
        frame.repaint();
    }

    public void viewNonWorkActivityProject(Project project, String previousPage) {
        frame.getContentPane().removeAll();
        NonWorkActivityProjectPage nonWorkActivityProjectPage = new NonWorkActivityProjectPage(frame, this, previousPage, project);
        JPanel nonWorkActivityProjectPagePanel = nonWorkActivityProjectPage.draw();
        frame.getContentPane().add(nonWorkActivityProjectPagePanel);
        frame.validate();
        frame.repaint();
    }

    public void viewActivity(Activity activity, Project previousProject) {
        frame.getContentPane().removeAll();
        ActivityPage activityPage = new ActivityPage(frame, this, activity, previousProject);
        JPanel activityPagePanel = activityPage.draw();
        frame.getContentPane().add(activityPagePanel);
        frame.validate();
        frame.repaint();
    }

    public void registerTimeOnActivity(String ActivityName, String ProjectNumber) {
        frame.getContentPane().removeAll();
        RegisterTimePage registerTimePage = new RegisterTimePage(frame, ActivityName, ProjectNumber, this);
        JPanel registerTimePagePanel = registerTimePage.draw();
        frame.getContentPane().add(registerTimePagePanel);
        frame.validate();
        frame.repaint();
    }

    public void viewDeveloperOverview(int startWeek, int startYear, int endWeek, int endYear, HashMap<Developer, HashMap<Project, ArrayList<Activity>>> developerActivitiesInPeriod) {
        frame.getContentPane().removeAll();
        DeveloperOverviewPage developerOverviewPage = new DeveloperOverviewPage(frame, startWeek, startYear, endWeek, endYear, developerActivitiesInPeriod, this);
        JPanel developerOverviewPagePanel = developerOverviewPage.draw();
        frame.getContentPane().add(developerOverviewPagePanel);
        frame.validate();
        frame.repaint();
    }

    public void changeScreen(String changeTo) {

        if (changeTo == "Log In") {
            frame.getContentPane().removeAll();
            JPanel loginPagePanel = logInPage.draw();
            frame.getContentPane().add(loginPagePanel);
            frame.validate();
            frame.repaint();

        } else if (changeTo == "Navigator") {
            frame.getContentPane().removeAll();
            JPanel navigatorPagePanel = navigatorPage.draw();
            frame.getContentPane().add(navigatorPagePanel);
            frame.validate();
            frame.repaint();

        } else if (changeTo == "Project View") {
            frame.getContentPane().removeAll();
            JPanel viewProjectsPagePanel = viewProjectsPage.draw();
            frame.getContentPane().add(viewProjectsPagePanel);
            frame.validate();
            frame.repaint();


        } else if (changeTo == "Create new project") {
            frame.getContentPane().removeAll();
            JPanel createNewProjectPagePanel = createNewProjectPage.draw();
            frame.getContentPane().add(createNewProjectPagePanel);
            frame.validate();
            frame.repaint();


        } else if (changeTo == "Time Registration") {
            frame.getContentPane().removeAll();
            JPanel timeRegistrationPagePanel = timeRegistrationPage.draw();
            frame.getContentPane().add(timeRegistrationPagePanel);
            frame.validate();
            frame.repaint();

        } else if (changeTo == "View Calender") {
            frame.getContentPane().removeAll();
            JPanel viewCalenderPanel = ViewCalender.draw();
            frame.getContentPane().add(viewCalenderPanel);
            frame.validate();
            frame.repaint();
        } else if (changeTo == "Developer Overview") {
            frame.getContentPane().removeAll();
            JPanel chooseOverviewDatePanel = chooseOverviewDate.draw();
            frame.getContentPane().add(chooseOverviewDatePanel);
            frame.validate();
            frame.repaint();
        }

    }
 
}