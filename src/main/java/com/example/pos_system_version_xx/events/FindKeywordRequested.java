package com.example.pos_system_version_xx.events;

import javafx.event.EventType;

public class FindKeywordRequested extends CustomEvent {
    public static final EventType<CustomEvent> EVENT_TYPE = new EventType(CUSTOM_EVENT_TYPE, "FindKeywordRequested");

    private final String param;

    public FindKeywordRequested(String param) {
        super(EVENT_TYPE);
        this.param = param;
    }

    @Override
    public void invokeHandler(SaleEventHandler handler) {
        handler.onFindKeywordRequested(param);
    }
}
