package ru.thedevs.coreui.view.coststatistics;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.component.button.JmixButton;
import ru.thedevs.entity.CostStatistics;
import io.jmix.flowui.view.*;


@Route(value = "costStatisticses", layout = DefaultMainViewParent.class)
@ViewController(id = "CostStatistics.list")
@ViewDescriptor(path = "cost-statistics-list-view.xml")
@LookupComponent("costStatisticsesDataGrid")
@DialogMode(width = "64em")
public class CostStatisticsListView extends StandardListView<CostStatistics> {

    @Subscribe(id = "uploadByExcel", subject = "clickListener")
    public void onCliclUploadByExcelBtn(final ClickEvent<JmixButton> event) {

    }
}