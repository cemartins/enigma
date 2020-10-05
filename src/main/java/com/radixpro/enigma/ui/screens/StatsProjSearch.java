/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.ui.screens;

import com.radixpro.enigma.Rosetta;
import com.radixpro.enigma.domain.stats.IStatsProject;
import com.radixpro.enigma.domain.stats.StatsProject;
import com.radixpro.enigma.ui.creators.*;
import com.radixpro.enigma.ui.shared.Help;
import com.radixpro.enigma.xchg.api.StatsProjApi;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.radixpro.enigma.ui.shared.UiDictionary.*;

public class StatsProjSearch {

   private static final double HEIGHT = 400.0;
   private final StatsProjApi statsProjApi;
   private Label lblTitle;
   private Label lblInstruction;
   private Pane paneTitle;
   private TextField tfSearchArg;
   private Button btnCancel;
   private Button btnHelp;
   private Button btnOk;
   private Button btnSearch;
   private ListView<String> lvSearchResults;
   private List<StatsProject> projects;
   private Stage stage;
   private List<String> statsProjNames;
   private StatsProject selectedItem;
   private boolean selectionMade = false;

   public StatsProjSearch(@NotNull final StatsProjApi statsProjApi) {
      this.statsProjApi = statsProjApi;
   }

   public void show() {
      initialize();
      stage = new Stage();
      stage.setTitle(Rosetta.getText("ui.stats.projsearch.title"));
      stage.setWidth(INPUT_WIDTH);
      stage.setScene(new Scene(createGridPane()));
      stage.showAndWait();
   }

   private void initialize() {
      defineLeafs();
      definePanes();
   }

   private void definePanes() {
      paneTitle = new PaneBuilder().setWidth(INPUT_WIDTH).setHeight(TITLE_HEIGHT).setStyleClass("titlepane").setChildren(lblTitle).build();
   }

   private void defineLeafs() {
      lblTitle = new LabelBuilder("ui.stats.projsearch.lbltitle").setPrefWidth(INPUT_WIDTH).setStyleClass("titletext").build();
      lblInstruction = new LabelBuilder("ui.stats.projsearch.lblinstruction").setPrefWidth(INPUT_WIDTH).build();
      tfSearchArg = new TextFieldBuilder().setPrefWidth(SMALL_INPUT_WIDTH).setStyleClass("inputDefault").build();
      lvSearchResults = new ListViewBuilder().setHeight(180.0).setWidth(SMALL_INPUT_WIDTH).setStyleClass("inputDefault").build();
      btnSearch = new ButtonBuilder("ui.shared.btn.search").setDisabled(false).build();
      btnSearch.setOnAction(click -> onSearch());
      btnCancel = new ButtonBuilder("ui.shared.btn.cancel").setDisabled(false).build();
      btnCancel.setOnAction(click -> onCancel());
      btnHelp = new ButtonBuilder("ui.shared.btn.help").setDisabled(false).build();
      btnHelp.setOnAction(click -> onHelp());
      btnOk = new ButtonBuilder("ui.shared.btn.ok").setDisabled(false).build();
      btnOk.setOnAction(click -> onOk());
   }

   private GridPane createGridPane() {  // col, row
      GridPane gridPane = new GridPaneBuilder().setPrefHeight(HEIGHT).setPrefWidth(INPUT_WIDTH).setHGap(GAP).setVGap(GAP).setStyleSheet(STYLESHEET).build();
      gridPane.add(paneTitle, 0, 0, 2, 1);
      gridPane.add(lblInstruction, 0, 1, 2, 1);
      gridPane.add(tfSearchArg, 0, 2, 1, 1);
      gridPane.add(btnSearch, 1, 2, 1, 1);
      gridPane.add(lvSearchResults, 0, 3, 1, 1);
      gridPane.add(createButtonBar(), 0, 4, 2, 1);
      return gridPane;
   }

   private ButtonBar createButtonBar() {
      return new ButtonBarBuilder().setButtons(btnHelp, btnCancel, btnOk).build();
   }

   private void onSearch() {
      lvSearchResults.getItems().clear();
      String arg = tfSearchArg.getText();             // todo use arg
      statsProjNames = statsProjApi.readAllNames();
      for (String projName : statsProjNames) {
         lvSearchResults.getItems().add(projName);
      }
   }


   private void onCancel() {
      lvSearchResults.getItems().clear();
      stage.close();
   }

   private void onHelp() {
      new Help(Rosetta.getHelpText("help.statsprojsearch.title"), Rosetta.getHelpText("help.statsprojsearch.content"));
   }

   private void onOk() {
      int index = lvSearchResults.getSelectionModel().getSelectedIndex();
      if (index >= 0) {
         String projName = statsProjNames.get(index);
         IStatsProject result = statsProjApi.read(projName);
         if (result.getSuccess()) {
            selectedItem = (StatsProject) result;
            selectionMade = true;
         }

      }
      stage.close();
   }

   public StatsProject getSelectedItem() {
      return selectedItem;
   }

   public boolean isSelectionMade() {
      return selectionMade;
   }
}
