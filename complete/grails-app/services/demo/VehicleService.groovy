package demo

import grails.transaction.Transactional

@Transactional
class VehicleService {

    def save(final Vehicle vehicle) {
        vehicle.save(failOnError: true)
    }

    @Transactional(readOnly = true)
    List<Vehicle> listAll() {
        // Vaadin requires it be declared to a variable first or it breaks
        final List<Vehicle> vehicleList = Vehicle.list()
        vehicleList // return
    }
}
