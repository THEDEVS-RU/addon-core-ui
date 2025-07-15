package ru.thedevs.coreui.view.monitoring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.context.event.EventListener;
import ru.thedevs.coreui.view.fragment.route_list_fragment.RouteListFragment;
import ru.thedevs.coreui.view.fragment.unit_list_fragment.UnitListFragment;
import ru.thedevs.coreui.view.fragment.map_browser_fragment.MapBrowserFragment;
import ru.thedevs.entity.Unit;
import ru.thedevs.entity.events.RouteUpdateEvent;

import java.util.List;

@Route(value = "monitoring-view", layout = DefaultMainViewParent.class)
@ViewController(id = "MonitoringView")
@ViewDescriptor(path = "monitoring-view.xml")
public class MonitoringView extends StandardView {


    @ViewComponent
    private MapBrowserFragment mapBrowserFragment;
    @ViewComponent
    private UnitListFragment unitListFragment;
    @ViewComponent
    private RouteListFragment routeListFragment;
    @ViewComponent
    private VerticalLayout tableBox;

    public MapBrowserFragment getMapBrowserFragment() {
        return mapBrowserFragment;
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        List<Unit> initialUnits = unitListFragment.getInitialUnits();
        mapBrowserFragment.setNewMarkers(initialUnits);
    }

    public void showRouteBrowse(Unit unit) {
        mapBrowserFragment.setNewMarkers(null);
        Component unitBrowse = getContent().getComponent("unitListFragment");
        unitBrowse.setVisible(false);
        routeListFragment.setUnit(unit);
        routeListFragment.setVisible(true);
    }

    public void showUnitsBrowse() {
        mapBrowserFragment.setRoutes(null);
        unitListFragment.setVisible(true);
        routeListFragment.setVisible(false);
    }

    @EventListener
    private void routeChanged(RouteUpdateEvent event) {
        //TODO
    }
}