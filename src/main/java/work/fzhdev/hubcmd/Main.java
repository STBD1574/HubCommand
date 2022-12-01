package work.fzhdev.hubcmd;


import com.nukkitx.protocol.bedrock.packet.TextPacket;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.plugin.Plugin;
import work.fzhdev.hubcmd.Command.*;

public class Main extends Plugin {

    private static Main plugin = null;

    @Override
    public void onEnable () {
        if (plugin == null) plugin = this;
        if (getResourceFile("config.yml") == null) saveResource("config.yml");
        this.loadConfig();
        getLogger().info("插件加载成功!");
        //注册命令
        getProxy().getCommandMap().registerCommand(new HubCmd());
        getProxy().getCommandMap().registerCommand(new LobbyCmd());
        getProxy().getCommandMap().registerCommand(new LCmd());
    }

    public static void BackLobby(ProxiedPlayer player) {
        ServerInfo info = Main.getInstance().getProxy().getServerInfo(Main.getInstance().getConfig().getString("Server"));
        if (info.getServerName() == player.getServerInfo().getServerName()) {
            return;
        }
        sendMessage(player,"§a匹配服务器中");
        sendMessage(player,"§a正在将你从 " + player.getServerInfo().getServerName() + " 转移到 " + info.getServerName());
        player.connect(info);
    }

    public static Main getInstance() {
        return plugin;
    }

    public static void sendMessage(ProxiedPlayer player,String message) {
        if (!message.trim().isEmpty()) {
            TextPacket packet = new TextPacket();
            packet.setType(TextPacket.Type.RAW);
            packet.setXuid(player.getXuid());
            packet.setMessage(message);
            player.sendPacket(packet);
        }
    }
}
