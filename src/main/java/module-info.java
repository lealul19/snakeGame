module com.htlshkoder.sew_projekt_snakegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.htlshkoder.sew_projekt_snakegame to javafx.fxml;
    exports com.htlshkoder.sew_projekt_snakegame;
}