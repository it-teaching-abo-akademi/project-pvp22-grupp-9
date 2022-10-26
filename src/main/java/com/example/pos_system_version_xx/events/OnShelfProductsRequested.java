package com.example.pos_system_version_xx.events;

import javafx.event.EventType;

public class OnShelfProductsRequested extends CustomEvent {

    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "OnShelfProductsRequested");

    public OnShelfProductsRequested() {
        super(EVENT_TYPE);
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onShelfProductsRequested();
    }

}
