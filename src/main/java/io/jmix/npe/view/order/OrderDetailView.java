package io.jmix.npe.view.order;

import com.vaadin.flow.router.RouteParameters;
import io.jmix.core.LoadContext;
import io.jmix.npe.app.OrderClient;
import io.jmix.npe.entity.Customer;
import io.jmix.npe.entity.Order;
import io.jmix.npe.view.main.MainView;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "orders/:id", layout = MainView.class)
@ViewController("Order_.detail")
@ViewDescriptor("order-detail-view.xml")
@EditedEntityContainer("orderDc")
public class OrderDetailView extends StandardDetailView<Order> {

    @Autowired
    private OrderClient orderClient;

//    @Install(to = "orderDl", target = Target.DATA_LOADER)
//    private Order orderDlLoadDelegate(final LoadContext<Order> orderLoadContext) {
//        return orderClient.getOrder((Integer) orderLoadContext.getId());
//    }
}
