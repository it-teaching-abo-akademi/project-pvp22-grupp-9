package com.example.pos_system_version_xx.events;

import com.example.pos_system_version_xx.GET.SearchParamType;
import com.example.pos_system_version_xx.models.PRODUCT_TEST_CLASS;
import com.example.pos_system_version_xx.models.Product;
import javafx.event.EventHandler;

public abstract class SaleEventHandler implements EventHandler<CustomEvent> {

    public abstract void onProductScanRequested(String param0);

    public abstract void onProductAddRequested(PRODUCT_TEST_CLASS param0);

    public abstract void onProductRemoveRequested(PRODUCT_TEST_CLASS param0);

    public abstract void onProductDiscountRequested(Product param0, double param1);

    public abstract void onGetProductsRequested(String keyword, SearchParamType type);

    public abstract void onStartPaymentRequested();

    public abstract void onOpenCashboxRequested();

    public abstract void onFindKeywordRequested(String param);

    @Override
    public void handle(CustomEvent event) {
        event.invokeHandler(this);
    }

}
