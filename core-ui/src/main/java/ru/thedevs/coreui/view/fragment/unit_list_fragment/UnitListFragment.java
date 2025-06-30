package ru.thedevs.coreui.view.fragment.unit_list_fragment;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Supply;
import io.jmix.flowui.view.ViewComponent;
import org.springframework.beans.factory.annotation.Autowired;
import ru.thedevs.coreui.UiUtils;
import ru.thedevs.coreui.view.additionaldata.AdditionalDataListView;
import ru.thedevs.coreui.view.monitoring.MonitoringView;
import ru.thedevs.coreui.view.unitcommand.UnitCommandListView;
import ru.thedevs.entity.Unit;

import java.io.IOException;

@FragmentDescriptor("unit-list-fragment.xml")
public class UnitListFragment extends Fragment<VerticalLayout> {

    @ViewComponent
    private DataGrid<Unit> unitsDataGrid;
    @ViewComponent
    private JmixButton removeButton;
    @ViewComponent
    private JmixButton indicatorButton;
    @ViewComponent
    private JmixButton routesButton;
    @ViewComponent
    private JmixButton commandsButton;
    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private CollectionLoader<Unit> unitsDl;
    @ViewComponent
    private CollectionContainer<Unit> unitsDc;
    @Autowired
    private UiUtils uiUtils;

    @Subscribe
    public void onReady(final ReadyEvent event) {

        unitsDl.load();

        unitsDataGrid.addSelectionListener(event1 -> {
            Unit unit = unitsDataGrid.getSingleSelectedItem();
            removeButton.setEnabled(unit != null);
            indicatorButton.setEnabled(unit != null);
            routesButton.setEnabled(unit != null);
            commandsButton.setEnabled(unit != null);
        });
    }

    @Subscribe(id = "commandsButton", subject = "clickListener")
    public void onCommandsButtonClick(final ClickEvent<JmixButton> event) {
        Unit selected = unitsDc.getItem();
        Unit unit = unitsDataGrid.getSingleSelectedItem();
        if (unit != null) {
            if (getParentController() instanceof MonitoringView parent) {
                dialogWindows.view(parent, UnitCommandListView.class)
                        .withViewConfigurer(view ->
                                view.setUnit(unit))
                        .open();
            }
        }
    }

    @Subscribe(id = "indicatorButton", subject = "clickListener")
    public void onIndicatorButtonClick(final ClickEvent<JmixButton> event) {
        Unit unit = unitsDataGrid.getSingleSelectedItem();
        if (unit != null) {
            if (getParentController() instanceof MonitoringView parent) {
                dialogWindows.view(parent, AdditionalDataListView.class)
                        .withViewConfigurer(view ->
                                view.setItem(unit))
                        .open();
            }
        }
    }

    @Subscribe(id = "routesButton", subject = "clickListener")
    public void onRoutesButtonClick(final ClickEvent<JmixButton> event) throws IOException {
        Unit unit = unitsDataGrid.getSingleSelectedItem();
        if (unit != null) {
            if (getParentController() instanceof MonitoringView parent) {
                parent.showRouteBrowse(unit);
            }
        }
    }

    @Supply(to = "unitsDataGrid.lastPing", subject = "renderer")
    private Renderer<Unit> unitsDataGridLastPingRenderer() {
        return new ComponentRenderer<>(unit -> {
            Long time = unit.getPosition().getTimestamp();
            if (time != null) {
                Long timeBefore = uiUtils.getDifferenceBetweenNowAndLastPositionDate(time);
                if (timeBefore > 10) {
                    return uiUtils.getDefaultHbWithIconAndCaption("font-icon:UNLINK",
                            "red",
                            timeBefore + " мин. назад",
                            "red");
                } else {
                    return uiUtils.getDefaultHbWithIconAndCaption("font-icon:LINK",
                            "green",
                            timeBefore + " мин. назад",
                            "green");
                }
            }
            return uiComponents.create(HorizontalLayout.class);
        });
    }


    @Subscribe("unitsDataGrid.remove")
    public void onUnitsDataGridRemove(final ActionPerformedEvent event) {
        Unit unit = unitsDataGrid.getSingleSelectedItem();

        //todo add entity removal logic here when flespi implemented

        //        try {
//            HttpStatus status = flespiServiceBean.deleteDevice(unit.getDevice().getId());
//            if (!HttpStatus.OK.equals(status)) {
//                throw new RuntimeException(messages.getMessage("flespi.error.delete.device"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}