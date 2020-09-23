/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.persistency.ChartDataDao;
import com.radixpro.enigma.shared.FailFastHandler;
import com.radixpro.enigma.shared.exceptions.DatabaseException;
import com.radixpro.enigma.xchg.domain.FullChartInputData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PersistedChartDataApi {

   private final ChartDataDao dao;

   public PersistedChartDataApi(@NotNull ChartDataDao dao) {
      this.dao = dao;
   }

   public int insert(@NotNull final FullChartInputData fullChartInputData) {
      int chartId = -1;
      try {
         chartId = dao.insert(fullChartInputData);
      } catch (DatabaseException de) {
         System.out.println(de.getMessage());
         //     new FailFastHandler().terminate(de.getMessage());
      }
      return chartId;
   }

   public void delete(final int chartId) {
      try {
         dao.delete(chartId);
      } catch (Exception e) {
         new FailFastHandler().terminate(e.getMessage());
      }
   }

   public List<FullChartInputData> read(final int id) {
      return dao.read(id);
   }

   public List<FullChartInputData> readAll() {
      List<FullChartInputData> fullChartInputDataResult = new ArrayList<>();
      try {
         fullChartInputDataResult = dao.readAll();
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
      return fullChartInputDataResult;
   }

   public List<FullChartInputData> search(@NotNull final String searchName) {
      return dao.search(searchName);
   }

}