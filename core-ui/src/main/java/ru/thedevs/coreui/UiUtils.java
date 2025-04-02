package ru.thedevs.coreui;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class UiUtils {
  public static final String TENANT_USERNAME_SEPARATOR = "|";

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
