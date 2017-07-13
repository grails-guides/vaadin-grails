package vaadin

import grails.compiler.GrailsCompileStatic

//import grails.plugin.springsecurity.annotation.Secured
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import vaadin.security.User

@GrailsCompileStatic
@EqualsAndHashCode(includes=['name'])
@ToString(includes=['name'], includeNames=true, includePackage=false)
class Driver extends User {

    String name

    static hasMany = [ vehicles: Vehicle ]

    static constraints = {
        vehicles nullable: true
    }
}
