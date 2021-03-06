/*
 * Jan Kampherbeek, (c) 2020.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.ui.screens;

import com.radixpro.enigma.Rosetta;
import com.radixpro.enigma.domain.stats.DataFileDescription;
import com.radixpro.enigma.shared.Property;
import com.radixpro.enigma.ui.creators.*;
import com.radixpro.enigma.ui.screens.blocks.StatsProjBlock;
import com.radixpro.enigma.xchg.api.PersistedPropertyApi;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import static com.radixpro.enigma.ui.shared.UiDictionary.*;

public class StatsStart {

   private static final double PROJ_HEIGHT = 200.0;
   private static final String KEY_PROJDIR = "projdir";
   private final PersistedPropertyApi propApi;
   private final DirectoryChooser dirChooser;
   private final StatsProjBlock projBlock;
   private final StatsProjSearch projSearch;
   private final StatsDataNew dataNew;
   private final StatsDataSearch dataSearch;
   private boolean projDirDefined;
   private String fullPathProjDir;
   private Stage stage;
   private Label lblPageTitle;
   private Label lblSubTitleNoProjdir;
   private Label lblSubTitleData;
   private Label lblProjDir;
   private Label lblProjDirValue;
   private Label lblDefineProjDir;
   private Button btnDefineProjDir;
   private Button btnInputData;
   private Button btnDataDetails;
   private Button btnDataNew;
   private Button btnDataSearch;
   private Pane panePageTitle;
   private Pane paneProjects;
   private Pane paneBtnBar;
   private Pane paneProjDir;
   private Pane paneSubTitleData;
   private Pane paneBtnBarData;
   private TableView tvDataFiles;
   private DataFileDescription dataFileDescription;

   public StatsStart(@NotNull final StatsDataNew dataNew,
                     @NotNull final StatsDataSearch dataSearch,
                     @NotNull final StatsProjBlock projBlock,
                     @NotNull final StatsProjSearch projSearch,
                     @NotNull final PersistedPropertyApi propApi,
                     @NotNull final DirectoryChooser dirChooser) {
      this.projBlock = projBlock;
      this.projSearch = projSearch;
      this.propApi = propApi;
      this.dirChooser = dirChooser;
      this.dataNew = dataNew;
      this.dataSearch = dataSearch;
   }

   public void show() {
      stage = new Stage();
      stage.setWidth(START_WIDTH);
      List<Property> properties = propApi.read(KEY_PROJDIR);
      if (0 == properties.size()) {
         fullPathProjDir = "";
         projDirDefined = false;
      } else {
         fullPathProjDir = properties.get(0).getValue();
         projDirDefined = true;
      }
      defineLeafs();
      definePanes();
      defineStructure();

      if (projDirDefined) stage.setScene(new Scene(createVBox()));
      else stage.setScene(new Scene(createVBoxDefineProjDir()));
      stage.showAndWait();
   }

   private void defineLeafs() {
      lblPageTitle = new LabelBuilder("ui.stats.start.pagetitle").setStyleClass("titletext").setPrefWidth(START_WIDTH).build();
      lblSubTitleNoProjdir = new LabelBuilder("ui.stats.start.noprojdirtitle").setStyleClass("subtitletext").setPrefWidth(START_WIDTH).build();
      lblDefineProjDir = new LabelBuilder("ui.stats.start.noprojdirtext").setPrefWidth(START_WIDTH).build();
      lblProjDir = new LabelBuilder("ui.stats.start.projdir").setPrefWidth(200.0).build();
      lblProjDirValue = new LabelBuilder("").setPrefWidth(300.0).setText(fullPathProjDir).build();
      btnDefineProjDir = new ButtonBuilder("ui.stats.start.noprojdirbtn").setDisabled(false).build();
      btnInputData = new ButtonBuilder("ui.stats.start.inputdatabtn").setDisabled(false).build();
      lblSubTitleData = new LabelBuilder("ui.stats.start.datasubtitle").setPrefWidth(START_WIDTH).setPrefHeight(SUBTITLE_HEIGHT).
            setStyleClass("subtitletext").build();
      tvDataFiles = new TableViewBuilder().setPrefWidth(START_WIDTH).setPrefHeight(TV_HEIGHT).build();
      tvDataFiles.setPlaceholder(new Label(Rosetta.getText("ui.stats.placeholder.datafiles")));
      TableColumn colName = new TableColumn<>(Rosetta.getText("ui.general.name"));
      TableColumn colDescr = new TableColumn<>(Rosetta.getText("ui.general.description"));
      colName.setCellValueFactory(new PropertyValueFactory("name"));
      colDescr.setCellValueFactory(new PropertyValueFactory("description"));
      colName.setPrefWidth(300.0);
      colDescr.setPrefWidth(300.0);
      tvDataFiles.getColumns().add(colName);
      tvDataFiles.getColumns().add(colDescr);
   }

   private void definePanes() {
      panePageTitle = new PaneBuilder().setHeight(TITLE_HEIGHT).setWidth(START_WIDTH).setStyleClass("titlepane").build();
      paneProjects = new PaneBuilder().setHeight(PROJ_HEIGHT).setWidth(START_WIDTH).build();
      paneBtnBar = new PaneBuilder().setHeight(BUTTONBAR_HEIGHT).setWidth(START_WIDTH).setChildren(createBtnBar()).build();
      paneProjDir = createPaneProjDir();
      paneSubTitleData = new PaneBuilder().setHeight(SUBTITLE_HEIGHT).setWidth(START_WIDTH).setStyleClass(STYLE_SUBTITLE_PANE).setChildren(lblSubTitleData).build();
      paneBtnBarData = new PaneBuilder().setHeight(BUTTONBAR_HEIGHT).setWidth(START_WIDTH).setChildren(createBtnBarData()).build();
   }

   private Pane createPaneProjDir() {
      HBox hBox = new HBoxBuilder().setPrefWidth(START_WIDTH).setChildren(lblProjDir, lblProjDirValue).build();
      return new PaneBuilder().setHeight(40.0).setWidth(START_WIDTH).setChildren(hBox).build();

   }

   private void defineStructure() {
      btnDefineProjDir.setOnAction(click -> onDefineProjDir());

      panePageTitle.getChildren().add(lblPageTitle);
      if (fullPathProjDir.isBlank()) {
//         paneSubTitleProjects.getChildren().add(lblSubTitleNoProjdir);
         paneProjects.getChildren().add(createVBoxDefineProjDir());
      } else {
//         paneSubTitleProjects.getChildren().add(lblSubTitleProjects);
         // define PaneProjects with overview of projects
      }

   }

   private VBox createVBoxDefineProjDir() {
      return new VBoxBuilder().setPadding(GAP).setWidth(START_WIDTH).setChildren(lblDefineProjDir, btnDefineProjDir).build();
   }

   private VBox createVBox() {
      return new VBoxBuilder().setWidth(START_WIDTH).setChildren(createMenuBar(), panePageTitle, paneProjDir, paneSubTitleData, tvDataFiles, paneBtnBarData, projBlock.getVBox(), paneBtnBar).build();
   }

   private MenuBar createMenuBar() {
      Menu menuGeneral = new Menu(Rosetta.getText("menu.general"));
      MenuItem miExit = new MenuItem(Rosetta.getText("menu.general.exit"));
      miExit.setOnAction(e -> stage.close());
      menuGeneral.getItems().add(miExit);
      Menu menuData = new Menu(Rosetta.getText("menu.stats.data"));
      MenuItem miNewData = new MenuItem(Rosetta.getText("menu.stats.data.new"));
      miNewData.setOnAction(click -> onNew());
      MenuItem miSearchData = new MenuItem(Rosetta.getText("menu.stats.data.search"));
      miSearchData.setOnAction((click -> onDataSearch()));
      MenuItem miDetailsData = new MenuItem(Rosetta.getText("menu.stats.data.details"));
      menuData.getItems().addAll(miNewData, miSearchData, miDetailsData);
      Menu menuProjects = new Menu(Rosetta.getText("menu.stats.projects"));
      MenuItem miNewProject = new MenuItem(Rosetta.getText("menu.stats.projects.new"));
      MenuItem miSearchProject = new MenuItem(Rosetta.getText("menu.stats.projects.search"));
      MenuItem miEditProject = new MenuItem(Rosetta.getText("menu.stats.projects.edit"));
      MenuItem miOpenProject = new MenuItem(Rosetta.getText("menu.stats.projects.open"));
      menuProjects.getItems().addAll(miNewProject, miSearchProject, miEditProject, miOpenProject);
      Menu menuHelp = new Menu(Rosetta.getText("menu.general.help"));
      MenuItem miShowHelp = new MenuItem(Rosetta.getText("menu.general.help.showhelp"));
      menuHelp.getItems().add(miShowHelp);
      MenuBar menuBar = new MenuBar();
      menuBar.getMenus().addAll(menuGeneral, menuData, menuProjects, menuHelp);
      return menuBar;
   }

   private ButtonBar createBtnBar() {
      Button btnHelp = new ButtonBuilder("ui.shared.btn.help").setDisabled(false).build();
      Button btnExit = new ButtonBuilder("ui.shared.btn.exit").setDisabled(false).build();
      return new ButtonBarBuilder().setButtons(btnHelp, btnExit).build();
   }

   private ButtonBar createBtnBarData() {
      btnDataDetails = new ButtonBuilder("ui.stats.start.btndatadetails").setDisabled(true).build();
      btnDataNew = new ButtonBuilder("ui.stats.start.btndatanew").setDisabled(false).build();
      btnDataSearch = new ButtonBuilder("ui.stats.start.btndatasearch").setDisabled(false).build();
      btnDataSearch.setOnAction(click -> onDataSearch());
      btnDataNew.setOnAction(click -> onNew());
      return new ButtonBarBuilder().setButtons(btnDataDetails, btnDataNew, btnDataSearch).build();
   }

   private void onDefineProjDir() {
      File selectedDirectory = dirChooser.showDialog(stage);
      if (null != selectedDirectory) {
         fullPathProjDir = selectedDirectory.getAbsolutePath();
         Property prop = new Property(KEY_PROJDIR, fullPathProjDir);
         propApi.insert(prop);
         stage.close();
      }

   }

   private String defineProjDir() {
      File selectedDirectory = dirChooser.showDialog(stage);
      if (null != selectedDirectory) return selectedDirectory.getAbsolutePath();
      return "";
   }

   public void onNew() {
      dataNew.show();
   }

   private void onProjSearch() {
      projSearch.show();
   }

   public void onDataSearch() {
      dataSearch.show();
      if (dataSearch.isSelectionMade()) {
         dataFileDescription = dataSearch.getSelectedItem();
         tvDataFiles.getItems().add(dataFileDescription);
      }
   }
}
