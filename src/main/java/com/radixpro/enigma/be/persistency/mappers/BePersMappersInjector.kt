/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */
package com.radixpro.enigma.be.persistency.mappers

import com.radixpro.enigma.ui.helpers.UiHelpersInjector

object BePersMappersInjector {
    fun injectChartDataCsvMapper(): ChartDataCsvMapper {
        return ChartDataCsvMapper(UiHelpersInjector.injectDateTimeJulianCreator())
    }

    @JvmStatic
    fun injectInputDataSetMapper(): InputDataSetMapper {
        return InputDataSetMapper()
    }

    fun injectStatsProjMapper(): StatsProjMapper {
        return StatsProjMapper()
    }
}