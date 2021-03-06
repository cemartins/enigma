/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.ui.creators;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.radixpro.enigma.ui.shared.UiDictionary.STYLESHEET;

/**
 * Creates a VBox, based on the Builder pattern.
 */
public class VBoxBuilder {

   private double prefWidth;
   private double padding;
   private double prefHeight;
   private String style;
   private Node[] children;


   public VBoxBuilder setWidth(final double prefWidth) {
      checkArgument(prefWidth > 0.0);
      this.prefWidth = prefWidth;
      return this;
   }

   public VBoxBuilder setHeight(final double prefHeight) {
      checkArgument(prefHeight > 0.0);
      this.prefHeight = prefHeight;
      return this;
   }

   public VBoxBuilder setPadding(final double padding) {
      checkArgument(padding > 0.0);
      this.padding = padding;
      return this;
   }

   public VBoxBuilder setChildren(@NotNull final Node... children) {
      this.children = children;
      return this;
   }

   public VBoxBuilder setStyle(final String style) {
      this.style = style;
      return this;
   }

   public VBox build() {
      VBox vBox = new VBox();
      vBox.getStylesheets().add(STYLESHEET);
      vBox.setPrefWidth(prefWidth);
      if (prefHeight > 0.0) vBox.setPrefHeight(prefHeight);
      if (padding > 0.0) vBox.setPadding(new Insets(padding, padding, padding, padding));
      if (null != style && !style.isBlank()) vBox.setStyle(style);
      if (children != null && children.length > 0) vBox.getChildren().addAll(children);
      return vBox;
   }

}
