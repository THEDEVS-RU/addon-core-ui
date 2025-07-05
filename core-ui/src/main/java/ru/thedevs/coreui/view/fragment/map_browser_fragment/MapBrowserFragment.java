package ru.thedevs.coreui.view.fragment.map_browser_fragment;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.core.DataManager;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.View;
import io.jmix.flowui.view.ViewComponent;
import org.springframework.beans.factory.annotation.Autowired;
import ru.thedevs.entity.Route;
import ru.thedevs.entity.Unit;
import ru.thedevs.integration.yandex.map.MapUtils;
import ru.thedevs.integration.yandex.map.component.YandexMap;
import ru.thedevs.integration.yandex.map.entity.MapState;
import ru.thedevs.utils.interfaces.IYandexMapBuilder;

import java.util.List;
import java.util.Set;

@FragmentDescriptor("map-browser-fragment.xml")
public class MapBrowserFragment extends Fragment<VerticalLayout> {

    @ViewComponent
    private VerticalLayout yandexMapContainer;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private IYandexMapBuilder mapBuilder;
    YandexMap map = null;
    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostInit(final View.InitEvent event) {

        MapState mapState = new MapState();
        mapState.setZoom(MapUtils.DEFAULT_ZOOM);
        mapState.setCenter(MapUtils.getCenter(mapState));
        mapState.setLink(mapBuilder.getYandexMapLink());

        map = new YandexMap(mapState);
        yandexMapContainer.add(map);
    }

    public void setNewMarkers(List<Unit> units) {
        MapState mapState = new MapState();
        MapUtils.convertUnitsToMapStateInfo(mapState, units);
        map.setMapState(mapState);
    }

    public void setRoutes(Set<Route> routes) {
        MapState mapState = new MapState();
        mapState.setPolylines(null);
        mapState.setPoints(null);
        if (routes != null)
            for (Route route : routes) {
                MapUtils.convertRouteToMapStateInfo(mapState, route);
            }
        map.setMapState(mapState);
    }
}