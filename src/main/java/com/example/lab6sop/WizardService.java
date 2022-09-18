package com.example.lab6sop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;

    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }

    public List<Wizard> retrieveWizards(){
        return repository.findAll();
    }

    public Wizard createWizard(Wizard w){
        return repository.save(w);
    }

    public Wizard updateWizard(Wizard w){
        return repository.save(w);
    }

    public Wizard findNameWizard(String name){
        System.out.println(repository.findByName(name));
        return  repository.findByName(name);
    }

    public boolean deleteWizard(Wizard w){
        try{
            repository.delete(w);
            return true;
        }
        catch(Exception e){
            return  false;
        }
    }

}
