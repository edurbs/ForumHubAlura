package com.company.forumhubalura.view.perfil;

import com.company.forumhubalura.entity.Perfil;
import com.company.forumhubalura.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "perfils", layout = MainView.class)
@ViewController("Perfil.list")
@ViewDescriptor("perfil-list-view.xml")
@LookupComponent("perfilsDataGrid")
@DialogMode(width = "64em")
public class PerfilListView extends StandardListView<Perfil> {
}