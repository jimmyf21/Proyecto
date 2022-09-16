package sistema.presentation.principal;

import sistema.application.Application;
import sistema.logic.Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControllerPrincipal implements ActionListener {
    Model model;
    View view;

    public ControllerPrincipal(View view, Model model) {
        this.model = model;
        this.view = view;


        view.setModel(model);
        view.setController(this);
    }

    public void exit(){
        Service.instance().store();
    }


    // ActionListener Interface
    public void actionPerformed(ActionEvent e) {
        model.commit();
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
        Application.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().store();
            }
        });
    }

}