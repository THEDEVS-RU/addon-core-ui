package ru.thedevs.coreui.view.additionaldata;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ru.thedevs.entity.AdditionalData;
import ru.thedevs.entity.Unit;


@Route(value = "additional-datas", layout = DefaultMainViewParent.class)
@ViewController(id = "AdditionalData.list")
@ViewDescriptor(path = "additional-data-list-view.xml")
@LookupComponent("additionalDatasDataGrid")
@DialogMode(width = "64em")
public class AdditionalDataListView extends StandardListView<AdditionalData> {

    //todo view stub

    public void setItem(Unit unit) {

    }
}