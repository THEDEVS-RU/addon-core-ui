package ru.thedevs.coreui.view.phone;

import com.vaadin.flow.router.Route;
import ru.thedevs.entity.Phone;
import io.jmix.flowui.view.*;

@Route(value = "phones/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "Phone.detail")
@ViewDescriptor(path = "phone-detail-view.xml")
@EditedEntityContainer("phoneDc")
public class PhoneDetailView extends StandardDetailView<Phone> {
}