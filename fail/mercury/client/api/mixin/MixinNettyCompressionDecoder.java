package fail.mercury.client.api.mixin;

import fail.mercury.client.api.util.ChatUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import net.minecraft.network.NettyCompressionDecoder;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({NettyCompressionDecoder.class})
public class MixinNettyCompressionDecoder {
   @Shadow
   @Final
   private Inflater field_179305_a;
   @Shadow
   private int field_179304_b;

   @Overwrite
   protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_) throws DataFormatException, Exception {
      if (p_decode_2_.readableBytes() != 0) {
         PacketBuffer packetbuffer = new PacketBuffer(p_decode_2_);
         int i = packetbuffer.func_150792_a();
         if (i == 0) {
            p_decode_3_.add(packetbuffer.readBytes(packetbuffer.readableBytes()));
         } else {
            if (i < this.field_179304_b) {
               ChatUtil.print("Badly compressed packet - size of " + i + " is below server threshold of " + this.field_179304_b);
            }

            if (i > 2097152) {
               ChatUtil.print("Badly compressed packet - size of " + i + " is larger than protocol maximum of " + 2097152);
            }

            byte[] abyte = new byte[packetbuffer.readableBytes()];
            packetbuffer.readBytes(abyte);
            this.field_179305_a.setInput(abyte);
            byte[] abyte1 = new byte[i];
            this.field_179305_a.inflate(abyte1);
            p_decode_3_.add(Unpooled.wrappedBuffer(abyte1));
            this.field_179305_a.reset();
         }
      }

   }
}
