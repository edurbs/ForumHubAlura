package com.company.forumhubalura.view.topico;

import com.company.forumhubalura.entity.Topico;
import com.company.forumhubalura.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.core.validation.group.UiCrossFieldChecks;
import io.jmix.flowui.component.validation.ValidationErrors;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.model.InstanceLoader;
import io.jmix.flowui.view.*;


@Route(value = "topicoes", layout = MainView.class)
@ViewController("Topico.list")
@ViewDescriptor("topico-list-view.xml")
@LookupComponent("topicoesDataGrid")
@DialogMode(width = "64em")
public class TopicoListView extends StandardListView<Topico> {

    @ViewComponent
    private DataContext dataContext;

    @ViewComponent
    private CollectionContainer<Topico> topicoesDc;

    @ViewComponent
    private InstanceContainer<Topico> topicoDc;

    @ViewComponent
    private InstanceLoader<Topico> topicoDl;

    @ViewComponent
    private VerticalLayout listLayout;

    @ViewComponent
    private FormLayout form;

    @ViewComponent
    private HorizontalLayout detailActions;

    @Subscribe
    public void onInit(final InitEvent event) {
        updateControls(false);
    }

    @Subscribe("topicoesDataGrid.create")
    public void onTopicoesDataGridCreate(final ActionPerformedEvent event) {
        dataContext.clear();
        Topico entity = dataContext.create(Topico.class);
        topicoDc.setItem(entity);
        updateControls(true);
    }

    @Subscribe("topicoesDataGrid.edit")
    public void onTopicoesDataGridEdit(final ActionPerformedEvent event) {
        updateControls(true);
    }

    @Subscribe("saveBtn")
    public void onSaveButtonClick(final ClickEvent<JmixButton> event) {
        Topico item = topicoDc.getItem();
        ValidationErrors validationErrors = validateView(item);
        if (!validationErrors.isEmpty()) {
            ViewValidation viewValidation = getViewValidation();
            viewValidation.showValidationErrors(validationErrors);
            viewValidation.focusProblemComponent(validationErrors);
            return;
        }
        dataContext.save();
        topicoesDc.replaceItem(item);
        updateControls(false);
    }

    @Subscribe("cancelBtn")
    public void onCancelButtonClick(final ClickEvent<JmixButton> event) {
        dataContext.clear();
        topicoDl.load();
        updateControls(false);
    }

    @Subscribe(id = "topicoesDc", target = Target.DATA_CONTAINER)
    public void onTopicoesDcItemChange(final InstanceContainer.ItemChangeEvent<Topico> event) {
        Topico entity = event.getItem();
        dataContext.clear();
        if (entity != null) {
            topicoDl.setEntityId(entity.getId());
            topicoDl.load();
        } else {
            topicoDl.setEntityId(null);
            topicoDc.setItem(null);
        }
    }

    protected ValidationErrors validateView(Topico entity) {
        ViewValidation viewValidation = getViewValidation();
        ValidationErrors validationErrors = viewValidation.validateUiComponents(form);
        if (!validationErrors.isEmpty()) {
            return validationErrors;
        }
        validationErrors.addAll(viewValidation.validateBeanGroup(UiCrossFieldChecks.class, entity));
        return validationErrors;
    }

    private void updateControls(boolean editing) {
        form.getChildren().forEach(component -> {
            if (component instanceof HasValueAndElement<?, ?> field) {
                field.setReadOnly(!editing);
            }
        });

        detailActions.setVisible(editing);
        listLayout.setEnabled(!editing);
    }

    private ViewValidation getViewValidation() {
        return getApplicationContext().getBean(ViewValidation.class);
    }
}