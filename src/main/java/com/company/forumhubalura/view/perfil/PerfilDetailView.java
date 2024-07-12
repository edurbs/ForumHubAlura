package com.company.forumhubalura.view.perfil;

import com.company.forumhubalura.entity.Perfil;
import com.company.forumhubalura.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "perfils/:id", layout = MainView.class)
@ViewController("Perfil.detail")
@ViewDescriptor("perfil-detail-view.xml")
@EditedEntityContainer("perfilDc")
public class PerfilDetailView extends StandardDetailView<Perfil> {
}