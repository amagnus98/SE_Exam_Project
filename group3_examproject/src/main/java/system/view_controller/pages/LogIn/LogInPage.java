package system.view_controller.pages.LogIn;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessHandle.Info;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.Text;

import system.view_controller.constants.Constants;
import system.view_controller.pages.Main;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.Header;
import system.view_controller.widgets.SubHeader;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.TextField;
import system.view_controller.actions.LogInAction;

public class LogInPage {

    Main main;
    Constants constants = new Constants();

    public LogInPage(Main main) {
        this.main = main;
    }

    public JPanel draw() {

        JPanel InformationPanel = new JPanel();
        InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.Y_AXIS));
        InformationPanel.setBackground(constants.backgroundColor);


        try {
            JPanel headerPanel = new JPanel();
            Path currentRelativePath = Paths.get("");
            String path = currentRelativePath.toAbsolutePath().toString();
            path = path.replace("\\", "/");
            String pathImage = path + "/src/main/java/system/view_controller/pages/LogIn/assets/logo.png";
            File image = new File(pathImage);
            BufferedImage logo = ImageIO.read(image);
            JLabel logoLabel = new JLabel(new ImageIcon(logo));
            headerPanel.add(logoLabel);
            headerPanel.setBackground(constants.backgroundColor);
            headerPanel.setBorder(new EmptyBorder(75,0,0,0));
            InformationPanel.add(headerPanel);
        } catch (IOException e) {
            System.out.println(e);
            JPanel headerPanel = new JPanel();
            JLabel headerLabel = new JLabel("KAMMA Time Registration");
            headerLabel.setFont(new Font("Arial", Font.BOLD, 25));
            headerPanel.add(headerLabel);
            headerPanel.setBackground(constants.backgroundColor);
            headerPanel.setBorder(new EmptyBorder(75,0,0,0));
            InformationPanel.add(headerPanel);
        }

        // set header
        new Header("Login Page", InformationPanel);
    
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(new EmptyBorder(30,0,30,0));
        loginPanel.setBackground(constants.boxColor);
        
        new SubHeader("Please enter your initials here:", constants.boxColor, loginPanel);

        TextField textField = new TextField("Initials", "", constants.boxColor).getTextField();
        loginPanel.add(textField.textFieldPanel);

        AbstractAction action = new LogInAction("Log in", textField.textField, main);
        JPanel ButtonPanel = new Button("Log in", constants.boxColor, "small", action).getButton();
        loginPanel.add(ButtonPanel);
        InformationPanel.add(loginPanel);


        JPanel container = new Container(InformationPanel).getContainer();
        InformationPanel.setAlignmentX(container.CENTER_ALIGNMENT);
        InformationPanel.setAlignmentY(container.CENTER_ALIGNMENT);
        return container;
    }
}
