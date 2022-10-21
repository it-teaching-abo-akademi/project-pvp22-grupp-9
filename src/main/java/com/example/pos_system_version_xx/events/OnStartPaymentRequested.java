package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.models.Product;
import javafx.event.EventType;

public class OnStartPaymentRequested extends CustomEvent {

    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "OnStartPaymentRequested");

    private final double param0;
    private final double param1;

    public OnStartPaymentRequested(double param0, double param1) {
        super(EVENT_TYPE);
        this.param0 = param0;
        this.param1 = param1;
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) { handler.onStartPaymentRequested(param0, param1); }
}
