package demo

import grails.transaction.Transactional

@Transactional
class DriverService {

    @Transactional(readOnly = true)
    List<Driver> listAll() {
        // Vaadin requires it be declared to a variable first or it breaks
        final List<Driver> driverList = Driver.list()
        driverList // return
    }
}
