import com.draming.groophite.api.Getters
import net.minecraft.util.text.TextComponentString
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
class test {
    static void main(String[] args) {
    def List<String> list = Getters.getModList();
        println(list)
    }
    @SubscribeEvent
    static void onEat(EntityItemPickupEvent event){
    event.getEntityPlayer().sendMessage(new TextComponentString("OK!"));
    }
}
