/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.xchg.domain.astrondata;

import com.radixpro.enigma.xchg.domain.IChartPoints;

import static com.google.common.base.Preconditions.checkNotNull;

public class LonDeclPosition implements IPosition {

   private final IChartPoints chartPoint;
   private final double longitude;
   private final double declination;

   public LonDeclPosition(final IChartPoints chartPoint, final double longitude, final double declination) {
      this.chartPoint = checkNotNull(chartPoint);
      this.longitude = longitude;
      this.declination = declination;
   }

   @Override
   public double getLongitude() {
      return longitude;
   }

   @Override
   public double getDeclination() {
      return declination;
   }

   @Override
   public IChartPoints getChartPoint() {
      return chartPoint;
   }
}