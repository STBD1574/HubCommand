package work.fzhdev.hubcmd.Command;

import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import work.fzhdev.hubcmd.Main;

public class LobbyCmd extends Command {

    public LobbyCmd() {
        super("lobby", CommandSettings.builder()
                .setDescription("Back to lobby")
                .setPermission("work.fzhdev.hubcmd")
                .setUsageMessage("/lobby").build());
    }

    @Override
    public boolean onExecute(CommandSender sender, String alias, String[] args) {
        Main.BackLobby((ProxiedPlayer)sender);
        return true;
    }
}