package demo

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Button
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Grid
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import org.springframework.beans.factory.annotation.Autowired
import javax.annotation.PostConstruct

@SpringView(name = GarageView.VIEW_NAME) //<1>
class GarageView extends VerticalLayout implements View { //<2>
    public static final String VIEW_NAME = "" //<3>

    /** Service Declarations: ------------------------------------------------------------------------------------------
      * Grails auto dependency injection is not able to detect services in Vaadin, thus we require using the more
      * traditional Spring annotation @Autowired in order to get dependency injection to work properly.
      * ------------------------------------------------------------------------------------------------------------- */
    @Autowired private DriverService driverService
    @Autowired private MakeService makeService
    @Autowired private ModelService modelService
    @Autowired private VehicleService vehicleService

    private Vehicle vehicle = new Vehicle()

    @PostConstruct //<4>
    void init() {
        /** Display Row One: (Add panel title) ---------------------------------------------------------------------- */
        final HorizontalLayout titleRow = new HorizontalLayout()
        titleRow.setWidth("100%")
        addComponent(titleRow)

        final Label title = new Label("Add a Vehicle:")
        titleRow.addComponent(title)
        titleRow.setExpandRatio(title, 1.0f) // Expand

        /** Display Row Two: (Build data input) --------------------------------------------------------------------- */
        final HorizontalLayout inputRow = new HorizontalLayout()
        inputRow.setWidth("100%")
        addComponent(inputRow)

        // Build data input constructs
        final TextField vehicleName = this.buildNewVehicleName()
        final ComboBox<Make> vehicleMake = this.buildMakeComponent()
        final ComboBox<Model> vehicleModel = this.buildModelComponent()
        final ComboBox<Driver> vehicleDriver = this.buildDriverComponent()
        final Button submitBtn = this.buildSubmitButton()

        // Add listeners to capture data change
        vehicleName.addValueChangeListener { event -> this.updateVehicle("NAME", event.value) }
        vehicleMake.addSelectionListener   { event -> this.updateVehicle("MAKE", event.getSelectedItem().get()) }
        vehicleModel.addSelectionListener  { event -> this.updateVehicle("MODEL", event.getSelectedItem().get()) }
        vehicleDriver.addSelectionListener { event -> this.updateVehicle("DRIVER", event.getSelectedItem().get()) }
        submitBtn.addClickListener         { event -> this.submit()}

        // Add data constructs to row
        inputRow.addComponent(vehicleName)
        inputRow.addComponent(vehicleMake)
        inputRow.addComponent(vehicleModel)
        inputRow.addComponent(vehicleDriver)
        inputRow.addComponent(submitBtn)

        /** Display Row Three: (Display all vehicles in database) --------------------------------------------------- */
        final HorizontalLayout dataDisplayRow = new HorizontalLayout()
        dataDisplayRow.setWidth("100%")
        addComponent(dataDisplayRow)
        dataDisplayRow.addComponent(this.buildVehicleComponent())
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

    /** Private UI component builders ------------------------------------------------------------------------------- */
    private TextField buildNewVehicleName() {
        final TextField vehicleName = new TextField()
        vehicleName.setPlaceholder("Enter a name...")

        vehicleName
    }

    private ComboBox<Make> buildMakeComponent() {
        final List<Make> makes = makeService.listAll()

        final ComboBox<Make> select = new ComboBox<>()
        select.setEmptySelectionAllowed(false)
        select.setPlaceholder("Select a Make")
        select.setItemCaptionGenerator{ item -> item.name }
        select.setItems(makes)

        select
    }

    private ComboBox<Model> buildModelComponent() {
        final List<Model> models = modelService.listAll()

        final ComboBox<Model> select = new ComboBox<>()
        select.setEmptySelectionAllowed(false)
        select.setPlaceholder("Select a Model")
        select.setItemCaptionGenerator{ item -> item.name }
        select.setItems(models)

        select
    }

    private ComboBox<Driver> buildDriverComponent() {
        final List<Driver> drivers = driverService.listAll()

        final ComboBox<Driver> select = new ComboBox<>()
        select.setEmptySelectionAllowed(false)
        select.setPlaceholder("Select a Driver")
        select.setItemCaptionGenerator{ item -> item.name }
        select.setItems(drivers)

        select
    }

    private Grid buildVehicleComponent() {
        final List<Vehicle> vehicles = vehicleService.listAll()
        final Grid grid = new Grid<>()
        grid.setSizeFull()  // ensures grid fills width
        grid.setItems(vehicles)
        grid.addColumn{ item -> item.id }.setCaption("ID")
        grid.addColumn{ item -> item.name }.setCaption("Name")
        grid.addColumn{ item -> item.make.name }.setCaption("Make")
        grid.addColumn{ item -> item.model.name }.setCaption("Model")
        grid.addColumn{ item -> item.driver.name }.setCaption("Name")

        grid
    }

    private Button buildSubmitButton() {
        final Button submitBtn = new Button("Add to Garage")
        submitBtn.setStyleName("friendly")

        submitBtn
    }

    private updateVehicle(final String eventType, final eventValue) {
        if(eventType == "NAME") {
            this.vehicle.name = eventValue
        } else if(eventType == "MAKE") {
            this.vehicle.make = eventValue
        } else if(eventType == "MODEL") {
            this.vehicle.model = eventValue
        } else if(eventType == "DRIVER") {
            this.vehicle.driver = eventValue
        } else {
            println 'SOMETHING WENT WRONG!'
        }
    }

    private submit() {
        vehicleService.save(this.vehicle)
        getUI().getNavigator().navigateTo(VIEW_NAME)
    }
}