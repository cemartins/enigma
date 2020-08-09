/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.domain.reqresp;

import com.radixpro.enigma.domain.astronpos.CalculatedChart;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Response after calculating a Solar Return Chart.
 */
public class SolarReturnResponse {

   private final CalculatedChart solarReturnChart;
   private final String resultMsg;

   /**
    * Constructor defines all properties.
    *
    * @param solarReturnChart the calculated chart for the Solar Return.
    * @param resultMsg        Result message: "OK" if no error occurred, otherwise with a descriptive text of the error.
    */
   public SolarReturnResponse(final CalculatedChart solarReturnChart, final String resultMsg) {
      this.solarReturnChart = solarReturnChart;
      this.resultMsg = checkNotNull(resultMsg);
   }

   public CalculatedChart getSolarReturnChart() {
      return solarReturnChart;
   }

   public String getResultMsg() {
      return resultMsg;
   }
}