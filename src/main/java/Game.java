import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Game {
    private HashMap<String, Player> reggedPlayers = new HashMap<>();

    /**
     * Я решил, что в ключ для каждого игрока будет дублироваться его имя
     */

    public HashMap<String, Player> getReggedPlayers() {
        return reggedPlayers;
    }

    public void registerForTournmt(Player newPlayerValue) { /** метод регистрации с защитой от дублирования игроков */
        String newPlayerKey = newPlayerValue.getPlayerName();
        if (reggedPlayers.containsValue(newPlayerValue) == false && reggedPlayers.containsKey(newPlayerKey) == false) {
            reggedPlayers.put(newPlayerKey, newPlayerValue);
        } /** Т.е. сделано так, чтоб не дублировались ни ключи, ни значения */
    }

    public Player findPlayerByRegKey(String targetKey) { /** в этом задании мы меняем метод поиска: теперь ищется не значение имени игрока, а ключ */
        Player resultPlayer = new Player();
        resultPlayer = null;
        HashMap<String, Player> playerDatabase = getReggedPlayers();

        if (playerDatabase.containsKey(targetKey) == true) {
            resultPlayer = playerDatabase.get(targetKey);
        }
        return resultPlayer;
    }

    public int tournmtRound(String playerName1, String playerName2) throws NotRegisteredException { /** метод, выявляющий исход турнира */
        Player opponent1 = findPlayerByRegKey(playerName1);
        Player opponent2 = findPlayerByRegKey(playerName2);

        /** Сперва делается проверка, зарегистрированы ли участники */

        if (opponent1 == null) {
            throw new NotRegisteredException("Пользователь с именем " + playerName1 + " не зарегистрирован");
        } else if (opponent2 == null) {
            throw new NotRegisteredException("Пользователь с именем " + playerName2 + " не зарегистрирован");
        }

        /** Собственно проверка, у кого из оппонентов MORE POWER */

        int roundResult = 0; /** здесь мы сохраняем результат сравнения */

        if (opponent1.getPlayerStrength() > opponent2.getPlayerStrength()) {
            roundResult = 1;
        } else if (opponent2.getPlayerStrength() > opponent1.getPlayerStrength()) {
            roundResult = 2;
        } else {
            roundResult = 0;
        }
        return roundResult;
    }
}
