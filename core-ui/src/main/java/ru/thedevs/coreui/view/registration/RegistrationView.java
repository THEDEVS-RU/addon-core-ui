package ru.thedevs.coreui.view.registration;


import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.router.Route;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.textfield.JmixPasswordField;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.validation.ValidationErrors;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.thedevs.entities.BaseUser;

import java.util.Objects;

@Route(value = "registration-view", layout = DefaultMainViewParent.class)
@ViewController(id = "RegistrationView")
@ViewDescriptor(path = "registration-view.xml")
public class RegistrationView extends StandardView {

    @ViewComponent
    private TypedTextField<String> loginField;
    @Autowired
    private ViewValidation viewValidation;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @ViewComponent
    private H4 loginExistsMessage;
    @ViewComponent
    private H4 passwordNotMatchMessage;
    @ViewComponent
    private JmixPasswordField retryPasswordField;
    @ViewComponent
    private JmixPasswordField passwordField;
    @ViewComponent
    private TypedTextField<String> emailField;
    @ViewComponent
    private JmixButton registerBtn;
    @Autowired
    private ViewNavigators viewNavigators;

    @Subscribe("loginField")
    public void onLoginFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<TypedTextField<String>, String> event) {
        validateComponent(loginField);

        String login = event.getValue();
        String loginWithTenant = "%s|%s".formatted(login, login);
        unconstrainedDataManager.load(BaseUser.class)
                .query("select u from User u where u.username = :login")
                .parameter("login", loginWithTenant)
                .optional()
                .ifPresentOrElse(user -> loginExistsMessage.setVisible(true), () -> loginExistsMessage.setVisible(false));

    }

    @Subscribe("retryPasswordField")
    public void onRetryPasswordFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixPasswordField, ?> event) {
        passwordNotMatchMessage.setVisible(!Objects.equals(event.getValue(), passwordField.getValue()));

    }

    @Subscribe("passwordField")
    public void onPasswordFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixPasswordField, ?> event) {
        passwordNotMatchMessage.setVisible(!Objects.equals(event.getValue(), retryPasswordField.getValue()));
    }

    @Subscribe("emailField")
    public void onEmailFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<TypedTextField<String>, String> event) {
        validateComponent(emailField);
    }

    @Subscribe(id = "registerBtn", subject = "clickListener")
    public void onRegisterBtnClick(final ClickEvent<JmixButton> event) {
//        todo decide if needed
//        iRegistrationService.registerUser(loginField.getValue(), loginField.getValue(), passwordField.getValue(), emailField.getValue());
//        todo decide if this should be in this module or in app
//        viewNavigators.view(this, JmixLoginForm.class)
//                .navigate();
    }

    private void validateComponent(Component toValidate) {
        ValidationErrors validationErrors = viewValidation.validateUiComponent(toValidate);
        if (!validationErrors.isEmpty()) {
            viewValidation.showValidationErrors(validationErrors);
            viewValidation.focusProblemComponent(validationErrors);
        } else {
            registerBtn.setEnabled(true);
        }
    }


}