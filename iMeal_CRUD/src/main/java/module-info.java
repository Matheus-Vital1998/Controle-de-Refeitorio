module br.edu.cefsa.imeal_crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens br.edu.cefsa.imeal_crud to javafx.fxml;
    exports br.edu.cefsa.imeal_crud;
}
