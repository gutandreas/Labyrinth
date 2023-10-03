module edu.andreasgut.labyrinth {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.andreasgut.labyrinth to javafx.fxml;
    opens edu.andreasgut.labyrinth.controller to javafx.fxml;
    exports edu.andreasgut.labyrinth;
    exports edu.andreasgut.labyrinth.controller;
    exports edu.andreasgut.labyrinth.core;
    opens edu.andreasgut.labyrinth.core to javafx.fxml;

}