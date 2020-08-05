/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.xchg.api;

import com.radixpro.enigma.be.persistency.ChartDataDao;
import com.radixpro.enigma.shared.FailFastHandler;
import com.radixpro.enigma.shared.exceptions.DatabaseException;
import com.radixpro.enigma.xchg.domain.ChartData;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PersistedChartDataApi {

   private final ChartDataDao dao;

   public PersistedChartDataApi(ChartDataDao dao) {
      this.dao = dao;
   }

   public int insert(final ChartData chartData) {
      checkNotNull(chartData);
      int chartId = -1;
      try {
         chartId = dao.insert(chartData);
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

   public List<ChartData> read(final int id) {
      return dao.read(id);
   }

   public List<ChartData> readAll() {
      List<ChartData> chartDataResult = new ArrayList<>();
      try {
         chartDataResult = dao.readAll();
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
      return chartDataResult;
   }

   public List<ChartData> search(final String searchName) {
      checkNotNull(searchName);
      return dao.search(searchName);
   }

}
