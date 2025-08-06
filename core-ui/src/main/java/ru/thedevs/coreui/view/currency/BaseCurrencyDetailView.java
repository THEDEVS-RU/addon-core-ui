package ru.thedevs.coreui.view.currency;

import com.vaadin.flow.router.Route;
import ru.thedevs.entities.Currency;
import io.jmix.flowui.view.*;

@Route(value = "BaseCurrency/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "BaseCurrency.detail")
@ViewDescriptor(path = "base-currency-detail-view.xml")
@EditedEntityContainer("baseCurrencyDc")
public class BaseCurrencyDetailView extends StandardDetailView<Currency> {
}