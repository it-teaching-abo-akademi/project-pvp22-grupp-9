package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.models.Barcode;
import com.example.pos_system_version_xx.models.Product;
import javafx.event.EventHandler;

public abstract class SaleEventHandler implements EventHandler<CustomEvent> {

    public abstract void onProductAddRequested(String param0);

    public abstract void onProductRemoveRequested(Product param0);

    public abstract void onProductDiscountRequested(Product param0, double param1);

    public abstract void onStartPaymentRequested();

    public abstract void onOpenCashboxRequested();

    @Override
    public void handle(CustomEvent event) {
        event.invokeHandler(this);
    }

}
