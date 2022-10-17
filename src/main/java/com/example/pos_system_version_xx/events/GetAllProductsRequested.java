package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.models.Product;
import javafx.event.EventType;

public class GetAllProductsRequested extends CustomEvent {
    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "GetAllProductsRequested");

    public GetAllProductsRequested() {
        super(EVENT_TYPE);
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onGetAllProductsRequested();
    }


}
