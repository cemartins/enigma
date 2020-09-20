/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */
package com.radixpro.enigma.domain.analysis

/**
 * Interface for analyzed results for a pair of points
 */
interface IAnalyzedPair {
    val first: AnalyzablePoint
    val second: AnalyzablePoint
    val actualOrb: Double
    val maxOrb: Double
    val percOrb: Double
}