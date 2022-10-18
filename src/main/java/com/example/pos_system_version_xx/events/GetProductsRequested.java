package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.GET.SearchParamType;
import javafx.event.EventType;

public class GetProductsRequested extends CustomEvent {
    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "GetProductsRequested");
    private final String searchParam;
    private final SearchParamType type;

    public GetProductsRequested(String searchParam, SearchParamType type) {
        super(EVENT_TYPE);
        this.searchParam = searchParam;
        this.type = type;
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onGetProductsRequested(searchParam, type);
    }


}
