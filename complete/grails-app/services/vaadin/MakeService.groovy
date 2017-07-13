package vaadin

import grails.transaction.Transactional

@Transactional
class MakeService {

    @Transactional(readOnly = true)
    List<Make> listAll() {
        // Vaadin requires it be declared to a variable first or it breaks
        final List<Make> makeList = Make.list()
        makeList // return
    }
}
