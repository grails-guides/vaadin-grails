package demo

import grails.transaction.Transactional
import groovy.transform.CompileStatic

@CompileStatic
@Transactional
class VehicleService {

    def save(final Vehicle vehicle) {
        vehicle.save(failOnError: true)
    }

    @Transactional(readOnly = true)
    List<Vehicle> listAll(boolean lazyFetch = true) {
        if ( !lazyFetch ) {
            return Vehicle.list(fetch: [make: 'eager', model: 'eager', driver: 'eager'])
        }
        Vehicle.where { }.list()
    }
}
