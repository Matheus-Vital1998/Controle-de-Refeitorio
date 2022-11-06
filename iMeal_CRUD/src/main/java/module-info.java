module br.edu.cefsa.imeal_crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens br.edu.cefsa.imeal_crud to javafx.fxml;
    exports br.edu.cefsa.imeal_crud;
}
