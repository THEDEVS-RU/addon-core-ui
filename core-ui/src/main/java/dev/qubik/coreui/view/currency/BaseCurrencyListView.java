package dev.qubik.coreui.view.currency;

import com.vaadin.flow.router.Route;
import dev.qubik.entity.Currency;
import io.jmix.flowui.view.*;


@Route(value = "BaseCurrency", layout = DefaultMainViewParent.class)
@ViewController(id = "BaseCurrency.list")
@ViewDescriptor(path = "base-currency-list-view.xml")
@LookupComponent("baseCurrenciesDataGrid")
@DialogMode(width = "64em")
public class BaseCurrencyListView extends StandardListView<Currency> {
}