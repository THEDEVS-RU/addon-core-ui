package ru.thedevs.coreui.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import javax.annotation.Nullable;


public enum StyleName implements EnumClass<String> {

  STRIKE_THROUGH("strikethrough"),
  RED("red");

  private String id;

  StyleName(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Nullable
  public static StyleName fromId(String id) {
    for (StyleName at : StyleName.values()) {
      if (at.getId().equals(id)) {
        return at;
      }
    }
    return null;
  }
}