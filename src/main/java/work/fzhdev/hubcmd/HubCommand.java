package work.fzhdev.hubcmd;

import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

import java.util.List;

public class HubCommand extends Command {
    List<String> servers, messages;

    public HubCommand(String name, String description, String usageMessage, List<String> aliases, List<String> servers, List<String> messages) {
        super(name, CommandSettings.builder()
                .setDescription(description)
                .setUsageMessage(usageMessage)
                .setAliases(aliases.toArray(new String[0]))
                .build()
        );

        this.servers = servers;
        this.messages = messages;
    }

    public boolean transfer(ProxiedPlayer player, String serverName) {
        ServerInfo info = ProxyServer.getInstance().getServerInfo(serverName);
        if (info == null || player.getServerInfo().getServerName().equals(serverName)) {
            return false;
        }

        player.connect(info);
        return player.getServerInfo().getServerName().equals(serverName);
    }

    @Override
    public boolean onExecute(CommandSender sender, String alias, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return false;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length > 0) {
            return this.transfer(player, args[0]);
        }

        for (String server : servers) {
            if (this.transfer(player, server)) {
                return true;
            }
        }
        return false;
    }
}