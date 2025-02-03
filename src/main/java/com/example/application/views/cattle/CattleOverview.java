package com.example.application.views.cattle;


import com.example.application.data.Cattle;
import com.example.application.data.CattleRepo;
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

@PageTitle("Говеда")
@Route("cattle")
@Menu(order = 4, icon = LineAwesomeIconUrl.GLOBE_SOLID)
@AnonymousAllowed
public class CattleOverview extends VerticalLayout {

public CattleOverview(CattleRepo cattleRepo){
    Grid<Cattle> grid = new Grid<>(Cattle.class, true);
    Button addButton = new Button("Add",l-> {
        Dialog dialog = new Dialog();

        dialog.setHeaderTitle("New employee");


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
    Button editButton = new Button("Edit");
    editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    Button removeButton = new Button("Remove");
    removeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    HorizontalLayout layout = new HorizontalLayout(addButton,editButton,removeButton);
grid.setItems(cattleRepo.findAll());
    add(layout);
    add(grid);


}
}
