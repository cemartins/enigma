/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */
package com.radixpro.enigma.xchg.api

import com.radixpro.enigma.statistics.core.DataFileDescription
import com.radixpro.enigma.statistics.process.DataFileHandler

class PersistedDataFileApi(private val handler: DataFileHandler) {

    fun readDataFileDescriptions(): List<DataFileDescription> {
        return handler.readDataFileDesciptions()
    }
}