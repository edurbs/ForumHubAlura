package com.company.forumhubalura.listener;

import com.company.forumhubalura.entity.Topico;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TopicoEventListener {

    private final DataManager dataManager;

    public TopicoEventListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventListener
    public void onTopicoSaving(final EntitySavingEvent<Topico> event) {

        Topico topico = event.getEntity();
        if (validateTopico(topico)) {
            throw new IllegalStateException("O tópico não pode ser igual a outro com a mesma mensagem");
        }
    }


    private boolean validateTopico(Topico topico) {

        long topicos = dataManager
                .load(Topico.class)
                .condition(LogicalCondition.and(
                        PropertyCondition.equal("titulo", topico.getTitulo()),
                        PropertyCondition.equal("mensagem", topico.getMensagem())
                                ))
                .optional().stream().count();
        return topicos > 0;
    }
}