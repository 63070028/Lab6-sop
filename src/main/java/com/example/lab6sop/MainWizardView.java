package com.example.lab6sop;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Route(value = "index")
public class MainWizardView extends VerticalLayout {
    private TextField fullName, dollars;
    private ComboBox position, school, house;
    private RadioButtonGroup<String> gender;
    private Button prv, create, update, delete, next;
    WizardController wizardController;

    public MainWizardView() {
        fullName = new TextField("Fullname");
        gender = new RadioButtonGroup<>("Gender:");
        gender.setItems("Male", "Famale");
        position = new ComboBox<>();
        position.setItems("Student", "Teacher");
        position.setPlaceholder("Position");
        dollars = new TextField("Dollars");
        dollars.setPrefixComponent(new Span("$"));
        school = new ComboBox<>();
        school.setItems( "Hogwarts", "Beauxbatons", "Durmstrang");
        school.setPlaceholder("School");
        house = new ComboBox<>();
        house.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slyther");
        house.setPlaceholder("House");
        prv = new Button("<<");
        create = new Button("Create");
        update = new Button("Update");
        delete = new Button("Delete");
        next = new Button(">>");

        HorizontalLayout hl  = new HorizontalLayout();
        hl.add(prv,create, update, delete, next);

        add(fullName, gender, position, dollars, dollars, school, house, hl);

//        ArrayList<Wizard> wizards = wizardController.getWizard();
//        for (Wizard w: wizards) {
//            System.out.println(w.getName());
//        }
    }
}
