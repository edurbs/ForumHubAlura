package com.company.forumhubalura.view.curso;

import com.company.forumhubalura.entity.Curso;
import com.company.forumhubalura.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "cursoes/:id", layout = MainView.class)
@ViewController("Curso.detail")
@ViewDescriptor("curso-detail-view.xml")
@EditedEntityContainer("cursoDc")
public class CursoDetailView extends StandardDetailView<Curso> {
}