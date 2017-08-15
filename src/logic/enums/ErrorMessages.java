package logic.enums;

/**
 * Created by xozh4v on 8/9/2017.
 */
public enum ErrorMessages {

    DUPLICATE_SHIP_ID("The battleship ID name must be unique!"),
    UNKNOWN_SHIP_ID("One or more of battleships' ID's is unknown!"),
    SHIP_MISSMATCH("The amount of the placed battleships doesn't match to the definition!"),
    ILLEGAL_POSITION("The battleships' positions are illegal! Please check your boards."),
    XML_NOT_EXISTS("XML file does not exists!"),
    EMPTY_FILE_NAME("File name can not be empty"),
    INVALID_XML("Invalid XML file"),
    BOARD_SIZE("Board size must be between 5 to 20"),
    GAME_IS_ALREADY_ON("You can't load a XML file while a game is already running."),
    XML_PREFIX("You must enter an xml file");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

}