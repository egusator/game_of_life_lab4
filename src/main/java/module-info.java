module com.example.game_of_life_lab4 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.game_of_life_lab4 to javafx.fxml;
    exports com.example.game_of_life_lab4;
}