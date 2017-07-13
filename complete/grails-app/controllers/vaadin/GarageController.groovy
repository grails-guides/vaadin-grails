package vaadin

import grails.compiler.GrailsCompileStatic
import grails.converters.JSON

@GrailsCompileStatic
class GarageController {

    VehicleService vehicleService //<1>

    def index() { //<2>
        final List<Vehicle> vehicleList = vehicleService.listAll()

        render vehicleList as JSON
    }
}
