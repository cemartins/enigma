/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.ui.screens.helpers;

import com.radixpro.enigma.Rosetta;
import com.radixpro.enigma.ui.shared.presentationmodel.PresentableProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A table with details of properties for a configuration.
 */
public class PropertiesTableForConfig {

   /**
    * Constructs the table
    *
    * @param height     Preferred height of the table.
    * @param width      Preferred width of the table.
    * @param properties List with actual properties.
    * @return Constructed table with properties int he form of a TableView.
    */
   public TableView<PresentableProperty> getTableView(final double height, final double width, @NotNull List<PresentableProperty> properties) {
      return createTableView(height, width, properties);
   }


   private TableView<PresentableProperty> createTableView(double height, double width, List<PresentableProperty> properties) {
      TableView<PresentableProperty> tableView = new TableView<>();
      tableView.setPrefHeight(height);
      tableView.setPrefWidth(width);
      TableColumn<PresentableProperty, String> propertyColumn = new TableColumn<>(Rosetta.getText("config.propertyname"));
      propertyColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      TableColumn<PresentableProperty, String> valueColumn = new TableColumn<>(Rosetta.getText(("config.propertyvalue")));
      valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
      tableView.getColumns().add(propertyColumn);
      tableView.getColumns().add(valueColumn);

      for (PresentableProperty prop : properties) {
         tableView.getItems().add(prop);
      }
      return tableView;
   }

}
