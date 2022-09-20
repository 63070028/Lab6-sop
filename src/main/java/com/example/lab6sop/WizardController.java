package com.example.lab6sop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;


    @RequestMapping(value ="/wizards", method = RequestMethod.GET)
    public Wizards getWizard(){
        Wizards wizards = new Wizards();
        wizards.setWizards(new ArrayList<Wizard>(wizardService.retrieveWizards()));

        return wizards;
    }

    @RequestMapping(value ="/addWizard", method = RequestMethod.POST)
    public Wizard addWizard(@RequestParam("sex") String sex, @RequestParam("name") String name,
                          @RequestParam("school") String school, @RequestParam("house") String house,
                          @RequestParam("money") int money, @RequestParam("position") String position){
        Wizard wizard = wizardService.createWizard(new Wizard(null, sex, name, school, house, money, position));
        return wizard;

    }

    @RequestMapping(value ="/updateWizard", method = RequestMethod.POST)
    public boolean updateWizard(@RequestParam("sex") String sex, @RequestParam("nameNew") String nameNew, @RequestParam("nameOld") String nameOld,
                                @RequestParam("school") String school, @RequestParam("house") String house,
                                @RequestParam("money") int money, @RequestParam("position") String position){

            Wizard w = wizardService.findNameWizard(nameOld);
            System.out.println(w.getName());
            if(w != null){
                wizardService.updateWizard(new Wizard(w.get_id(), sex, nameNew, school, house, money, position));
                return true;
            }
            else{
                return false;
            }

    }

    @RequestMapping(value ="/deleteWizard", method = RequestMethod.POST)
    public boolean deleteWizard(@RequestParam("name") String name){
        Wizard w = wizardService.findNameWizard(name);
        System.out.println(w.getName());
        return wizardService.deleteWizard(w);
    }

}
