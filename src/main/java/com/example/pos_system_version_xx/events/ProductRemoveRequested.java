package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.event.EventType;

public class ProductRemoveRequested extends CustomEvent {
    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "ProductRemoveRequested");

    private final PRODUCT_TEST_CLASS param;

    public ProductRemoveRequested(PRODUCT_TEST_CLASS param) {
        super(EVENT_TYPE);
        this.param = param;
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onProductRemoveRequested(param);
    }
}
