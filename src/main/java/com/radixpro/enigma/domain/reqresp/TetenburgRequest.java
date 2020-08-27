/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.domain.reqresp;

import com.radixpro.enigma.domain.datetime.FullDateTime;
import com.radixpro.enigma.xchg.domain.LocationOld;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Request for the calculation of a critical point according to the theory of Ton TEtenburg.
 */
public class TetenburgRequest {

   private final double longMcRadix;
   private final double solarSpeed;
   private final LocationOld locationOld;
   private final FullDateTime birthDateTime;
   private final FullDateTime progDateTime;

   /**
    * Constructor defines all arguments.
    *
    * @param longMcRadix   ecliptical longitude MC radix. PRE: 0.0 <= longMcRadix < 360.0
    * @param solarSpeed    daily speed of Sun in radix. PRE: 0.9 < solarSpeed < 1.1
    * @param locationOld   location at birth (coudl be relocated but that does probably not make sense). PRE: not null.
    * @param birthDateTime Date and time at birth. PRE: not null
    * @param progDateTime  Date and time for progression. PRE: not null
    */
   public TetenburgRequest(final double longMcRadix, final double solarSpeed, final LocationOld locationOld, final FullDateTime birthDateTime,
                           final FullDateTime progDateTime) {
      checkArgument(0.0 <= longMcRadix && longMcRadix < 360.0);
      checkArgument(0.9 < solarSpeed && solarSpeed < 1.1);
      this.longMcRadix = longMcRadix;
      this.solarSpeed = solarSpeed;
      this.locationOld = checkNotNull(locationOld);
      this.birthDateTime = checkNotNull(birthDateTime);
      this.progDateTime = checkNotNull(progDateTime);
   }

   public double getLongMcRadix() {
      return longMcRadix;
   }

   public double getSolarSpeed() {
      return solarSpeed;
   }

   public LocationOld getLocation() {
      return locationOld;
   }

   public FullDateTime getBirthDateTime() {
      return birthDateTime;
   }

   public FullDateTime getProgDateTime() {
      return progDateTime;
   }
}
