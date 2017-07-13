package vaadin

import vaadin.security.Role
import vaadin.security.UserRole

class BootStrap {

    def init = { servletContext ->
        println "Loading database..."
        final driver1 = new Driver(name: "Susan", username: "susan", password: "password1").save() //<1>
        final driver2 = new Driver(name: "Pedro", username:  "pedro", password: "password2").save()

        final role = new Role(authority: "ROLE_DRIVER").save()  //<2>

        UserRole.create(driver1, role, true)  //<3>
        UserRole.create(driver2, role, true)

        final nissan = new Make(name: "Nissan").save()
        final ford = new Make(name: "Ford").save()

        final titan = new Model(name: "Titan").save()
        final leaf = new Model(name: "Leaf").save()
        final windstar = new Model(name: "Windstar").save()

        new Vehicle(name: "Pickup", driver: driver1, make: nissan, model: titan).save()
        new Vehicle(name: "Economy", driver: driver1, make: nissan, model: leaf).save()
        new Vehicle(name: "Minivan", driver: driver2, make: ford, model: windstar).save()
    }
    def destroy = {
    }
}
