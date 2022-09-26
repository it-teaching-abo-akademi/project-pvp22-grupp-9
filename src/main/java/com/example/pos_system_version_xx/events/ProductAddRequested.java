package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.models.Barcode;
import javafx.event.EventType;

public class ProductAddRequested extends CustomEvent {

    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "ProductAddRequested");

    private final Barcode param;

    public ProductAddRequested(Barcode param) {
        super(EVENT_TYPE);
        this.param = param;
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onProductAddRequested(param);
    }

}
