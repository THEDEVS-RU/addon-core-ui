package ru.thedevs.coreui.view.setting;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ru.thedevs.entity.Setting;

@Route(value = "settings/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "Setting.detail")
@ViewDescriptor(path = "setting-detail-view.xml")
@EditedEntityContainer("settingDc")
public class SettingDetailView extends StandardDetailView<Setting> {

    // todo view stub
}