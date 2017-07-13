package vaadin

import grails.compiler.GrailsCompileStatic

//import grails.plugin.springsecurity.annotation.Secured
import grails.rest.Resource
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes=['name'])
@ToString(includes=['name'], includeNames=true, includePackage=false)
class Make {

    String name

    static constraints = {
    }
}
