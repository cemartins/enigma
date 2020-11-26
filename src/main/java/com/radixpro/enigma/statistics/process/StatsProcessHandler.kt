/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.statistics.process

import com.radixpro.enigma.statistics.core.DataTypes
import com.radixpro.enigma.statistics.core.ScenRangeBe
import com.radixpro.enigma.statistics.core.ScenarioBe

class StatsProcessHandler(val rangeProcessor: ScenRangeProcessor) {

    fun handleProcess(scenario: ScenarioBe, dataTypeText: String): String {
        val dataType = DataTypes.valueOf(dataTypeText)
        return if (scenario is ScenRangeBe) return rangeProcessor.process(scenario, dataType)
        else ""
    }

}