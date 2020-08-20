/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.ui.creators;

import com.radixpro.enigma.Rosetta;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Creates a Label, based on the Builder pattern.</br>
 * If a text is entered, this will overwrite the value as indicated with rbKey (key to the resourcebundle).
 */
public class LabelBuilder {

   private final Rosetta rosetta;
   private String rbKey = "";
   private String text = "";
   private double prefWidth;
   private double prefHeight;
   private double layoutX;
   private double layoutY;
   private Pos alignment;
   private String styleClass;

   public LabelBuilder(final String rbKey) {
      this.rbKey = checkNotNull(rbKey);
      this.rosetta = Rosetta.getRosetta();
   }

   /**
    * Use only to overwrite the text from the resourcebundle.
    *
    * @param text Text to rep[lace the value from the resource bundle.
    * @return Partially initialized LabelBuilder.
    */
   public LabelBuilder setText(final String text) {
      checkNotNull(text);
      this.text = text;
      return this;
   }

   public LabelBuilder setPrefWidth(final double prefWidth) {
      checkArgument(prefWidth > 0.0);
      this.prefWidth = prefWidth;
      return this;
   }

   public LabelBuilder setPrefHeight(final double prefHeight) {
      checkArgument(prefHeight > 0.0);
      this.prefHeight = prefHeight;
      return this;
   }

   public LabelBuilder setLayoutX(final double layoutX) {
      this.layoutX = layoutX;
      return this;
   }

   public LabelBuilder setLayoutY(final double layoutY) {
      this.layoutY = layoutY;
      return this;
   }

   public LabelBuilder setStyleClass(final String styleClass) {
      this.styleClass = checkNotNull(styleClass);
      return this;
   }

   public LabelBuilder setAlignment(final Pos alignment) {
      this.alignment = checkNotNull(alignment);
      return this;
   }

   public Label build() {
      String lblText = "";
      if (!text.isEmpty()) lblText = text;
      else lblText = (rbKey.isEmpty() ? "" : rosetta.getText(rbKey));
      Label label = new Label(lblText);
      if (prefWidth > 0.0) label.setPrefWidth(prefWidth);
      if (prefHeight > 0.0) label.setPrefHeight(prefHeight);
      if (layoutX > 0.0) label.setLayoutX(layoutX);
      if (layoutY > 0.0) label.setLayoutY(layoutY);
      if (null != styleClass && !styleClass.isEmpty()) label.getStyleClass().add(styleClass);
      if (null != alignment) label.setAlignment(alignment);
      return label;
   }

}
