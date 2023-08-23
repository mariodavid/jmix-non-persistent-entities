package io.jmix.npe.view.customer;

import io.jmix.core.LoadContext;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.npe.app.OrderClient;
import io.jmix.npe.entity.Customer;
import io.jmix.npe.entity.Order;
import io.jmix.npe.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "customers/:id", layout = MainView.class)
@ViewController("Customer.detail")
@ViewDescriptor("customer-detail-view.xml")
@EditedEntityContainer("customerDc")
public class CustomerDetailView extends StandardDetailView<Customer> {
    @Autowired
    private OrderClient orderClient;
    @ViewComponent
    private DataGrid<Order> ordersDataGrid;
    @Autowired
    private DialogWindows dialogWindows;

    @Install(to = "customerDl", target = Target.DATA_LOADER)
    private Customer customerDlLoadDelegate(final LoadContext<Customer> customerLoadContext) {
        return orderClient.getCustomerWithOrders((Integer) customerLoadContext.getId());
    }

    @Subscribe("ordersDataGrid.read")
    public void onOrdersDataGridRead(final ActionPerformedEvent event) {
        dialogWindows.detail(this, Order.class)
                .editEntity(ordersDataGrid.getSingleSelectedItem())
                .open();
    }

}
