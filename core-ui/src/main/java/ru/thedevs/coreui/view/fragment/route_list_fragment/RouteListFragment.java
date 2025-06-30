package ru.thedevs.coreui.view.fragment.route_list_fragment;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.model.InstanceLoader;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import ru.thedevs.coreui.view.monitoring.MonitoringView;
import ru.thedevs.entity.Unit;

@FragmentDescriptor("route-list-fragment.xml")
public class RouteListFragment extends Fragment<VerticalLayout> {

    @ViewComponent
    private InstanceLoader<Unit> unitDl;
    @ViewComponent
    private InstanceContainer<Unit> unitDc;

    public void setUnit(Unit unit) {
        unitDl.setParameter("curId", unit.getId());
        unitDl.load();
    }

    @Subscribe(id = "backButton", subject = "clickListener")
    public void onBackButtonClick(final ClickEvent<JmixButton> event) {
        if (getParentController() instanceof MonitoringView parent) {
            parent.showUserBrowse();
        }
    }
}