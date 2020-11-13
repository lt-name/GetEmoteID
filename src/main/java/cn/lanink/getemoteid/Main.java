package cn.lanink.getemoteid;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.EmoteListPacket;
import cn.nukkit.network.protocol.EmotePacket;
import cn.nukkit.plugin.PluginBase;

/**
 * @author lt_name
 */
public class Main extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onDataPacketReceive(DataPacketReceiveEvent event) {
        if (event.getPacket() instanceof EmoteListPacket ||
                event.getPacket() instanceof EmotePacket) {
            this.getLogger().info(event.getPacket().toString());
        }
    }

}
