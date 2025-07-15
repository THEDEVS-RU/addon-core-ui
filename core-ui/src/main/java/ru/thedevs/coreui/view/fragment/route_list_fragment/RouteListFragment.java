package ru.thedevs.coreui.view.fragment.route_list_fragment;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.model.InstanceLoader;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.View;
import io.jmix.flowui.view.ViewComponent;
import org.springframework.beans.factory.annotation.Autowired;
import ru.thedevs.coreui.view.fragment.map_browser_fragment.MapBrowserFragment;
import ru.thedevs.coreui.view.monitoring.MonitoringView;
import ru.thedevs.entity.Route;
import ru.thedevs.entity.Unit;
import ru.thedevs.utils.FlespiServiceBean;

import java.util.*;

@FragmentDescriptor("route-list-fragment.xml")
public class RouteListFragment extends Fragment<VerticalLayout> {

    @ViewComponent
    private InstanceLoader<Unit> unitDl;
    @ViewComponent
    private InstanceContainer<Unit> unitDc;

    //todo this is stub
    @Autowired
    private FlespiServiceBean flespiServiceBean;

    private MapBrowserFragment mapBrowserFragment;
    @ViewComponent
    private DataGrid<Route> routesTable;
    @ViewComponent
    private TypedDateTimePicker<Date> beginDF;
    @ViewComponent
    private TypedDateTimePicker<Date> endDF;
    @ViewComponent
    private CollectionPropertyContainer<Route> routesDc;

    public void setUnit(Unit unit) {
        unitDl.setParameter("curId", unit.getId());
        unitDl.load();
        onBackButtonClick(null);
    }

    @Subscribe(id = "searchBtn", subject = "clickListener")
    public void onSearchBtnClick(final ClickEvent<JmixButton> event) {
        List<Route> routes = null;
        if (beginDF.getValue() == null && endDF.getValue() == null) {
            routes = flespiServiceBean.getLatestIntervals(1662580, unitDc.getItem().getDevice().getId(), null);
        } else {
            //todo decide and fix Date and OffsetDateTime
            Long begin = beginDF.getValue() == null ? null : beginDF.getValue().toInstant(null).toEpochMilli();
            Long end = endDF.getValue() == null ? null : endDF.getValue().toInstant(null).toEpochMilli();
            routes = flespiServiceBean.getLatestIntervals(1662580, unitDc.getItem().getDevice().getId(), begin, end);
        }
        if (routes != null) {
            routes.forEach(route -> {
                route.setUnit(unitDc.getItem());
                route.setId(UUID.randomUUID());
            });
            routesDc.setItems(routes.stream().sorted(Comparator.comparing(Route::getBeginTime).reversed()).toList());
        }
    }

    @Subscribe(id = "backButton", subject = "clickListener")
    public void onBackButtonClick(final ClickEvent<JmixButton> event) {
        if (getParentController() instanceof MonitoringView parent) {
            parent.showUnitsBrowse();
        }
    }

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostInit(final View.InitEvent event) {

    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        routesTable.addSelectionListener(selectionEvent -> {
            sendRouteToMap(routesTable.getSelectedItems());
        });

        mapBrowserFragment = ((MonitoringView) getParentController()).getMapBrowserFragment();
    }

    private void sendRouteToMap(Set<Route> routes) {
        if (routes != null && !routes.isEmpty()) {
            mapBrowserFragment.setRoutes(routes);
        }
    }


}