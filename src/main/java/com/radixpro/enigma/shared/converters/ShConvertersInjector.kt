/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */
package com.radixpro.enigma.shared.converters

object ShConvertersInjector {

    @JvmStatic
    fun injectCsv2LocationConverter(): Csv2LocationConverter {
        return Csv2LocationConverter()
    }
}