package ee.taltech.survivors.account;

import static ee.taltech.survivors.helper.constants.AssetsFilepaths.PLAYER_NAME;

public class Account {
    private static Account INSTANCE;

    private String playerName;
    private String login;
    private String password;

    private Account() {
        playerName = PLAYER_NAME;
    }

    public static synchronized Account getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Account();
        }
        return INSTANCE;
    }

    public void createPlayerCharacter() {
    }

    public String getPlayerName() {
        return playerName;
    }
}
