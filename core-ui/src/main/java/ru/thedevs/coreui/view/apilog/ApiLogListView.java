package ru.thedevs.coreui.view.apilog;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ru.thedevs.entity.ApiLog;


@Route(value = "api-logs", layout = DefaultMainViewParent.class)
@ViewController(id = "ApiLog.list")
@ViewDescriptor(path = "api-log-list-view.xml")
@LookupComponent("apiLogsDataGrid")
@DialogMode(width = "64em")
public class ApiLogListView extends StandardListView<ApiLog> {
}