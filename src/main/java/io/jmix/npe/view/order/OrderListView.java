package io.jmix.npe.view.order;

import com.vaadin.flow.router.RouteParameters;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.npe.app.OrderClient;
import io.jmix.npe.entity.Order;
import io.jmix.npe.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Route(value = "orders", layout = MainView.class)
@ViewController("Order_.list")
@ViewDescriptor("order-list-view.xml")
@LookupComponent("ordersDataGrid")
@DialogMode(width = "50em")
public class OrderListView extends StandardListView<Order> {
    @Autowired
    private OrderClient orderClient;
    @ViewComponent
    private DataGrid<Order> ordersDataGrid;

    @Install(to = "ordersDl", target = Target.DATA_LOADER)
    protected List<Order> ordersDlLoadDelegate(LoadContext<Order> loadContext) {
        return orderClient.getOrders();
    }

    @Install(to = "ordersDataGrid.read", subject = "routeParametersProvider")
    private RouteParameters ordersDataGridReadRouteParametersProvider() {
        Integer customerId = ordersDataGrid.getSingleSelectedItem().getId();
        return new RouteParameters(Collections.singletonMap("id", customerId.toString()));
    }

}
