/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.statistics.testhelpers

import com.radixpro.enigma.astronomy.ui.domain.CelObjects
import com.radixpro.enigma.astronomy.ui.domain.MundanePoints
import com.radixpro.enigma.statistics.core.ScenRangeBe
import com.radixpro.enigma.statistics.ui.domain.ScenRangeFe
import com.radixpro.enigma.statistics.ui.domain.ScenarioTypes
import com.radixpro.enigma.statistics.ui.domain.StatsRangeTypes

class ScenarioCreators {

    private val scenName = "Name for scenario"
    private val rangeDescr = "Descr for scenario"
    private val projName = "Project with scenarios"
    private val scenType = "RANGE"
    private val notExistingScenType = "DOES NOT EXIST"
    private val rangeTypeName = "SIGNS"

    fun createScenRangeFe(): ScenRangeFe {
        return ScenRangeFe(scenName, rangeDescr, projName, scenType, rangeTypeName, createCelObjectNames(), createMundanePointNames())
    }

    fun createScenRangeBe(): ScenRangeBe {
        return ScenRangeBe(scenName, rangeDescr, projName, ScenarioTypes.RANGE, StatsRangeTypes.SIGNS, createCelObjects(), createMundanePoints())
    }

    fun createScenRangeFeError(): ScenRangeFe {
        return ScenRangeFe(scenName, rangeDescr, projName, notExistingScenType, rangeTypeName, createCelObjectNames(), createMundanePointNames())
    }

    private fun createCelObjectNames(): List<String> {
        return listOf("SUN", "MOON", "MERCURY")
    }

    private fun createCelObjects(): List<CelObjects> {
        return listOf(CelObjects.SUN, CelObjects.MOON, CelObjects.MERCURY)
    }

    private fun createMundanePointNames(): List<String> {
        return listOf("MC", "ASC")
    }

    private fun createMundanePoints(): List<MundanePoints> {
        return listOf(MundanePoints.MC, MundanePoints.ASC)
    }

}