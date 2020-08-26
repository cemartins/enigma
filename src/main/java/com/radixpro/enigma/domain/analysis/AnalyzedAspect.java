/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.domain.analysis;

import com.radixpro.enigma.references.AspectTypes;
import org.jetbrains.annotations.NotNull;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * ValueObject for an aspect that has been analyzed.
 */
public class AnalyzedAspect implements IAnalyzedPair {

   private final AnalyzablePoint firstPoint;
   private final AnalyzablePoint secondPoint;
   private final AspectTypes aspectType;
   private final double actualOrb;
   private final double maxOrb;
   private final double percOrb;


   /**
    * Constructor defines all parameters.
    * PRE: 0.0 <= actualOrb <= maxOrb
    *
    * @param firstPoint  First point that was compared.
    * @param secondPoint Second point that was compared.
    * @param aspectType  Type of aspect.
    * @param actualOrb   The actual orb fir this aspect.
    * @param maxOrb      The maximum orb for this aspect.
    */
   public AnalyzedAspect(@NotNull final AnalyzablePoint firstPoint, @NotNull final AnalyzablePoint secondPoint,
                         @NotNull final AspectTypes aspectType, final double actualOrb, final double maxOrb) {
      checkArgument(0.0 <= actualOrb && actualOrb <= maxOrb);
      this.firstPoint = firstPoint;
      this.secondPoint = secondPoint;
      this.aspectType = aspectType;
      this.actualOrb = actualOrb;
      this.maxOrb = maxOrb;
      this.percOrb = calculatePercOrb();
   }


   @Override
   public AnalyzablePoint getFirst() {
      return firstPoint;
   }

   @Override
   public AnalyzablePoint getSecond() {
      return secondPoint;
   }

   @Override
   public double getActualOrb() {
      return actualOrb;
   }

   @Override
   public double getMaxOrb() {
      return maxOrb;
   }

   @Override
   public double getPercOrb() {
      return percOrb;
   }

   public AspectTypes getAspectType() {
      return aspectType;
   }

   private double calculatePercOrb() {
      return (actualOrb / maxOrb) * 100.0;
   }

}
