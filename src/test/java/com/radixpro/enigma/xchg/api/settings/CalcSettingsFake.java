/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.xchg.api.settings;

import com.radixpro.enigma.references.Ayanamshas;
import com.radixpro.enigma.references.CelestialObjects;
import com.radixpro.enigma.xchg.domain.IChartPoints;

import java.util.ArrayList;
import java.util.List;

public class CalcSettingsFake implements ICalcSettings {

   @Override
   public List<IChartPoints> getPoints() {
      List<IChartPoints> points = new ArrayList<>();
      points.add(CelestialObjects.URANUS);
      return points;
   }

   @Override
   public Ayanamshas getAyanamsha() {
      return Ayanamshas.HUBER;
   }

   @Override
   public boolean isSidereal() {
      return true;
   }
}
