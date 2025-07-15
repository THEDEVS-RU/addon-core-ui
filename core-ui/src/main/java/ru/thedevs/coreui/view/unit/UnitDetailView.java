package ru.thedevs.coreui.view.unit;

import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.multitenancy.entity.Tenant;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ru.thedevs.entity.AdditionalData;
import ru.thedevs.entity.Token;
import ru.thedevs.entity.Unit;
import ru.thedevs.entity.embeddable.*;

import java.util.Random;

@Route(value = "units/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "Unit.detail")
@ViewDescriptor(path = "unit-detail-view.xml")
@EditedEntityContainer("unitDc")
public class UnitDetailView extends StandardDetailView<Unit> {

    @ViewComponent
    private TypedTextField<Long> identField;
    @ViewComponent
    private TypedTextField<String> nameField;
    @ViewComponent
    private DataContext dataContext;

    // todo basic stub for entity creation
    @Subscribe
    public void onInitEntity(final InitEntityEvent<Unit> event) {

        Random random = new Random();

        identField.setReadOnly(false);
//        deviceSF.setVisible(true);
        nameField.setReadOnly(false);

        Unit created = event.getEntity();
        created.setTenant("default");

        Token token = dataContext.create(Token.class);
        token.setSystem(false);
        token.setName("secretToken");
        token.setToken("TokenString");
        created.setToken(token);

        AdditionalData additionalData = dataContext.create(AdditionalData.class);
        Engine engine = dataContext.create(Engine.class);
        engine.setStatus(true);
        additionalData.setEngine(engine);
        created.setAdditional(additionalData);

        Protocol protocol = dataContext.create(Protocol.class);
        protocol.setId(random.nextLong());
        created.setProtocol(protocol);

        Device device = dataContext.create(Device.class);
        device.setName(RandomStringUtils.randomAlphabetic(7));
        device.setId(random.nextLong());
        device.setType(dataContext.create(Type.class));
        created.setDevice(device);

        Position position = dataContext.create(Position.class);
        position.setLongitude(random.nextDouble(50, 70));
        position.setLatitude(random.nextDouble(25, 40));
        position.setValid(true);

        created.setPosition(position);


//        Tenant tenant = dataContext.create(Tenant.class);
//        tenant.setName("newTenant");
//        tenant.setTenantId("newTenant");

        //todo check access
//        if (!UiUtils.checkIsDealer(currentAuthentication)) {
//            tenantField.setValue(tenantProvider.getCurrentUserTenantId());
//            tenantField.setVisible(false);
//        } else {
//            tenantField.setOptionsList(multitenancyUiSupport.getTenantOptions());
//        }
    }

    @Subscribe(id = "unitDc", target = Target.DATA_CONTAINER)
    public void onUnitDcItemChange(InstanceContainer.ItemChangeEvent<Unit> event) {
        if (event.getItem() != null) {
            initFormFields(event.getItem());
        }
    }

    private void initFormFields(Unit unit) {
        if (unit.getIdent() != null) {
            identField.setReadOnly(true);
        }

//        if (!UiUtils.checkIsDealer(currentAuthentication)) {
//            tenantField.setVisible(false);
//        }
    }

//    @Subscribe("deviceSF")
//    public void onDeviceSFComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<Device>, Device> event) {
//        Device device = event.getValue();
//        if (device != null) {
//            Long protocolId = device.getId();
//            getEditedEntity().getProtocol().setId(protocolId);
//            getEditedEntity().getDevice().setId(protocolId);
//            if (protocolId != null) {
//                String mainMessage = messages.getMessage("protocolId");
//                protocolConnectionInfo.setText(mainMessage + messages.getMessage(String.valueOf(protocolId)));
//            } else {
//                protocolConnectionInfo.setText(messages.getMessage("protocolDisabledByAdmin"));
//            }
//        }
//    }
}