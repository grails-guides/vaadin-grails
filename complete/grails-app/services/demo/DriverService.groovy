package demo

import grails.transaction.Transactional
import groovy.transform.CompileStatic

@CompileStatic
@Transactional
class DriverService {

    @Transactional(readOnly = true)
    List<Driver> listAll() {
        Driver.where { }.list()
    }
}
