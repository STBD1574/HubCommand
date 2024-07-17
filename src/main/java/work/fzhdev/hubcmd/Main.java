package work.fzhdev.hubcmd;

import dev.waterdog.waterdogpe.plugin.Plugin;

import java.util.Arrays;
import java.util.Collections;

public class Main extends Plugin {
    private static Main instance = null;

    @Override
    public void onEnable () {
        instance = this;

        if (getResourceFile("config.yml") == null) {
            this.saveResource("config.yml");
        }
        this.loadConfig();

        this.getProxy().getCommandMap().registerCommand(new HubCommand(
                this.getConfig().getString("name"),
                this.getConfig().getString("description"),
                this.getConfig().getString("usageMessage"),
                this.getConfig().getStringList("aliases"),
                this.getConfig().getStringList("servers"),
                this.getConfig().getStringList("messages")
        ));

        this.getLogger().info("plugin load!");
    }

    public static Main getInstance() {
        return instance;
    }
}
