import com.draming.groophite.api.getters
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.text.TextComponentString
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.event.entity.player.PlayerContainerEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent


@Mod.EventBusSubscriber
class test {
    static void main(String[] args) {
    def List<String> list = getters.getModList();
        println(list)
    }
    @SubscribeEvent
    static void onEat(EntityItemPickupEvent event){
    event.getEntityPlayer().sendMessage(new TextComponentString("OK!"));
    }
}
