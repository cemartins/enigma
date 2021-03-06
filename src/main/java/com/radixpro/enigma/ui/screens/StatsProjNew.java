/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.ui.screens;

import com.radixpro.enigma.Rosetta;
import com.radixpro.enigma.domain.config.BaseAstronConfig;
import com.radixpro.enigma.domain.stats.DataFileDescription;
import com.radixpro.enigma.domain.stats.StatsProject;
import com.radixpro.enigma.references.Ayanamshas;
import com.radixpro.enigma.references.EclipticProjections;
import com.radixpro.enigma.references.HouseSystems;
import com.radixpro.enigma.references.ObserverPositions;
import com.radixpro.enigma.ui.creators.*;
import com.radixpro.enigma.ui.screens.blocks.BaseConfigInputBlock;
import com.radixpro.enigma.ui.screens.blocks.NameDescriptionInputBlock;
import com.radixpro.enigma.xchg.api.StatsProjApi;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.radixpro.enigma.ui.shared.UiDictionary.*;

/**
 * Input screen for new projects for statistics.
 */
public class StatsProjNew extends InputScreen {

   private static final double HEIGHT = 800.0;
   private final NameDescriptionInputBlock nameDescrBlock;
   private final BaseConfigInputBlock configBlock;
   private final StatsDataSearch dataSearch;
   private Label lblSubTitleDataFiles;
   private Pane paneSubTitleData;
   private Pane paneDataFiles;
   private TableView tvDataFiles;
   private DataFileDescription dataFileDescription;
   private final StatsProjApi statsProjApi;
   private Button btnHelp;
   private Button btnCancel;
   private Button btnSave;
   private TableColumn<DataFileDescription, String> colName;
   private TableColumn<DataFileDescription, String> colDescr;
   private ObservableList<DataFileDescription> selectedDataFiles;
   private Button btnRemove;

   public StatsProjNew(@NotNull final NameDescriptionInputBlock nameDescrBlock,
                       @NotNull final BaseConfigInputBlock configBlock,
                       @NotNull final StatsDataSearch dataSearch,
                       @NotNull final StatsProjApi statsProjApi) {
      super();
      this.nameDescrBlock = nameDescrBlock;
      this.configBlock = configBlock;
      this.dataSearch = dataSearch;
      this.statsProjApi = statsProjApi;
   }

   public void show() {
      initialize();
      stage.setScene(new Scene(createVBox()));
      stage.showAndWait();
   }

   @Override
   public void checkStatus() {
      btnSave.setDisable(null == nameDescrBlock.getName() || nameDescrBlock.getName().isBlank() ||
            null == nameDescrBlock.getDescr() || nameDescrBlock.getDescr().isBlank() || tvDataFiles.getItems().isEmpty());
      btnRemove.setDisable(tvDataFiles.getSelectionModel().getSelectedIndex() == -1);
   }

   private void initialize() {
      defineLeafs();
      createTableView();
      definePanes();
   }

   private void defineLeafs() {
      lblSubTitleDataFiles = new LabelBuilder("ui.stats.datafiles.subtitle").setPrefWidth(INPUT_WIDTH).setPrefHeight(SUBTITLE_HEIGHT).
            setStyleClass("subtitletext").build();
   }

   private void definePanes() {
      paneSubTitleData = new PaneBuilder().setHeight(SUBTITLE_HEIGHT).setWidth(INPUT_WIDTH).setStyleClass(STYLE_SUBTITLE_PANE).
            setChildren(lblSubTitleDataFiles).build();
      paneDataFiles = new PaneBuilder().setHeight(120).setWidth(INPUT_WIDTH).setChildren(tvDataFiles).build();
   }

   private Pane createPaneTitle() {
      Label label = new LabelBuilder("ui.stats.newproj.title").setPrefWidth(INPUT_WIDTH).setPrefHeight(TITLE_HEIGHT).setStyleClass("titletext").build();
      return new PaneBuilder().setWidth(INPUT_WIDTH).setHeight(TITLE_HEIGHT).setStyleClass("titlepane").setChildren(label).build();
   }


   private VBox createVBox() {
      return new VBoxBuilder().setWidth(INPUT_WIDTH).setHeight(650.0).setChildren(
            createPaneTitle(),
            nameDescrBlock.getGridPane(this),
            configBlock.getGridPane(),
            createVBoxData(),
            createPaneBtnBar()
      ).build();
   }

   private VBox createVBoxData() {
      return new VBoxBuilder().setWidth(INPUT_WIDTH).setHeight(230.0).setChildren(
            paneSubTitleData,
            paneDataFiles,
            createBtnBarData()).build();
   }

   @Override
   protected ButtonBar createBtnBar() {
      btnHelp = new ButtonBuilder("ui.shared.btn.help").setDisabled(false).build();
      btnCancel = new ButtonBuilder("ui.shared.btn.cancel").setDisabled(false).build();
      btnSave = new ButtonBuilder("ui.shared.btn.save").setDisabled(true).build();
      btnSave.setOnAction(e -> onSave());
      return new ButtonBarBuilder().setButtons(btnHelp, btnCancel, btnSave).build();
   }

   private ButtonBar createBtnBarData() {
      Button searchButton = new ButtonBuilder("ui.stats.datafiles.btnsearch").setDisabled(false).build();
      btnRemove = new ButtonBuilder("ui.stats.datafiles.btnremove").setDisabled(true).build();
      searchButton.setOnAction(e -> onSearch());
      btnRemove.setOnAction(e -> onRemove());
      return new ButtonBarBuilder().setButtons(searchButton, btnRemove).build();
   }

   private void onSearch() {
      dataSearch.show();
      if (dataSearch.isSelectionMade()) {
         this.dataFileDescription = dataSearch.getSelectedItem();
         tvDataFiles.getItems().add(dataFileDescription);

      }
      checkStatus();
   }

   private void createTableView() {
      tvDataFiles = new TableViewBuilder().setPrefWidth(INPUT_WIDTH).setPrefHeight(120).build();
      tvDataFiles.setPlaceholder(new Label(Rosetta.getText("ui.stats.placeholder.datafiles")));
      colName = new TableColumn<>(Rosetta.getText("ui.general.name"));
      colDescr = new TableColumn<>(Rosetta.getText("ui.general.description"));
      colName.setCellValueFactory(new PropertyValueFactory("name"));
      colDescr.setCellValueFactory(new PropertyValueFactory("description"));
      colName.setPrefWidth(300.0);
      colDescr.setPrefWidth(300.0);
      tvDataFiles.getColumns().add(colName);
      tvDataFiles.getColumns().add(colDescr);

      TableView.TableViewSelectionModel<DataFileDescription> selectionModel = tvDataFiles.getSelectionModel();
      selectionModel.setSelectionMode(SelectionMode.SINGLE);
      selectedDataFiles = selectionModel.getSelectedItems();
      selectedDataFiles.addListener((ListChangeListener<DataFileDescription>) change -> onSelectFile());
   }

   private void onRemove() {
      int id = tvDataFiles.getSelectionModel().getSelectedIndex();
      tvDataFiles.getItems().remove(id);
      checkStatus();
   }

   private void onSelectFile() {
      checkStatus();
   }

   private void onSave() {
      statsProjApi.saveProject(createProject());
   }

   private StatsProject createProject() {
      final String name = nameDescrBlock.getName();
      final String descr = nameDescrBlock.getDescr();
      final List<DataFileDescription> dataFiles = new ArrayList<>();
      final ObservableList items = tvDataFiles.getItems();
      for (Object obj : tvDataFiles.getItems()) {
         dataFiles.add((DataFileDescription) obj);
      }
      HouseSystems houseSystem = configBlock.getHouseSystem();
      Ayanamshas ayanamsha = configBlock.getAyanamsha();
      ObserverPositions obsPos = configBlock.getObserverPosition();
      EclipticProjections eclProj = configBlock.getEclipticProjection();
      BaseAstronConfig config = new BaseAstronConfig(houseSystem, ayanamsha, eclProj, obsPos);
      return new StatsProject(name, descr, config, dataFiles);


   }

}
