package vaadin

import com.vaadin.annotations.Title
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewDisplay
import com.vaadin.server.VaadinRequest
import com.vaadin.annotations.Theme
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.spring.annotation.SpringViewDisplay
import com.vaadin.ui.Button
import com.vaadin.ui.Component
import com.vaadin.ui.Label
import com.vaadin.ui.MenuBar
import com.vaadin.ui.MenuBar.Command
import com.vaadin.ui.MenuBar.MenuItem
import com.vaadin.ui.Panel
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@SpringUI(path="/vaadinui")
@Title("Vaadin Grails")
@Theme("vaadin-grails-theme")
@SpringViewDisplay
class VaadinGrailsUI extends UI implements ViewDisplay {
    private Panel springViewDisplay

    /** Where a line is matters, it can change the position of an element. */
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout()
        root.setSizeFull()
        setContent(root)
        springViewDisplay = new Panel()
        springViewDisplay.setSizeFull()

        root.addComponent(this.buildHeader())
        root.addComponent(springViewDisplay)
        root.setExpandRatio(springViewDisplay, 1.0f)
    }


    private Label buildHeader() {
        final Label mainTitle = new Label("Welcome to the Garage")
        mainTitle // return
    }


    @Override
    void showView(final View view) {
        springViewDisplay.setContent((Component) view)
    }
}