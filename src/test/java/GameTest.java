import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameTest {
    Game ofThrones = new Game();

    Player player1 = new Player(1, "abobus3450d", 300);
    Player player2 = new Player(2, "v4duhWin", 9001);
    Player player3 = new Player(3, "BibaSenior72cards", 5928);
    Player player4 = new Player(4, "BibaJunior8bit", 404);
    Player player5 = new Player(5, "zurin1825", 1917);
    Player player6 = new Player(6, "zurin1825", 1877);
    Player player7 = new Player(7, "Ku6ePnoQ_u3_NAV1", 404);

    @Test
    public void shdRegisterPlayerNormal() { /** обычное добавление массива в пустую карту */
        ofThrones.registerForTournmt(player1);

        Player expected = player1;
        Player actual = ofThrones.getReggedPlayers().get("abobus3450d");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdRegisterPlayerIfDuplicate() { /** проверяет, какой длины карта, если добавляемый объект уже есть в списке */
        ofThrones.registerForTournmt(player1);
        ofThrones.registerForTournmt(player1);

        int expected = 1;
        int actual = ofThrones.getReggedPlayers().size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdFindPlayerByNameNormal() { /** обычная работа метода поиска по имени */
        ofThrones.registerForTournmt(player1);
        ofThrones.registerForTournmt(player2);
        ofThrones.registerForTournmt(player3);

        Player expected = player2;
        Player actual = ofThrones.findPlayerByRegKey("v4duhWin");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdFindPlayerByNameIfNonexistent() { /** если имя для поиска отсутствует */
        ofThrones.registerForTournmt(player1);
        ofThrones.registerForTournmt(player2);
        ofThrones.registerForTournmt(player3);

        Player expected = null;
        Player actual = ofThrones.findPlayerByRegKey("semen1984stalnoj");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdFindPlayerByNameIfDuplicateNames() { /** проверяет поиск, если есть два игрока с одинаковыми именами, лол */
        ofThrones.registerForTournmt(player5);
        ofThrones.registerForTournmt(player6);

        Player expected = player5;
        Player actual = ofThrones.findPlayerByRegKey("zurin1825");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdThrowExceptForPlayer1() { /** проверка исключения для 1 игрока */
        ofThrones.registerForTournmt(player3);
        ofThrones.registerForTournmt(player4);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            ofThrones.tournmtRound("v4duhWin", "BibaSenior72cards");
        });
    }

    @Test
    public void shdThrowExceptForPlayer2() { /** для 2 игрока */
        ofThrones.registerForTournmt(player3);
        ofThrones.registerForTournmt(player4);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            ofThrones.tournmtRound("BibaSenior72cards", "zurin1825");
        });
    }

    @Test
    public void shdThrowExceptForBothPlayers() { /** для обоих */
        ofThrones.registerForTournmt(player3);
        ofThrones.registerForTournmt(player4);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            ofThrones.tournmtRound("abobus3450d", "zurin1825");
        });
    }

    @Test
    public void shdDefineRoundWinnerForPlayer1() { /** проверяет метод выявления победителя, если сильнее 1 игрок */
        ofThrones.registerForTournmt(player3);
        ofThrones.registerForTournmt(player4);

        int expected = 1;
        int actual = ofThrones.tournmtRound("BibaSenior72cards", "BibaJunior8bit");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdDefineRoundWinnerForPlayer2() { /** если сильнее 2 игрок */
        ofThrones.registerForTournmt(player3);
        ofThrones.registerForTournmt(player4);

        int expected = 2;
        int actual = ofThrones.tournmtRound("BibaJunior8bit", "BibaSenior72cards");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdDefineRoundWinnerIfDraw() { /** если игроки равны по силе */
        ofThrones.registerForTournmt(player4);
        ofThrones.registerForTournmt(player7);

        int expected = 0;
        int actual = ofThrones.tournmtRound("BibaJunior8bit", "Ku6ePnoQ_u3_NAV1");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdDefineRoundWinnerIfDuplicateNames() { /** для тех же дублирующихся имён */
        ofThrones.registerForTournmt(player5);
        ofThrones.registerForTournmt(player6);

        int expected = 0;
        int actual = ofThrones.tournmtRound("zurin1825", "zurin1825"); /** т.е. получается, что игрок сравнивается сам с собой */

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdDefineRoundWinnerIfOneUnregistered() { /** если один из игроков не зарегистрирован */
        ofThrones.registerForTournmt(player1);
        ofThrones.registerForTournmt(player3);

        Assertions.assertThrows(NotRegisteredException.class, () -> {ofThrones.tournmtRound("abobus3450d", "kiktorVislyj1964");});
    }
}
