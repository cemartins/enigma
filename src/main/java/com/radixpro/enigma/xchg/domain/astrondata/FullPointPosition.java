/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.xchg.domain.astrondata;

import com.radixpro.enigma.xchg.domain.CelestialObjects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Full set of positions with all coordinates for a specific celestial object.
 */
public class FullPointPosition {

   private final CelestialObjects celObject;
   private final FullPointCoordinate eclPos;
   private final FullPointCoordinate eqPos;
   private final FullPointCoordinate horPos;

   /**
    * Constructor defines all properties.
    *
    * @param celObject definition of celestial object. PRE: not null.
    * @param eclPos    ecliptical position. PRE: not null.
    * @param eqPos     equatorial positon. PRE: not null.
    * @param horPos    horizontal position. PRE: not null.
    */
   public FullPointPosition(final CelestialObjects celObject, final FullPointCoordinate eclPos, final FullPointCoordinate eqPos,
                            final FullPointCoordinate horPos) {
      this.celObject = checkNotNull(celObject);
      this.eclPos = checkNotNull(eclPos);
      this.eqPos = checkNotNull(eqPos);
      this.horPos = checkNotNull(horPos);
   }

   public CelestialObjects getCelObject() {
      return celObject;
   }

   public FullPointCoordinate getEclPos() {
      return eclPos;
   }

   public FullPointCoordinate getEqPos() {
      return eqPos;
   }

   public FullPointCoordinate getHorPos() {
      return horPos;
   }
}
