package vaadin

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes=['name'])
@ToString(includes=['name'], includeNames=true, includePackage=false)
class Model {

    String name

    static constraints = {
    }
}
