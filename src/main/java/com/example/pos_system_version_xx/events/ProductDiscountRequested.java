package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.event.EventType;

public class ProductDiscountRequested extends CustomEvent {
    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "ProductDiscountRequested");

    private final PRODUCT_TEST_CLASS param0;
    private final double param1;

    public ProductDiscountRequested(PRODUCT_TEST_CLASS param0, double param1) {
        super(EVENT_TYPE);
        this.param0 = param0;
        this.param1 = param1;
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onProductDiscountRequested(param0, param1);
    }
}
