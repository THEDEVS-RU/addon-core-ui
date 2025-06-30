package ru.thedevs.coreui;


import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.kit.component.button.JmixButton;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Date;

@Component
public class UiUtils {

  private final UiComponents uiComponents;

  public static final String TENANT_USERNAME_SEPARATOR = "|";

  public UiUtils(UiComponents uiComponents) {
    this.uiComponents = uiComponents;
  }

  public static BigDecimal getValueBySurchange(BigDecimal value, BigDecimal surchange) {
    if (value != null && surchange != null && surchange.compareTo(BigDecimal.ZERO) != 0) {
      if (value.compareTo(BigDecimal.ZERO) != 0) {
        BigDecimal surcharge = surchange.setScale(8, RoundingMode.UP);
        BigDecimal onePercent = value.setScale(8, RoundingMode.DOWN).divide(new BigDecimal("100"), RoundingMode.UP);
        return onePercent.multiply(surcharge).add(value).setScale(2, RoundingMode.UP);
      } else {
        return BigDecimal.ZERO;
      }
    }
    return value;
  }

  public HorizontalLayout getDefaultHbWithIconAndCaption(String iconName, String iconStyleName, String caption, String labelStyleName) {
    HorizontalLayout defaultHb = getDefaultHbWithIcon(iconName, iconStyleName);

    Span label = uiComponents.create(Span.class);
    label.setText(caption);
    label.addClassName(labelStyleName);
    defaultHb.add(label);
    return defaultHb;
  }

  public HorizontalLayout getDefaultHbWithIcon(String iconName, String iconStyleName) {
    HorizontalLayout defaultHb = uiComponents.create(HorizontalLayout.class);
    defaultHb.add(getDefaultIconButton(iconName, iconStyleName));
    return defaultHb;
  }

  public JmixButton getDefaultIconButton(String iconName, String styleName) {
    JmixButton button = uiComponents.create(JmixButton.class);
    button.setThemeName(styleName == null ? "icon-only" : "icon-only" + " " + styleName);
    button.setEnabled(false);
    button.setIcon(new Icon(iconName));
    return button;
  }

  public Long getDifferenceBetweenNowAndLastPositionDate(Long lastPosition) {
    if (lastPosition != null) {
      // Определите порог, чтобы проверить, в каком формате представлен timestamp
      long threshold = Instant.now().getEpochSecond();
      // Если lastPosition меньше порога, то предположим, что он в секундах
      if (lastPosition < threshold) {
        lastPosition *= 1000;
      }
      Date now = new Date();
      long difference = now.getTime() - lastPosition;
      // Вычисление разницы в минутах
      return difference / (60 * 1000);
    }
    return null;
  }

//  public static boolean isGrantRole(CurrentAuthentication currentAuthentication) {
//    return currentAuthentication.getAuthentication().getAuthorities().stream()
//         .anyMatch(grantedAuthority ->
//              grantedAuthority.getAuthority().equals(DealerRole.CODE) || grantedAuthority.getAuthority().equals(FullAccessRole.CODE));
//  }
//
//  public static boolean isAdminRole(CurrentAuthentication currentAuthentication) {
//    return isGrantRole(currentAuthentication) || currentAuthentication.getAuthentication().getAuthorities().stream()
//         .anyMatch(grantedAuthority ->
//              grantedAuthority.getAuthority().equals(AdminRole.CODE) || grantedAuthority.getAuthority().equals(FullUserRole.CODE));
//  }
//
//  public static boolean isUserRole(CurrentAuthentication currentAuthentication) {
//    return currentAuthentication.getAuthentication().getAuthorities().stream()
//         .anyMatch(grantedAuthority ->
//              grantedAuthority.getAuthority().equals(UserRole.CODE));
//  }
}
