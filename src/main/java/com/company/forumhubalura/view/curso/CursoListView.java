package com.company.forumhubalura.view.curso;

import com.company.forumhubalura.entity.Curso;
import com.company.forumhubalura.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "cursoes", layout = MainView.class)
@ViewController("Curso.list")
@ViewDescriptor("curso-list-view.xml")
@LookupComponent("cursoesDataGrid")
@DialogMode(width = "64em")
public class CursoListView extends StandardListView<Curso> {
}