package system.view_controller.actions;
import javax.swing.*;

import system.view_controller.pages.Main;

import java.awt.event.ActionEvent;

public class MainMenuAction extends AbstractAction {

    String changeTo;
    Main main;

    public MainMenuAction(String name, String changeTo, Main main) {
        putValue(NAME, name);
        this.changeTo = changeTo;
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.changeScreen(changeTo);
    }

}
