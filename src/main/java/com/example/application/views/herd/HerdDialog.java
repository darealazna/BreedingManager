package com.example.application.views.herd;

import com.example.application.data.AbstractEntity;
import com.example.application.data.Cattle;
import com.example.application.data.Herd;
import com.example.application.data.HerdRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class HerdDialog extends Dialog {
    private final HerdRepo repo;
    private final Herd herd;
    private TextField name = new TextField();
    private TextArea description = new TextArea();

    Button saveButton = new Button("Save");
    Button cancelButton = new Button("Cancel", e -> this.close());
    private BeanValidationBinder<Herd> binder = new BeanValidationBinder<>(Herd.class);

    public HerdDialog(Herd herd, HerdRepo repo){
        this.herd=herd;
        this.repo=repo;
        initContent();
    }

    private void initContent() {
        binder.bindInstanceFields(this);
        binder.readBean(herd);
        createMainContent();
        configureButtons();
    }

    private void createMainContent() {
        final FormLayout layout = new FormLayout();
        layout.addFormItem(name, "Име на стадо");
        layout.addFormItem(description, "Бележки");
        add(layout);
    }

    private void configureButtons() {
        cancelButton.addClickListener(l->close());
        saveButton.addClickListener(l->{
           if(binder.writeBeanIfValid(herd)){
               repo.save(herd);
               close();
           }
        });
        getFooter().add(saveButton,cancelButton);
    }
}
