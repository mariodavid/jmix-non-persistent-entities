package io.jmix.npe.view.customer;

import com.vaadin.flow.router.RouteParameters;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.npe.app.OrderClient;
import io.jmix.npe.entity.Customer;
import io.jmix.npe.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Route(value = "customers", layout = MainView.class)
@ViewController("Customer.list")
@ViewDescriptor("customer-list-view.xml")
@LookupComponent("customersDataGrid")
@DialogMode(width = "50em")
public class CustomerListView extends StandardListView<Customer> {

    @Autowired
    private OrderClient orderClient;
    @ViewComponent
    private DataGrid<Customer> customersDataGrid;

    @Install(to = "customersDl", target = Target.DATA_LOADER)
    protected List<Customer> customersDlLoadDelegate(LoadContext<Customer> loadContext) {
        return orderClient.getCustomersFromOrders();
    }

    @Install(to = "customersDataGrid.read", subject = "routeParametersProvider")
    private RouteParameters customersDataGridReadRouteParametersProvider() {
        Integer customerId = customersDataGrid.getSingleSelectedItem().getId();
        return new RouteParameters(Collections.singletonMap("id", customerId.toString()));
    }
}
