/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.be.persistency.mappers

import com.fasterxml.jackson.core.JsonParseException
import com.radixpro.enigma.domain.config.BaseAstronConfig
import com.radixpro.enigma.domain.stats.DataFileDescription
import com.radixpro.enigma.domain.stats.IStatsProject
import com.radixpro.enigma.domain.stats.StatsFailedProject
import com.radixpro.enigma.domain.stats.StatsProject
import com.radixpro.enigma.references.*
import org.apache.log4j.Logger
import org.json.simple.JSONObject

/**
 * Converts from Json to StatsProject
 */
class StatsProjMapper {

    private val log = Logger.getLogger(StatsProjMapper::class.java)

    fun jsonToStatsProject(jsonObject: JSONObject): IStatsProject {
        return constructStatsProject(jsonObject)
    }

    private fun constructStatsProject(jsonObject: JSONObject): IStatsProject {
        return try {
            val name = jsonObject["name"] as String
            val description = jsonObject["description"] as String
            val config = createConfig(jsonObject["baseAstronConfig"] as JSONObject)
            val dataFile = createDataFile(jsonObject["dataFile"] as JSONObject)
            StatsProject(true, name, description, config, dataFile)
        } catch (e: JsonParseException) {
            log.error("Error parsing Json for StatsProject. Message ${e.message}. Json: $jsonObject")
            StatsFailedProject(false, ErrorMsgs.PARSE_ERROR_JSON)
        }
    }

    private fun createConfig(jsonObject: JSONObject): BaseAstronConfig {
        val houseSystem = HouseSystems.valueOf(jsonObject["houseSystem"] as String)
        val ayanamsha = Ayanamshas.valueOf(jsonObject["ayanamsha"] as String)
        val eclProj = EclipticProjections.valueOf(jsonObject["eclipticProjection"] as String)
        val obsPos = ObserverPositions.valueOf(jsonObject["observerPosition"] as String)
        return BaseAstronConfig(houseSystem, ayanamsha, eclProj, obsPos)
    }

    private fun createDataFile(jsonObject: JSONObject): DataFileDescription {
//        val dataFiles: MutableList<DataFileDescription> = ArrayList()
//        for (dataObject in jsonArray) {
//            val jsonObject = dataObject as JSONObject
        val name = jsonObject["name"] as String
        val description = jsonObject["description"] as String
        val nrOfRecords = jsonObject["nrOfRecords"].toString().toInt()
        return DataFileDescription(name, description, nrOfRecords)
//        }
//        return dataFiles
    }

}
