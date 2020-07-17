/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.calc.handlers.astrondata.CalculatedChartHandler;
import com.radixpro.enigma.xchg.api.requests.CalculatedChartRequest;
import com.radixpro.enigma.xchg.api.responses.CalculatedChartResponse;
import com.radixpro.enigma.xchg.domain.astrondata.CalculatedChart;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Api for the calculation of a chart.
 */
public class CalculatedChartApi {

   private final CalculatedChartHandler handler;

   /**
    * Instantiate via factory.
    *
    * @param handler handler for the calculation. PRE: not null.
    * @see ApiChartCalcFactory
    */
   public CalculatedChartApi(final CalculatedChartHandler handler) {
      this.handler = checkNotNull(handler);
   }

   /**
    * Service for the calculation of a chart.
    *
    * @param request Request for the calcualtion. PRE: not null.
    * @return response with the calculated chart.
    */
   public CalculatedChartResponse calcChart(final CalculatedChartRequest request) {
      checkNotNull(request);
      CalculatedChart calculatedChart = null;
      String resultMsg = "OK";
      try {
         calculatedChart = handler.defineChart(request.getSettings(), request.getDateTime(), request.getLocation());
      } catch (Exception e) {
         resultMsg = "Exception when calculating a chart : " + e.getMessage();
      }
      return new CalculatedChartResponse(calculatedChart, resultMsg);
   }

}
