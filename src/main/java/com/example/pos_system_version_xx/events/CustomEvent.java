package com.example.pos_system_version_xx.events;

import javafx.event.Event;
import javafx.event.EventType;

public abstract class CustomEvent extends Event {

    public static final EventType<CustomEvent> CUSTOM_EVENT_TYPE = new EventType(ANY);

    public CustomEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public abstract void invokeHandler(SaleEventHandler handler);

}