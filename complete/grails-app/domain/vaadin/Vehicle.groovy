package vaadin

import grails.compiler.GrailsCompileStatic

//import grails.plugin.springsecurity.annotation.Secured
import groovy.transform.EqualsAndHashCode

@GrailsCompileStatic
@EqualsAndHashCode(includes=['name', 'make', 'model'])
//@ToString(includes=['name', 'make', 'model'], includeNames=true, includePackage=false)
class Vehicle {

    String name

    Make make
    Model model

    static belongsTo = [driver: Driver]
    static mapping = {
        driver lazy: false
        make lazy: false
        model lazy: false
    }

    static constraints = {
    }
}
