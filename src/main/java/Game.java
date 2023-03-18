import java.util.ArrayList;

public class Game {
    private ArrayList<Player> reggedPlayers = new ArrayList<>();

    public ArrayList<Player> getReggedPlayers() {
        return reggedPlayers;
    }

    public void registerForTournmt(Player newPlayer) { /** метод решистрации с защитой от дублирования объектов игроков */
        if (reggedPlayers.contains(newPlayer) == false) {
            reggedPlayers.add(newPlayer);
        }
    }

    public Player findPlayerByRegName(String targetName) { /** отдельный метод для поиска игрока по имени */
        Player resultPlayer = new Player();
        resultPlayer = null;
        ArrayList<Player> playerDatabase = getReggedPlayers();

        for (Player targetPlayer : playerDatabase) {
            if (targetPlayer.getPlayerName() == targetName) {
                resultPlayer = targetPlayer;
            }
        }
        return resultPlayer;
    }

    public int tournmtRound(String playerName1, String playerName2) throws NotRegisteredException { /** метод, выявляющий исход турнира */
        Player opponent1 = findPlayerByRegName(playerName1);
        Player opponent2 = findPlayerByRegName(playerName2);

        /** Сперва делается проверка, зарегистрированы ли участники */

        if (opponent1 == null) { /** способ поумнее */
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
