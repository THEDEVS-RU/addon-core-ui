package dev.qubik.coreui.view.user;

import com.vaadin.flow.router.Route;
import dev.qubik.entity.BaseUser;
import io.jmix.flowui.view.*;

@Route(value = "BaseUser", layout = DefaultMainViewParent.class)
@ViewController("BaseUser.list")
@ViewDescriptor("base-user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class BaseUserListView<T extends BaseUser> extends StandardListView<T> {
}