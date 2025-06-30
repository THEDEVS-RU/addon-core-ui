package ru.thedevs.coreui.view.setting;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ru.thedevs.entity.Setting;


@Route(value = "settings", layout = DefaultMainViewParent.class)
@ViewController(id = "Setting.list")
@ViewDescriptor(path = "setting-list-view.xml")
@LookupComponent("settingsDataGrid")
@DialogMode(width = "64em")
public class SettingListView extends StandardListView<Setting> {

    // todo view stub
}