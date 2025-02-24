package com.example.application.views.cattle;


import com.example.application.data.Cattle;
import com.example.application.data.Herd;
import com.example.application.data.Reproduction;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Отелвания")
@Route("reproduction")
@Menu(order = 6, icon = LineAwesomeIconUrl.GLOBE_SOLID)
@AnonymousAllowed
public class ReproductionOverview extends VerticalLayout {
    public ReproductionOverview() {


        Grid<Cattle> cattleGrid = new Grid<>(Cattle.class, false);
        cattleGrid.addColumn("number").setHeader("Cattle Number");
        cattleGrid.addColumn("dateOfBirth").setHeader("DateOfBirth");
        cattleGrid.addColumn(cattle -> Boolean.TRUE.equals(cattle.isGender()) ? "Male" : "Female")
                .setHeader("Gender");

        add(cattleGrid);

        Grid<Reproduction> reproductionGrid = new Grid<>(Reproduction.class,false);
        reproductionGrid.addColumn("eventDateTime").setHeader("Дата на Събитие");
        reproductionGrid.addColumn("category").setHeader("Вид Събитие");
        reproductionGrid.addColumn("description").setHeader("Описание");
        reproductionGrid.addColumn("fatherNumber").setHeader("Номер на бащата");

        add(reproductionGrid);
    }

}




