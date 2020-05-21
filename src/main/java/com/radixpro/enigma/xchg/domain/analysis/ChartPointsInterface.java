/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.xchg.domain.analysis;

public interface ChartPointsInterface {

   int getId();

   String getRbKey();

   ChartPointsInterface getItemForId(final int id);

}
