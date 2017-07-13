package vaadin

import grails.transaction.Transactional

@Transactional
class ModelService {

    @Transactional(readOnly = true)
    List<Model> listAll() {
        // Vaadin requires it be declared to a variable first or it breaks
        final List<Model> modelList = Model.list()
        modelList // return
    }
}
