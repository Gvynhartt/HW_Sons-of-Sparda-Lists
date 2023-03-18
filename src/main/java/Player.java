public class Player {
    private int playerId;
    private String playerName;
    private int playerStrength;

    public Player() {
    }

    public Player(int playerId, String playerName, int playerStrength) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerStrength = playerStrength;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerStrength() {
        return playerStrength;
    }

    public void setPlayerStrength(int playerStrength) {
        this.playerStrength = playerStrength;
    }
}
