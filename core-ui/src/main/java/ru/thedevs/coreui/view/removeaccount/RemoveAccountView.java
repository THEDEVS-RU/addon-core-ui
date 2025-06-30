package ru.thedevs.coreui.view.removeaccount;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;

@Route(value = "remove-account-view", layout = DefaultMainViewParent.class)
@ViewController(id = "RemoveAccountView")
@ViewDescriptor(path = "remove-account-view.xml")
public class RemoveAccountView extends StandardView {

    //todo decide if this should be here or in main application

    @Subscribe(id = "removeAcc", subject = "clickListener")
    public void onRemoveAccClick(final ClickEvent<JmixButton> event) {
        // todo implement acc removal logic
    }
}