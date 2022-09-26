package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.models.Product;
import javafx.event.EventType;

public class ProductRemoveRequested extends CustomEvent {
    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "ProductRemoveRequested");

    private final Product param;

    public ProductRemoveRequested(Product param) {
        super(EVENT_TYPE);
        this.param = param;
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onProductRemoveRequested(param);
    }
}
