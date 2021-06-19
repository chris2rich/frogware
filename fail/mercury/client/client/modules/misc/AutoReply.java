package fail.mercury.client.client.modules.misc;

import fail.mercury.client.api.module.Module;
import fail.mercury.client.api.module.annotations.ModuleManifest;
import fail.mercury.client.api.module.category.Category;
import fail.mercury.client.client.events.PacketEvent;
import me.kix.lotus.property.annotations.Property;
import net.b0at.api.event.EventHandler;
import net.minecraft.network.play.server.SPacketChat;

@ModuleManifest(
   label = "AutoReply",
   category = Category.MISC,
   fakelabel = "Auto Reply",
   description = "Automatically replies to messages."
)
public class AutoReply extends Module {
   @Property("Message")
   private String message = "sorry, im too busy using mercury.fail";

   @EventHandler
   public void onPacket(PacketEvent event) {
      if (event.getType().equals(PacketEvent.Type.INCOMING) && event.getPacket() instanceof SPacketChat && event.getPacket() instanceof SPacketChat && ((SPacketChat)event.getPacket()).func_148915_c().func_150260_c().contains("whispers:")) {
         mc.field_71439_g.func_71165_d("/r " + this.message);
      }

   }
}
