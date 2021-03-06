/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */
package com.radixpro.enigma.references

enum class AspectTypes(val id: Int,
                       val aspectCategory: AspectCategory,
                       val angle: Double,
                       val fullRbId: String) {
    CONJUNCTION(1, AspectCategory.MAJOR, 0.0, "aspects.conjunction"),
    OPPOSITION(2, AspectCategory.MAJOR, 180.0, "aspects.opposition"),
    TRIANGLE(3, AspectCategory.MAJOR, 120.0, "aspects.triangle"),
    SQUARE(4, AspectCategory.MAJOR, 90.0, "aspects.square"),
    SEXTILE(5, AspectCategory.MAJOR, 60.0, "aspects.sextile"),
    SEMISEXTILE(6, AspectCategory.MINOR, 30.0, "aspects.semisextile"),
    INCONJUNCT(7, AspectCategory.MINOR, 150.0, "aspects.inconjunct"),
    SEMISQUARE(8, AspectCategory.MINOR, 45.0, "aspects.semisquare"),
    SESQUIQUADRATE(9, AspectCategory.MINOR, 135.0, "aspects.sesquiquadrate"),
    QUINTILE(10, AspectCategory.MINOR, 72.0, "aspects.quintile"),
    BIQUINTILE(11, AspectCategory.MINOR, 144.0, "aspects.biquintile"),
    SEPTILE(12, AspectCategory.MINOR, 51.42857143, "aspects.septile"),
    PARALLEL(13, AspectCategory.DECLINATION, 0.0, "aspects.parallel"),
    CONTRAPARALLEL(14, AspectCategory.DECLINATION, 0.0, "aspects.contraparallel"),
    VIGINTILE(15, AspectCategory.MICRO, 18.0, "aspects.vigintile"),
    SEMIQUINTILE(16, AspectCategory.MICRO, 36.0, "aspects.semiquintile"),
    TRIDECILE(17, AspectCategory.MICRO, 108.0, "aspects.tridecile"),
    BISEPTILE(18, AspectCategory.MICRO, 102.857142857, "aspects.biseptile"),
    TRISEPTILE(19, AspectCategory.MICRO, 154.2857142857, "aspects.triseptile"),
    NOVILE(20, AspectCategory.MICRO, 40.0, "aspects.novile"),
    BINOVILE(21, AspectCategory.MICRO, 80.0, "aspects.binovile"),
    QUADRANOVILE(22, AspectCategory.MICRO, 160.0, "aspects.quadranovile"),
    UNDECILE(23, AspectCategory.MICRO, 32.7272727272, "aspects.undecile"),
    CENTILE(24, AspectCategory.MICRO, 100.0, "aspects.centile");

    companion object {
        @JvmStatic
        fun getAspectForId(id: Int): AspectTypes? {
            for (aspect in values()) {
                if (aspect.id == id) {
                    return aspect
                }
            }
            return null
            // TODO Release 2020.2: throw exception if aspect is not found
        }
    }
}