import logic.Battleship;
import logic.TheGame;
import logic.exceptions.XmlContentException;
import module.BattleShipGameType;
import module.PositionType;
import module.ShipType;
import module.ShipTypeType;
import org.junit.jupiter.api.Test;
import ui.verifiers.ErrorCollector;
import ui.verifiers.XmlFileVerifier;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by barakm on 27/07/2017
 */
public class XmlFileTests {

    private static final String RELATIVE_PATH = "module/battleshipx.xml";
    private final ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void loadEmptyFile() {
        boolean actual = XmlFileVerifier.isFileOk("", errorCollector);

        assertEquals(false, actual);
    }

    @Test
    public void verifyFileIsNotXml() {
        boolean actual = XmlFileVerifier.isFileOk("bla.bla", errorCollector);

        assertEquals(false, actual);
    }

    @Test
    public void verifyFileIsXml() {
        boolean actual = XmlFileVerifier.isFileOk("bla.xml", errorCollector);

        assertEquals(true, actual);
    }

    @Test
    public void verifyBoardsSize() throws JAXBException {
        BattleShipGameType xmlContent = new BattleShipGameType();

        xmlContent.setBoardSize("5");
        boolean actual = XmlFileVerifier.isFileContentOK(xmlContent, errorCollector);

        assertEquals(true, actual);

        xmlContent.setBoardSize("20");
        actual = XmlFileVerifier.isFileContentOK(xmlContent, errorCollector);

        assertEquals(true, actual);

        xmlContent.setBoardSize("4");
        actual = XmlFileVerifier.isFileContentOK(xmlContent, errorCollector);

        assertEquals(false, actual);

        xmlContent.setBoardSize("21");
        actual = XmlFileVerifier.isFileContentOK(xmlContent, errorCollector);

        assertEquals(false, actual);
    }

    @Test
    public void verifyFileNotExist() {
        boolean actual;
        TheGame theGame = new TheGame();

        try {
            theGame.loadFile("stamPath.xml", errorCollector);
            actual = false;
        } catch (XmlContentException e) {
            actual = true;
        }

        assertEquals(true, actual);
    }

    @Test
    public void verifyFileExist() {
        boolean actual;
        TheGame theGame = new TheGame();

        try {
            theGame.loadFile(RELATIVE_PATH, errorCollector);
            actual = true;
        } catch (XmlContentException e) {
            actual = false;
        }

        assertEquals(true, actual);
    }

    @Test
    public void verifyGameIsOn() {
        boolean actual;
        TheGame theGame = new TheGame();

        try {
            theGame.loadFile(RELATIVE_PATH, errorCollector);
            theGame.init();
        } catch (XmlContentException e) {
            e.printStackTrace();
        }

        //Try to load another file after start game.
        try {
            if (theGame.loadFile(RELATIVE_PATH, errorCollector)) {
                actual = false;
            } else {
                actual = true;
            }
        } catch (XmlContentException e) {
            actual = true;
        }

        assertEquals(true, actual);
    }

    @Test
    public void verifyButtleshipAmountIsNotOk() {
        boolean actual;
        List<Battleship> battleships = new ArrayList<>();
        Map<String, ShipTypeType> map = new HashMap<>();

        ShipTypeType shipTypeType = new ShipTypeType();
        ShipType shipType = new ShipType();

        shipTypeType.setAmount("2");
        shipTypeType.setId("1");

        PositionType positionType1 = new PositionType();
        PositionType positionType2 = new PositionType();

        positionType1.setX("1");
        positionType1.setY("1");

        positionType2.setX("3");
        positionType2.setY("3");

        shipType.setShipTypeId("1");
        shipType.setPosition(positionType1);

        shipType.setShipTypeId("1");
        shipType.setPosition(positionType2);

        battleships.add(new Battleship(shipTypeType, shipType));

        map.put("1", shipTypeType);

        actual = XmlFileVerifier.isBattleshipAmountOk(battleships, map);

        assertEquals(true, actual);
    }
}
