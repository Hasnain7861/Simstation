package mvc;

//import mineField.MineFieldFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public abstract class AppPanel extends JPanel implements ActionListener, PropertyChangeListener

{
    private AppFactory appFactory;
    protected controlPanel controlPanel;
    private View view;
    private String fName;
    protected Model model;

    public AppPanel(AppFactory appFactory) {
        this.appFactory = appFactory;
        model = appFactory.makeModel();
        view = appFactory.makeView(model);
        controlPanel = new controlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        controlPanel.setPreferredSize(new Dimension(500, 500));
        this.add(view);
        view.setPreferredSize(new Dimension(500,500));
    }

    public void display() {
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(appFactory.getTitle());
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}

    protected class controlPanel extends JPanel {

        public controlPanel() {
            setBackground(Color.PINK);
        }

    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", appFactory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"Help", "About"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmmd = e.getActionCommand();

        try {
            switch (cmmd) {

                case "Save": {
                    model.setUnsavedChanges(false);
                    Utilities.save(model, false);
                    break;
                }

                case "Save As": {
                    Utilities.save(model, true);
                    break;
                }

                case "Open": {
                    model.setUnsavedChanges(false);
                    model = Utilities.open(model);
                    view.setModel(model);
                    break;

                }

                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        fName = null;
                        model = appFactory.makeModel();
                        view.setModel(model);
                        model.setUnsavedChanges(false);
                    }
                    break;
                }

                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(appFactory.about());
                    break;
                }

                case "Help": {
                    String[] cmmds = appFactory.getHelp();
                    Utilities.inform(cmmds);
                    break;

                }

                default: {
                    for (String s: appFactory.getEditCommands()) {
                        if(cmmd == s) {
                            appFactory.makeEditCommand(model, cmmd, null).execute();
                        }
                    }
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

}