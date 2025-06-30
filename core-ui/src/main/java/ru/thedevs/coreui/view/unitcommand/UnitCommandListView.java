package ru.thedevs.coreui.view.unitcommand;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ru.thedevs.entity.Unit;
import ru.thedevs.entity.UnitCommand;


@Route(value = "unit-commands", layout = DefaultMainViewParent.class)
@ViewController(id = "UnitCommand.list")
@ViewDescriptor(path = "unit-command-list-view.xml")
@LookupComponent("unitCommandsDataGrid")
@DialogMode(width = "64em")
public class UnitCommandListView extends StandardListView<UnitCommand> {
    public void setUnit(Unit unit) {

    }
}