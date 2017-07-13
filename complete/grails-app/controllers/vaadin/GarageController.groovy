package vaadin

import grails.compiler.GrailsCompileStatic
import grails.converters.JSON

@GrailsCompileStatic
class GarageController {

    VehicleService vehicleService

    def index() {
        final List<Vehicle> vehicleList = vehicleService.listAll()

        render vehicleList as JSON
    }
}
