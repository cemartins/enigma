/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.be.persistency.mappers;

import com.radixpro.enigma.ui.helpers.UiHelpersInjector;

public class BePersMappersInjector {

   private BePersMappersInjector() {
      // prevent instantiation
   }

   public static ChartDataCsvMapper injectChartDataCsvMapper() {
      return new ChartDataCsvMapper(UiHelpersInjector.injectDateTimeJulianCreator());
   }

   public static InputDataSetMapper injectInputDataSetMapper() {
      return new InputDataSetMapper();
   }

}
