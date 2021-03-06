/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.ui.screens.helpers;

import com.radixpro.enigma.Rosetta;
import com.radixpro.enigma.domain.config.ConfiguredCelObject;
import com.radixpro.enigma.references.CelObjectCategory;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Overview of celestial objects in a configuration.
 */
public class CelObjectsInConfig {

   private static final Logger LOG = Logger.getLogger(CelObjectsInConfig.class);


   public List<PresentableProperty> constructProperties(@NotNull final List<ConfiguredCelObject> celObjects) {
      List<PresentableProperty> presentableProperties = new ArrayList<>();
      StringBuilder classicCelObjectsAsText = new StringBuilder();
      StringBuilder modernCelObjectsAsText = new StringBuilder();
      StringBuilder extraplutCelObjectsAsText = new StringBuilder();
      StringBuilder asteroidCelObjectsAsText = new StringBuilder();
      StringBuilder centaurCelObjectsAsText = new StringBuilder();
      StringBuilder intersectionsCelObjectsAsText = new StringBuilder();
      StringBuilder hypothetsCelObjectsAsText = new StringBuilder();
      int category;
      String nameText;
      for (ConfiguredCelObject celObject : celObjects) {
         category = celObject.getCelObject().getCategory().getId();
         nameText = Rosetta.getText(celObject.getCelObject().getRbKey()) + " ";
         switch (category) {
            case 1 -> classicCelObjectsAsText.append(nameText);
            case 2 -> modernCelObjectsAsText.append(nameText);
            case 3 -> extraplutCelObjectsAsText.append(nameText);
            case 4 -> asteroidCelObjectsAsText.append(nameText);
            case 5 -> centaurCelObjectsAsText.append(nameText);
            case 6 -> intersectionsCelObjectsAsText.append(nameText);
            case 7 -> hypothetsCelObjectsAsText.append(nameText);
            default -> LOG.error("Invalid category for celestial body while constructing details of configuration." +
                  "Received category with Id : " + category + ". Celestial object was ignored.");
         }
      }
      if (classicCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            Rosetta.getText(CelObjectCategory.CLASSICS.getNameForRB()), classicCelObjectsAsText.toString()));
      if (modernCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            Rosetta.getText(CelObjectCategory.MODERN.getNameForRB()), modernCelObjectsAsText.toString()));
      if (extraplutCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            Rosetta.getText(CelObjectCategory.EXTRA_PLUT.getNameForRB()), extraplutCelObjectsAsText.toString()));
      if (asteroidCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            Rosetta.getText(CelObjectCategory.ASTEROIDS.getNameForRB()), asteroidCelObjectsAsText.toString()));
      if (centaurCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            Rosetta.getText(CelObjectCategory.CENTAURS.getNameForRB()), centaurCelObjectsAsText.toString()));
      if (intersectionsCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            Rosetta.getText(CelObjectCategory.INTERSECTIONS.getNameForRB()), intersectionsCelObjectsAsText.toString()));
      if (hypothetsCelObjectsAsText.length() > 0) presentableProperties.add(new PresentableProperty(
            Rosetta.getText(CelObjectCategory.HYPOTHETS.getNameForRB()), hypothetsCelObjectsAsText.toString()));
      return presentableProperties;
   }

}
