package ru.thedevs.coreui.view.phone;


import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.DefaultMainViewParent;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "MultiAddPhoneFromIccid", layout = DefaultMainViewParent.class)
@ViewController(id = "coreui_Multiaddphonefromiccid")
@ViewDescriptor(path = "MultiAddPhoneFromIccid.xml")
public class Multiaddphonefromiccid extends StandardView {
}