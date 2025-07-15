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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@FragmentDescriptor("map-browser-fragment.xml")
public class MapBrowserFragment extends Fragment<VerticalLayout> {

    @ViewComponent
    private VerticalLayout yandexMapContainer;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private IYandexMapBuilder mapBuilder;

    private YandexMap map = null;
    private MapState state = new MapState();

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostInit(final View.InitEvent event) {

        state = new MapState();
        state.setZoom(MapUtils.DEFAULT_ZOOM);
        state.setCenter(MapUtils.getCenter(state));
        state.setLink(mapBuilder.getYandexMapLink());

        map = new YandexMap(state);
        yandexMapContainer.add(map);
    }

    public void setNewMarkers(List<Unit> units) {
        MapUtils.convertUnitsToMapStateInfo(state, units);
        if (units != null) {
            state.setZoom(state.getZoom() - units.size());
        }
        map.setMapState(state);

    }

    public void setRoutes(Set<Route> routes) {
        if (routes != null) {
            for (Route route : routes) {
                MapUtils.convertRouteToMapStateInfo(state, route);
            }

        } else {
            state.setPolylines(new ArrayList<>());
        }

        map.setMapState(state);
    }
}