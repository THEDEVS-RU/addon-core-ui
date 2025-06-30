package ru.thedevs.coreui.view.additionaldata;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ru.thedevs.entity.AdditionalData;

@Route(value = "additional-datas/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "AdditionalData.detail")
@ViewDescriptor(path = "additional-data-detail-view.xml")
@EditedEntityContainer("additionalDataDc")
public class AdditionalDataDetailView extends StandardDetailView<AdditionalData> {

    //todo view stub
}