package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.models.Product;
import javafx.event.EventType;

public class OpenCashboxRequested extends CustomEvent {

    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "OpenCashboxRequested");

    public OpenCashboxRequested(Product param0, double param1) {
        super(EVENT_TYPE);
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onOpenCashboxRequested();
    }

}
