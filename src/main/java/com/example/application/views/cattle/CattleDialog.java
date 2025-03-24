package com.example.application.views.cattle;

import com.example.application.data.Cattle;
import com.example.application.data.CattleRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

public class CattleDialog extends Dialog {
    private final Cattle cattle;
    private final CattleRepo repo;
    private TextField number= new TextField("CattleID Number");
    private ComboBox<Boolean> gender=new ComboBox("Gender");
    private DatePicker dateOfBirth= new DatePicker("Date of Birth");

    Button saveButton = new Button("Save");
    Button cancelButton = new Button("Cancel", e -> this.close());
    private BeanValidationBinder<Cattle> binder = new BeanValidationBinder<Cattle>(Cattle.class);


    public CattleDialog (Cattle cattle, CattleRepo repo){
        this.cattle = cattle;
        this.repo = repo;
        initContent();
    }

    private void initContent() {

        gender.setItems(true,false);
        gender.setItemLabelGenerator(l->l?"male":"female");
        binder.bindInstanceFields(this);
        binder.readBean(cattle);
        FormLayout formLayout = new FormLayout();

        formLayout.add(number,gender,dateOfBirth);
        add(formLayout);
        getFooter().add(saveButton,cancelButton);
        saveButton.addClickListener(l->{
            try {
                binder.writeBean(cattle);
                repo.save(cattle);
                close();
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }

        });
    }

}
