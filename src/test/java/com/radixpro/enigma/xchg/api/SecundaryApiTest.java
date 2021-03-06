/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.domain.analysis.IAnalyzedPair;
import com.radixpro.enigma.domain.astronpos.AllMundanePositions;
import com.radixpro.enigma.domain.astronpos.AstronSpecifics;
import com.radixpro.enigma.domain.astronpos.CalculatedChart;
import com.radixpro.enigma.domain.astronpos.IPosition;
import com.radixpro.enigma.domain.input.DateTimeJulian;
import com.radixpro.enigma.domain.input.Location;
import com.radixpro.enigma.domain.reqresp.EphProgAspectResponse;
import com.radixpro.enigma.domain.reqresp.ProgAnalyzeRequest;
import com.radixpro.enigma.domain.reqresp.SecundaryCalcRequest;
import com.radixpro.enigma.domain.reqresp.SimpleProgResponse;
import com.radixpro.enigma.references.*;
import com.radixpro.enigma.ui.helpers.DateTimeJulianCreator;
import com.radixpro.enigma.ui.helpers.LocationCreator;
import com.radixpro.enigma.xchg.api.settings.ICalcSettings;
import com.radixpro.enigma.xchg.api.settings.ProgSettings;
import com.radixpro.enigma.xchg.domain.IChartPoints;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.radixpro.enigma.testsupport.TestConstants.DELTA_5_POS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecundaryApiTest {

   @Mock
   private IPosition posSunMock;
   @Mock
   private IPosition posMarsMock;
   @Mock
   private IPosition posUraMock;
   @Mock
   private IPosition posAscMock;
   private SecundaryApi api;

   @Before
   public void setUp() throws Exception {
      when(posSunMock.getLongitude()).thenReturn(351.8);    // SUN, 351.8, 1.0, 352.0, -8.8
      when(posMarsMock.getLongitude()).thenReturn(21.5);    // MARS, 21.5, 0.7, 22.0, 4.4
      when(posUraMock.getLongitude()).thenReturn(102.7);    // URANUS, 102.7, 0.3, 103.0, 4.0
      when(posAscMock.getLongitude()).thenReturn(162.5);    // ASC, 162.5, 0.0, 162.0, 20.0)
      when(posMarsMock.getChartPoint()).thenReturn(CelestialObjects.MARS);
      api = XchgApiInjector.injectSecundaryApi();
   }

   @Test
   public void calculateSecundary() {
      SecundaryCalcRequest request = createCalcRequest();
      final SimpleProgResponse response = api.calculateSecundary(request);
      assertEquals(3, response.getPositions().size());
      List<IPosition> positions = response.getPositions();
      assertEquals(CelestialObjects.SUN, positions.get(0).getChartPoint());
      // Difference in Julian days is 7305
      // Divide by length of tropical year : 365.24219 gives 20.0004276614
      // which is 20 days 0 h 0 m and 37 sec  --> 2000/6/26 13:42:37  CET
      // Positions (no parallax):
      // Sun 5d12m10s CN    decl 23d20m07s N
      // Moon 23d24m52s AR
      // Mars 06d41m21s CN


      assertEquals(95.20299984447792, positions.get(0).getLongitude(), DELTA_5_POS);
      assertEquals(23.3354796103748, positions.get(0).getDeclination(), DELTA_5_POS);
//      assertEquals(23.414673045168932, positions.get(1).getLongitude(), DELTA_5_POS);    // FIXME reactivate test and check value for JD
      assertEquals(96.68935276274844, positions.get(2).getLongitude(), DELTA_5_POS);
   }

   @Test
   public void defineAspects() {
      SecundaryCalcRequest request = createCalcRequest();
      final SimpleProgResponse response = api.calculateSecundary(request);
      List<IPosition> progPositions = response.getPositions();
      final EphProgAspectResponse ephProgAspectResponse = api.defineAspects(createAnalyzeRequest(progPositions));
      assertEquals(1, ephProgAspectResponse.getAnalyzedAspects().size());
      IAnalyzedPair aspect = ephProgAspectResponse.getAnalyzedAspects().get(0);
      assertEquals(CelestialObjects.MOON, aspect.getFirst().getChartPoint());
      assertEquals(CelestialObjects.MARS, aspect.getSecond().getChartPoint());
   }

   private SecundaryCalcRequest createCalcRequest() {
//      SimpleDate date = new SimpleDate(2020, 6, 6, true);
//      final SimpleTime time = new SimpleTime(13, 42, 0);
//      SimpleDateTime dateTime = new SimpleDateTime(date, time);
//      final FullDateTime eventDateTime = new FullDateTime(dateTime, TimeZones.CET, false, 0.0);
      String dateText = "2020/6/6";
      String cal = "G";
      String timeText = "13:42:00";
      boolean dst = false;
      double offSetLmt = 0.0;
      final DateTimeJulian eventDateTime = new DateTimeJulianCreator().createDateTime(dateText, cal, timeText, TimeZones.CET, dst, offSetLmt);
      dateText = "2000/6/6";

//      date = new SimpleDate(2000, 6, 6, true);
//      dateTime = new SimpleDateTime(date, time);
//      final FullDateTime birthDateTime = new FullDateTime(dateTime, TimeZones.CET, false, 0.0);
      final DateTimeJulian birthDateTime = new DateTimeJulianCreator().createDateTime(dateText, cal, timeText, TimeZones.CET, dst, offSetLmt);
      Location location = new LocationCreator().createLocation(52, 13, 0, "n", 6, 54, 0, "e");
      List<IChartPoints> points = new ArrayList<>();
      points.add(CelestialObjects.SUN);
      points.add(CelestialObjects.MOON);
      points.add(CelestialObjects.MARS);
      final ICalcSettings settings = new ProgSettings(points, Ayanamshas.NONE, false, false);
      return new SecundaryCalcRequest(eventDateTime, birthDateTime, location, settings);
   }

   private ProgAnalyzeRequest createAnalyzeRequest(final List<IPosition> progPositions) {
      List<AspectTypes> aspects = new ArrayList<>();
      aspects.add(AspectTypes.CONJUNCTION);
      aspects.add(AspectTypes.OPPOSITION);
      aspects.add(AspectTypes.TRIANGLE);
      aspects.add(AspectTypes.SQUARE);
      aspects.add(AspectTypes.SEXTILE);
      List<IPosition> celestialPositions = new ArrayList<>();
      celestialPositions.add(posSunMock);
      celestialPositions.add(posMarsMock);
      celestialPositions.add(posUraMock);
      List<IPosition> mundanePositions = new ArrayList<>();
      mundanePositions.add(posAscMock);
      AllMundanePositions allMundPos = new AllMundanePositions(mundanePositions, mundanePositions);
      AstronSpecifics astronSpecifics = new AstronSpecifics(0.0, 0.0);
      CalculatedChart calcChart = new CalculatedChart(celestialPositions, allMundPos, astronSpecifics);
      return new ProgAnalyzeRequest(ProgAnalysisType.ASPECTS, progPositions, calcChart, aspects, 2.0);
   }

}