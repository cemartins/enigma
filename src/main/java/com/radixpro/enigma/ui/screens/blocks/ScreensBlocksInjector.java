/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.ui.screens.blocks;

import com.radixpro.enigma.AppScope;
import com.radixpro.enigma.ui.validators.UiValidatorsInjector;

public class ScreensBlocksInjector {

   private ScreensBlocksInjector() {
      // prevent instantiation
   }

   public static DateTimeInputBlock injectDateTimeInputBlock(AppScope scope) {
      return new DateTimeInputBlock(scope.getSessionState(), UiValidatorsInjector.injectValidatedDate(scope), UiValidatorsInjector.injectValidatedTime(scope),
            UiValidatorsInjector.injectValidatedLongitude(scope));
   }

   public static LocationInputBlock injectLocationInputBlock(AppScope scope) {
      return new LocationInputBlock(scope.getSessionState(), UiValidatorsInjector.injectValidatedLongitude(scope),
            UiValidatorsInjector.injectValidatedLatitude(scope));
   }

   public static ProgMetaInputBlock injectProgMetaInputBLock(AppScope scope) {
      return new ProgMetaInputBlock(scope.getSessionState());
   }

}