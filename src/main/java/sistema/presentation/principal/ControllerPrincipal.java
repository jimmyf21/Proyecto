package sistema.presentation.principal;

import sistema.application.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPrincipal implements ActionListener {
    Model model;
    View view;

    public ControllerPrincipal(View view, Model model) {
        this.model = model;
        this.view = view;


        view.setModel(model);
        view.setController(this);
    }


    // ActionListener Interface
    public void actionPerformed(ActionEvent e) {
        model.commit();
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

}