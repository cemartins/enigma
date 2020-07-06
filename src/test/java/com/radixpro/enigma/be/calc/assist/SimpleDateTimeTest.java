/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.calc.assist;

import com.radixpro.enigma.xchg.domain.SimpleDate;
import com.radixpro.enigma.xchg.domain.SimpleDateTime;
import com.radixpro.enigma.xchg.domain.SimpleTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SimpleDateTimeTest {

   @Mock
   private SimpleDate simpleDateMock;
   @Mock
   private SimpleTime simpleTimeMock;
   private SimpleDateTime simpleDateTime;

   @Before
   public void setUp() {
      simpleDateTime = new SimpleDateTime(simpleDateMock, simpleTimeMock);
   }

   @Test
   public void testGetDate() {
      assertEquals(simpleDateMock, simpleDateTime.getDate());
   }

   @Test
   public void testGetTime() {
      assertEquals(simpleTimeMock, simpleDateTime.getTime());
   }

}