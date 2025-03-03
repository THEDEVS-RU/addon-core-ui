package dev.qubik.coreui.view.currency;

import com.vaadin.flow.router.Route;
import dev.qubik.entity.Currency;
import io.jmix.flowui.view.*;

@Route(value = "BaseCurrency/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "BaseCurrency.detail")
@ViewDescriptor(path = "base-currency-detail-view.xml")
@EditedEntityContainer("baseCurrencyDc")
public class BaseCurrencyDetailView extends StandardDetailView<Currency> {
}