package ru.thedevs.coreui.view.unitcommand;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ru.thedevs.entity.UnitCommand;

@Route(value = "unit-commands/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "UnitCommand.detail")
@ViewDescriptor(path = "unit-command-detail-view.xml")
@EditedEntityContainer("unitCommandDc")
public class UnitCommandDetailView extends StandardDetailView<UnitCommand> {
}