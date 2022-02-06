import com.draming.groophite.api.events.EntityEventHelper
import com.draming.groophite.api.events.Groophite_PlayerContainerEvent

public class test3{
    public static void main (String[] args){
        Groophite_PlayerContainerEvent.subscribeEvent {
            EntityEventHelper entityEventHelper ->
            println("ok!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                println(entityEventHelper.g_entityPlayer.getName());
                entityEventHelper.g_entityPlayer.setDead();
        }
    }
}