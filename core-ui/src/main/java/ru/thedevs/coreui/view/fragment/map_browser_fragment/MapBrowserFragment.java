package ru.thedevs.coreui.view.fragment.map_browser_fragment;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.View;
import io.jmix.flowui.view.ViewComponent;

@FragmentDescriptor("map-browser-fragment.xml")
public class MapBrowserFragment extends Fragment<VerticalLayout> {

    @ViewComponent
    private VerticalLayout yandexMapContainer;

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostInit(final View.InitEvent event) {

        //todo fix once map addon is published
//        MapState mapState = new MapState();
//        mapState.setZoom(10);
//        Coordinates center = dataManager.create(Coordinates.class);
//        center.setLatitude(37.588144);
//        center.setLongitude(55.733842);
//        mapState.setCenter(center);
//        mapState.setLink(mapBuilder.getYandexMapLink());

//        YandexMap map = new YandexMap();
//
//        map.setWidthFull();
//        map.setHeightFull();
//        map.setVisible(true);

//        map.getElement().setPropertyBean("data", mapState);

//        yandexMapContainer.add(map);
    }
}