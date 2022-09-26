module com.example.dariiarusbackjava {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.dariiarusbackjava to javafx.fxml;
    exports com.example.dariiarusbackjava;
}