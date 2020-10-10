/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */
package com.radixpro.enigma.be.persistency

import com.radixpro.enigma.be.persistency.mappers.BePersMappersInjector.injectInputDataSetMapper
import com.radixpro.enigma.be.persistency.mappers.BePersMappersInjector.injectStatsProjMapper
import com.radixpro.enigma.shared.converters.ShConvertersInjector.injectCsv2LocationConverter

object BePersistencyInjector {
    fun injectChartDataDao(): ChartDataDao {
        return ChartDataDao()
    }

    fun injectConfigurationDao(): ConfigurationDao {
        return ConfigurationDao()
    }

    fun injectDataFileDao(): DataFileDao {
        return DataFileDao(injectJsonReader(), injectInputDataSetMapper())
    }

    fun injectDataReaderCsv(): DataReaderCsv {
        return DataReaderCsv(injectCsv2LocationConverter())
    }

    fun injectJsonReader(): JsonReader {
        return JsonReader()
    }

    fun injectJsonWriter(): JsonWriter {
        return JsonWriter()
    }

    fun injectPropertyDao(): PropertyDao {
        return PropertyDao()
    }

    fun injectStatsProjDao(): StatsProjDaoH2 {
        return StatsProjDaoH2(injectJsonWriter(), injectJsonReader(), injectStatsProjMapper())
    }

    fun injectVersionDao(): VersionDao {
        return VersionDao()
    }
}