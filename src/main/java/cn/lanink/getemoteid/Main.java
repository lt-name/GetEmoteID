package cn.lanink.getemoteid;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.EmoteListPacket;
import cn.nukkit.network.protocol.EmotePacket;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.ArrayList;

/**
 * @author lt_name
 */
public class Main extends PluginBase implements Listener {

    private Config log;
    private final ArrayList<String> emoteList = new ArrayList<>();

    @Override
    public void onEnable() {
        this.log = new Config(this.getDataFolder() + "/log.yml", Config.YAML);

        this.emoteList.clear();
        this.emoteList.addAll(this.log.getStringList("emoteList"));

        this.getServer().getPluginManager().registerEvents(this, this);

        this.getServer().getScheduler().scheduleRepeatingTask(this, this::saveEmoteList, 20 * 60 * 5); // 5分钟保存一次
    }

    @Override
    public void onDisable() {
        this.saveEmoteList();
    }

    public void saveEmoteList() {
        this.log.set("emoteList", this.emoteList);
        this.log.save();
    }

    @EventHandler
    public void onDataPacketReceive(DataPacketReceiveEvent event) {
        if (event.getPacket() instanceof EmoteListPacket) {
            this.getLogger().info(event.getPacket().toString());
        }else if (event.getPacket() instanceof EmotePacket) {
            EmotePacket emotePacket = (EmotePacket) event.getPacket();
            this.emoteList.add(emotePacket.emoteID);
            this.getLogger().info(event.getPacket().toString());
        }
    }

}
