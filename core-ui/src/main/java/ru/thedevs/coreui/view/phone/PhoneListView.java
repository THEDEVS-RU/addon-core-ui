package ru.thedevs.coreui.view.phone;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import ru.thedevs.coreui.entity.StyleName;
import ru.thedevs.entity.Phone;
import ru.thedevs.entity.enums.PhoneEventTypeEnum;
import ru.thedevs.entity.enums.PhoneOperatorEnum;
import ru.thedevs.entity.events.PhoneEvent;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.core.SaveContext;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.multitenancy.core.TenantProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;
import java.text.ParseException;


@Route(value = "phone", layout = DefaultMainViewParent.class)
@ViewController(id = "Phone.list")
@ViewDescriptor(path = "phone-list-view.xml")
@LookupComponent("phonesDataGrid")
@DialogMode(width = "64em")
public class PhoneListView extends StandardListView<Phone> {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Messages messages;
    @Autowired
    private Notifications notifications;
    @Autowired
    private TenantProvider tenantProvider;
    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @ViewComponent
    private JmixButton editButton;
    @ViewComponent
    private JmixButton exportButton;
    @ViewComponent
    private JmixButton blockButton;
    @ViewComponent
    private JmixButton removeButton;
    @ViewComponent
    private JmixButton multiCreateBtn;
    @ViewComponent
    private JmixButton updateStatusPhoneBtn;

    @ViewComponent
    private CollectionLoader<Phone> phonesDl;
    @ViewComponent
    private DataGrid<Phone> phonesDataGrid;

    @Subscribe
    public void onInit(InitEvent event) {
        String currentTenantId = tenantProvider.getCurrentUserTenantId();
        if (!currentTenantId.equals(TenantProvider.NO_TENANT)) {
            phonesDl.setQuery("select e from Phone e where e.tenant = :curTenant");
            phonesDl.setParameter("curTenant", currentTenantId);
        }
        phonesDataGrid.addSelectionListener(event1 -> blockBtnNameLogic());
        phonesDl.load();
    }

    @Subscribe(id = "blockButton", subject = "clickListener")
    public void onBlockBtnClick(final ClickEvent<JmixButton> event) throws IOException, InterruptedException {
        for (Phone phone : phonesDataGrid.getSelectedItems()) {
            if (phone != null) {
                if (phone.getActive()) {
                    if (PhoneOperatorEnum.MTS.equals(phone.getOperator())) {
                        createEvent(PhoneEventTypeEnum.BLOCK, phone);
                        showNotify("blockExecSuccess");
                    }
                } else {
                    if (PhoneOperatorEnum.MTS.equals(phone.getOperator())) {
                        createEvent(PhoneEventTypeEnum.UNBLOCK, phone);
                        showNotify("unblockExecSuccess");
                    }
                }
            }
        }
        phonesDl.load();
        blockBtnNameLogic();
    }

    private void blockBtnNameLogic() {
        Phone phone = phonesDataGrid.getSingleSelectedItem();
        if (phone != null) {
            blockButton.setEnabled(true);
            updateStatusPhoneBtn.setEnabled(true);
            if (phone.getActive()) {
                blockButton.setTitle(messages.getMessage("blockBtnBlockCaption"));
            } else {
                blockButton.setTitle(messages.getMessage("blockBtnUnBlockCaption"));
            }
        } else {
            updateStatusPhoneBtn.setEnabled(false);
            blockButton.setEnabled(false);
        }
    }

    @Install(to = "phonesDataGrid", subject = "styleProvider")
    protected String phoneTableStyleProvider(Phone phone, String property) {
        if (property == null) {
            if (!phone.getActive()) {
                return StyleName.STRIKE_THROUGH.getId();
            }
        }
        return null;
    }

    private void showNotify(String key) {
        notifications.create("phoneChangedStatus", messages.getMessage(key))
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe(id = "editButton", subject = "clickListener")
    public void onEditBtnClick(final ClickEvent<JmixButton> event) {
        Phone phone = phonesDataGrid.getSingleSelectedItem();
        if (phone != null) {
            viewNavigators.detailView(this, Phone.class)
                    .editEntity(phone)
                    .withBackwardNavigation(false);
        }
    }

    @Subscribe(id = "removeButton", subject = "clickListener")
    public void onRemoveBtnClick(final ClickEvent<JmixButton> event) {
        for (Phone phone : phonesDataGrid.getSelectedItems()) {
            if (phone.getIsValid()) {
                phone.setTenant(null);
                dataManager.save(phone);
            } else {
                dataManager.remove(phone);
            }
        }
        phonesDl.load();
    }

    @Subscribe("multiCreateBtn")
    public void onMultiCreateBtnClick(final ClickEvent<JmixButton> event) {
//        viewNavigators.view(this, MultiUploadByIccid.class)
//                .navigate();
    }

    @Subscribe(id = "updateStatusPhoneBtn", subject = "clickListener")
    public void click(final ClickEvent<JmixButton> event) throws IOException, ParseException, InterruptedException {
        SaveContext saveContext = new SaveContext().setDiscardSaved(true);
        Phone phone = phonesDataGrid.getSingleSelectedItem();
        if (phone != null) {
            createEvent(PhoneEventTypeEnum.UPDATE, phone);
            saveContext.saving(phone);
            dataManager.save(saveContext);
            phonesDl.load();
        }
    }

    private void createEvent(PhoneEventTypeEnum type, Phone phone){
        PhoneEvent phoneEvent = dataManager.create(PhoneEvent.class);
        phoneEvent.setType(type);
        phoneEvent.setPhone(phone);
        applicationEventPublisher.publishEvent(phoneEvent);
    }
}