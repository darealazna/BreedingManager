package com.example.application.views.herd;

import com.example.application.data.Cattle;
import com.example.application.data.CattleRepo;
import com.example.application.data.Herd;
import com.example.application.data.HerdRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Стада")
@Route("herd")
@Menu(order = 5, icon = LineAwesomeIconUrl.GLOBE_SOLID)
@AnonymousAllowed
public class HerdOverview extends VerticalLayout {
    private final HerdRepo herdRepo;
    private final CattleRepo cattleRepo;

    private Grid<Herd> grid;

    public HerdOverview(HerdRepo herdRepo, CattleRepo cattleRepo) {
        this.herdRepo = herdRepo;
        this.cattleRepo = cattleRepo;

        initContent(herdRepo);
    }

    private void initContent(HerdRepo herdRepo) {
        initHerdGrid();
        add(createHerdButtons());
        add(grid);
        add(createCattleGrid());
    }

    private Grid<Cattle> createCattleGrid() {
        Grid<Cattle> cattleGrid = new Grid<>(Cattle.class, false);
        cattleGrid.addColumn("number").setHeader("Cattle Number");
        cattleGrid.addColumn("dateOfBirth").setHeader("DateOfBirth");
        cattleGrid.addColumn(cattle -> Boolean.TRUE.equals(cattle.isGender()) ? "Male" : "Female")
                .setHeader("Gender");
        return cattleGrid;
    }

    private HorizontalLayout createHerdButtons() {
        Button addButton = createAddButton();
        Button editButton = createEditButton();
        Button removeButton = createRemoveButton();
        Button addCattleToHerdButton = createAddCattleToHerdButton();
        HorizontalLayout layout = new HorizontalLayout(addButton, editButton, removeButton, addCattleToHerdButton);
        return layout;
    }

    private Button createAddCattleToHerdButton() {
        Button button = new Button("Добави в стадото");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.setEnabled(false);
        grid.asSingleSelect().addValueChangeListener(event -> {
            button.setEnabled(event.getValue() != null);
        });
        button.addClickListener(l->{
            Dialog dialog = new Dialog();
            dialog.setMinWidth("600px");
            final Herd selectedHerd = grid.asSingleSelect().getValue();
            List<Cattle> cattleList = selectedHerd.getCattle();
            List<Cattle> cattles = cattleRepo.findAll().stream().filter(c -> !cattleList.contains(c)).collect(Collectors.toList());
            Grid<Cattle> cattleGrid = createCattleGrid();
            cattleGrid.setItems(cattles);
            cattleGrid.setSelectionMode(Grid.SelectionMode.MULTI);
            dialog.add(cattleGrid);
            final Button save = new Button("Save");
            save.setEnabled(false);
            final Button cancel = new Button("Cancel", l2->dialog.close());
            dialog.getFooter().add(save,cancel);
            dialog.open();
        });
        return button;
    }

    private Button createRemoveButton() {
        Button removeButton = new Button("Remove");
        removeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        removeButton.setEnabled(false);
        grid.asSingleSelect().addValueChangeListener(event -> {
            // Enable the button if a row is selected, otherwise disable it
            removeButton.setEnabled(event.getValue() != null);
        });
        return removeButton;
    }

    private Button createAddButton() {
        Button addButton = new Button("Add", l -> openHerdDialog(new Herd()));
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return addButton;
    }

    private Button createEditButton() {
        Button editButton = new Button("Edit", l -> openHerdDialog(grid.asSingleSelect().getValue()));
        editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        editButton.setEnabled(false);
        grid.asSingleSelect().addValueChangeListener(event -> {
            // Enable the button if a row is selected, otherwise disable it
            editButton.setEnabled(event.getValue() != null);
        });
        return editButton;
    }

    private void openHerdDialog(Herd value) {
        HerdDialog herdDialog = new HerdDialog(value, herdRepo);
        herdDialog.open();
        herdDialog.addOpenedChangeListener(l2 -> {
            if (!l2.isOpened()) {
                grid.setItems(herdRepo.findAll());
            }
        });
    }

    private void initHerdGrid() {
        grid = new Grid<>(Herd.class, false);
        grid.addColumn("name").setHeader("Име");
        grid.addColumn("description").setHeader("Описание");
        grid.setItems(herdRepo.findAll());
    }

}



