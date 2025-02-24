package com.example.application.views.cattle;

import com.example.application.data.Cattle;
import com.example.application.data.CattleRepo;
import com.example.application.data.Herd;
import com.example.application.data.HerdRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Стада")
@Route("herd")
@Menu(order = 5, icon = LineAwesomeIconUrl.GLOBE_SOLID)
@AnonymousAllowed
public class HerdOverview extends VerticalLayout {
    public HerdOverview(HerdRepo herdRepo, CattleRepo cattleRepo){
        Grid<Herd> grid = new Grid<>(Herd.class, false);
        grid.addColumn("name").setHeader("Име");
        grid.addColumn("description").setHeader("Описание");
        Button addButton = new Button("Add",l-> {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("New cattle");

            //dialog.add(dialogLayout);


            Button saveButton = new Button("Save");
            Button cancelButton = new Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);
            dialog.open();


            ComboBox<Boolean> comboBox = new ComboBox<>("Gender");
            comboBox.setItems(true,false);
            comboBox.setItemLabelGenerator(g->g?"male":"female");
            dialog.add(comboBox);
        });
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button editButton = new Button("Edit", l -> {
            Dialog editButtonDialog = new Dialog();

            editButtonDialog.setHeaderTitle("Edit cattle");

            Button saveButton = new Button("Save");
            Button cancelButton = new Button("Cancel", e -> editButtonDialog.close());
            editButtonDialog.getFooter().add(cancelButton);
            editButtonDialog.getFooter().add(saveButton);
            editButtonDialog.open();
        });
        editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        editButton.setEnabled(false);
        grid.asSingleSelect().addValueChangeListener(event -> {
            // Enable the button if a row is selected, otherwise disable it
            editButton.setEnabled(event.getValue() != null);
        });
        Button removeButton = new Button("Remove");
        removeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        removeButton.setEnabled(false);
        grid.asSingleSelect().addValueChangeListener(event -> {
            // Enable the button if a row is selected, otherwise disable it
            removeButton.setEnabled(event.getValue() != null);
        });
        Button cullButton = new Button("Cull");
        cullButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cullButton.setEnabled(false);
        grid.asSingleSelect().addValueChangeListener(event -> {
            // Enable the button if a row is selected, otherwise disable it
            cullButton.setEnabled(event.getValue() != null);
        });
        HorizontalLayout layout = new HorizontalLayout(addButton,editButton,removeButton,cullButton);
        grid.setItems(herdRepo.findAll());
        add(layout);
       add(grid);

        Grid<Cattle> cattleGrid = new Grid<>(Cattle.class, false);
        cattleGrid.addColumn("number").setHeader("Cattle Number");
        cattleGrid.addColumn("dateOfBirth").setHeader("DateOfBirth");
        cattleGrid.addColumn(cattle->Boolean.TRUE.equals(cattle.isGender()) ? "Male" : "Female")
                .setHeader("Gender");

        add(cattleGrid);

    }

}



