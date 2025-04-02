package ru.thedevs.coreui.view.coststatistics.coststatisticpart;

import com.vaadin.flow.router.Route;
import ru.thedevs.entity.CostStatisticPart;
import io.jmix.flowui.view.*;


@Route(value = "costStatisticParts", layout = DefaultMainViewParent.class)
@ViewController(id = "CostStatisticPart.list")
@ViewDescriptor(path = "cost-statistic-part-list-view.xml")
@LookupComponent("costStatisticPartsDataGrid")
@DialogMode(width = "64em")
public class CostStatisticPartListView extends StandardListView<CostStatisticPart> {
}