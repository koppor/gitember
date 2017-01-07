package com.az.gitember;

import com.az.gitember.misc.Const;
import com.az.gitember.scm.impl.git.GitRepositoryService;
import com.az.gitember.service.GitemberServiceImpl;
import com.az.gitember.service.SettingsServiceImpl;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainApp extends Application {

    private static Stage mainStage = null;

    public static StringProperty currentRepositoryPath = new SimpleStringProperty();
    public static StringProperty remoteUrl = new SimpleStringProperty();

    private static GitRepositoryService repositoryService = new GitRepositoryService();
    private static SettingsServiceImpl settingsService = new SettingsServiceImpl();
    private static GitemberServiceImpl gitemberService;



    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setCurrentRepositoryPath(String currentRepositoryPath) {
        MainApp.currentRepositoryPath.setValue(currentRepositoryPath);
    }


    public static String getCurrentRepositoryPathWOGit() {
        if(currentRepositoryPath != null) {
            return currentRepositoryPath.getValue().substring(
                    0,
                    currentRepositoryPath.getValue().indexOf(Const.GIT_FOLDER) - 1);
        }
        return null;
    }

    public static GitRepositoryService getRepositoryService() {
        return repositoryService;
    }

    public static void setRepositoryService(GitRepositoryService repositoryService) {
        MainApp.repositoryService = repositoryService;
        MainApp.remoteUrl.setValue(repositoryService.getRemoteUrl());
    }

    public static GitemberServiceImpl getGitemberService() {
        return gitemberService;
    }

    public static void setGitemberService(GitemberServiceImpl gitemberService) {
        MainApp.gitemberService = gitemberService;
    }

    public static void  setTitle(String title) {
        mainStage.setTitle(title);
    }

    public static SettingsServiceImpl getSettingsService() {
        return settingsService;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Const.DEFAULT_CSS);
        mainStage = stage;
        setTitle(Const.TITLE);
        stage.setScene(scene);
        stage.getIcons().add(new Image(MainApp.class.getResourceAsStream(Const.ICON)));
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
