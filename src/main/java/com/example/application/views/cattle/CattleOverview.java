package com.example.application.views.cattle;


import com.example.application.data.Cattle;
import com.vaadin.flow.component.grid.Grid;
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

public CattleOverview(){
    Grid<Cattle> grid = new Grid<>(Cattle.class, true);
    add(grid);
}
}
