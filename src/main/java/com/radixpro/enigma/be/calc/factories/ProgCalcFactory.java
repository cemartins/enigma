/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.be.calc.factories;

import com.radixpro.enigma.be.calc.assist.CoordSetForDateTimeCalc;
import com.radixpro.enigma.be.calc.assist.JdFromPosCalc;
import com.radixpro.enigma.be.calc.core.SeFrontend;
import com.radixpro.enigma.be.calc.handlers.EphProgCalcHandler;
import com.radixpro.enigma.be.calc.handlers.SolarReturnHandler;
import com.radixpro.enigma.be.calc.handlers.TetenburgHandler;

/**
 * Factory for handlers and assists that take care of calculations for progressive techniques.
 */
public class ProgCalcFactory {

   public EphProgCalcHandler getTransitsCalcHandler() {
      return new EphProgCalcHandler(SeFrontend.getFrontend());
   }

   public CoordSetForDateTimeCalc getCoordSetForDateTimeCalc() {
      return new CoordSetForDateTimeCalc(SeFrontend.getFrontend());
   }

   public JdFromPosCalc getJdFromPosCalc() {
      return new JdFromPosCalc(getCoordSetForDateTimeCalc());
   }

   public SolarReturnHandler getSolarReturnHandler() {
      return new SolarReturnHandler(getJdFromPosCalc());
   }

   public TetenburgHandler getTetenburgHandler() {
      return new TetenburgHandler(SeFrontend.getFrontend());
   }

}
