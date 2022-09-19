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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value = "index")
public class MainWizardView extends VerticalLayout {
    private TextField fullName, dollars;
    private ComboBox position, school, house;
    private RadioButtonGroup<String> gender;
    private Button prv, create, update, delete, next;
    private Wizards wizards;
    private static int maxPage;
    private static int page = -1;

    public MainWizardView() {
        fullName = new TextField("Fullname");
        gender = new RadioButtonGroup<>("Gender:");
        gender.setItems("Male", "Female");
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


        next.addClickListener(e->{
            if(page < 0){
                page++;
                wizards = WebClient.create().get().uri("http://localhost:8080/wizards").retrieve().bodyToMono(Wizards.class).block();
                maxPage = wizards.getWizards().size();
                System.out.println(page);

                fullName.setValue(wizards.getWizards().get(page).getName());
                if(wizards.getWizards().get(page).getSex().equals("m")){
                    gender.setValue("Male");
                } else if (wizards.getWizards().get(page).getSex().equals("f")){
                    gender.setValue("Female");
                }
                position.setValue(wizards.getWizards().get(page).getPosition());
                dollars.setValue(String.valueOf(wizards.getWizards().get(page).getMoney()));
                school.setValue(wizards.getWizards().get(page).getSchool());
                house.setValue(wizards.getWizards().get(page).getHouse());
            }
            else if(page >= 0 && page+1 < maxPage){
                page++;
                System.out.println(page);
                fullName.setValue(wizards.getWizards().get(page).getName());
                if(wizards.getWizards().get(page).getSex().equals("m")){
                    gender.setValue("Male");
                } else if (wizards.getWizards().get(page).getSex().equals("f")){
                    gender.setValue("Female");
                }
                position.setValue(wizards.getWizards().get(page).getPosition());
                dollars.setValue(String.valueOf(wizards.getWizards().get(page).getMoney()));
                school.setValue(wizards.getWizards().get(page).getSchool());
            }
        });

        prv.addClickListener(e->{
            if(page > 0){
                page--;
                System.out.println(page);
                fullName.setValue(wizards.getWizards().get(page).getName());
                if(wizards.getWizards().get(page).getSex().equals("m")){
                    gender.setValue("Male");
                } else if (wizards.getWizards().get(page).getSex().equals("f")){
                    gender.setValue("Female");
                }
                position.setValue(wizards.getWizards().get(page).getPosition());
                dollars.setValue(String.valueOf(wizards.getWizards().get(page).getMoney()));
                school.setValue(wizards.getWizards().get(page).getSchool());
            }
        });

        create.addClickListener(e->{
//           Wizard wizard = WebClient.create().post().uri("http://localhost:8080/addWizard" +
//                   "").retrieve().bodyToMono(Wizard.class).block();

        });

    }
}
