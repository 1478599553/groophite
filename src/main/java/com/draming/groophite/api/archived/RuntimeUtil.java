package com.draming.groophite.api.archived;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class RuntimeUtil {
    public static String temp = "package com.draming.groophite.api.events;\n" +
            "\n" +
            "import crafttweaker.mc1120.events.handling.MCPlayerAdvancementEvent;\n" +
            "import groovy.lang.Closure;\n" +
            "import net.minecraftforge.event.entity.player.AdvancementEvent;\n" +
            "import net.minecraftforge.fml.common.Mod;\n" +
            "import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;\n" +
            "import groovy.transform.stc.ClosureParams;\n" +
            "import groovy.transform.stc.FromString;\n"+
            "\n" +
            "import java.util.HashSet;\n" +
            "import java.util.Set;\n" +
            "\n" +
            "@Mod.EventBusSubscriber\n" +
            "public class EventSubscriberTemplate {\n" +
            "    public static Set<Closure> callList = new HashSet<>();\n" +
            "    \n" +
            "        @SubscribeEvent\n" +
            "    public static void onEvent(AdvancementEvent event){\n" +
            "        for (Closure closure: callList){\n" +
            "            closure.call(new MCPlayerAdvancementEvent(event));\n" +
            "        }\n" +
            "    }\n" +
            "public static void sub(@ClosureParams(value = FromString.class,options = \"crafttweaker.mc1120.events.handling.MCPlayerAdvancementEvent\")Closure closure){\n" +
            "            callList.add(closure);\n" +
            "    }\n"+
            "}";

public static String[] classes = {"crafttweaker.mc1120.events.handling.MCAnimalTameEvent","crafttweaker.mc1120.events.handling.MCArrowLooseEvent","crafttweaker.mc1120.events.handling.MCArrowNockEvent","crafttweaker.mc1120.events.handling.MCBlockBreakEvent","crafttweaker.mc1120.events.handling.MCBlockEvent","crafttweaker.mc1120.events.handling.MCBlockFarmlandTrampleEvent","crafttweaker.mc1120.events.handling.MCBlockHarvestDropsEvent","crafttweaker.mc1120.events.handling.MCBlockNeighborNotifyEvent","crafttweaker.mc1120.events.handling.MCBlockPlaceEvent","crafttweaker.mc1120.events.handling.MCClientTickEvent","crafttweaker.mc1120.events.handling.MCCommandEvent","crafttweaker.mc1120.events.handling.MCCriticalHitEvent","crafttweaker.mc1120.events.handling.MCCropGrowPostEvent","crafttweaker.mc1120.events.handling.MCCropGrowPreEvent","crafttweaker.mc1120.events.handling.MCEnchantmentLevelSetEvent","crafttweaker.mc1120.events.handling.MCEnderTeleportEvent","crafttweaker.mc1120.events.handling.MCEntityJoinWorldEvent","crafttweaker.mc1120.events.handling.MCEntityLivingAttackedEvent","crafttweaker.mc1120.events.handling.MCEntityLivingDamageEvent","crafttweaker.mc1120.events.handling.MCEntityLivingDeathDropsEvent","crafttweaker.mc1120.events.handling.MCEntityLivingDeathEvent","crafttweaker.mc1120.events.handling.MCEntityLivingEquipmentChangeEvent","crafttweaker.mc1120.events.handling.MCEntityLivingFallEvent","crafttweaker.mc1120.events.handling.MCEntityLivingHealEvent","crafttweaker.mc1120.events.handling.MCEntityLivingHurtEvent","crafttweaker.mc1120.events.handling.MCEntityLivingJumpEvent","crafttweaker.mc1120.events.handling.MCEntityLivingSpawnEvent$MCEntityLivingExtendedSpawnEvent","crafttweaker.mc1120.events.handling.MCEntityLivingSpawnEvent$MCEntityLivingSpecialSpawnEvent","crafttweaker.mc1120.events.handling.MCEntityLivingSpawnEvent","crafttweaker.mc1120.events.handling.MCEntityLivingUpdateEvent","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent$Finish","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent$Start","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent$Stop","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent$Tick","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent","crafttweaker.mc1120.events.handling.MCEntityMountEvent","crafttweaker.mc1120.events.handling.MCEntityStruckByLightningEvent","crafttweaker.mc1120.events.handling.MCEntityTravelToDimensionEvent","crafttweaker.mc1120.events.handling.MCExplosionDetonateEvent","crafttweaker.mc1120.events.handling.MCExplosionEvent","crafttweaker.mc1120.events.handling.MCExplosionStartEvent","crafttweaker.mc1120.events.handling.MCItemExpireEvent","crafttweaker.mc1120.events.handling.MCItemFishedEvent","crafttweaker.mc1120.events.handling.MCItemTossEvent","crafttweaker.mc1120.events.handling.MCLivingDestroyBlockEvent","crafttweaker.mc1120.events.handling.MCLivingExperienceDropEvent","crafttweaker.mc1120.events.handling.MCLivingKnockBackEvent","crafttweaker.mc1120.events.handling.MCLootingLevelEvent","crafttweaker.mc1120.events.handling.MCMinecartCollisionEvent","crafttweaker.mc1120.events.handling.MCMinecartInteractEvent","crafttweaker.mc1120.events.handling.MCMinecartUpdateEvent","crafttweaker.mc1120.events.handling.MCMobGriefingEvent","crafttweaker.mc1120.events.handling.MCNoteBlockChangeEvent","crafttweaker.mc1120.events.handling.MCNoteBlockEvent","crafttweaker.mc1120.events.handling.MCNoteBlockPlayEvent","crafttweaker.mc1120.events.handling.MCPlayerAdvancementEvent","crafttweaker.mc1120.events.handling.MCPlayerAnvilRepairEvent","crafttweaker.mc1120.events.handling.MCPlayerAnvilUpdateEvent","crafttweaker.mc1120.events.handling.MCPlayerAttackEntityEvent","crafttweaker.mc1120.events.handling.MCPlayerBonemealEvent","crafttweaker.mc1120.events.handling.MCPlayerBreakSpeedEvent","crafttweaker.mc1120.events.handling.MCPlayerBrewedPotionEvent","crafttweaker.mc1120.events.handling.MCPlayerChangedDimensionEvent","crafttweaker.mc1120.events.handling.MCPlayerCloneEvent","crafttweaker.mc1120.events.handling.MCPlayerCloseContainerEvent","crafttweaker.mc1120.events.handling.MCPlayerCraftedEvent","crafttweaker.mc1120.events.handling.MCPlayerDeathDropsEvent","crafttweaker.mc1120.events.handling.MCPlayerDestroyItemEvent","crafttweaker.mc1120.events.handling.MCPlayerFillBucketEvent","crafttweaker.mc1120.events.handling.MCPlayerInteractEntityEvent","crafttweaker.mc1120.events.handling.MCPlayerInteractEvent","crafttweaker.mc1120.events.handling.MCPlayerItemPickupEvent","crafttweaker.mc1120.events.handling.MCPlayerLeftClickBlockEvent","crafttweaker.mc1120.events.handling.MCPlayerLoggedInEvent","crafttweaker.mc1120.events.handling.MCPlayerLoggedOutEvent","crafttweaker.mc1120.events.handling.MCPlayerOpenContainerEvent","crafttweaker.mc1120.events.handling.MCPlayerPickupItemEvent","crafttweaker.mc1120.events.handling.MCPlayerPickupXpEvent","crafttweaker.mc1120.events.handling.MCPlayerRespawnEvent","crafttweaker.mc1120.events.handling.MCPlayerRightClickBlockEvent","crafttweaker.mc1120.events.handling.MCPlayerRightClickItemEvent","crafttweaker.mc1120.events.handling.MCPlayerSetSpawnEvent","crafttweaker.mc1120.events.handling.MCPlayerSleepInBedEvent","crafttweaker.mc1120.events.handling.MCPlayerSmeltedEvent","crafttweaker.mc1120.events.handling.MCPlayerTickEvent","crafttweaker.mc1120.events.handling.MCPlayerUseHoeEvent","crafttweaker.mc1120.events.handling.MCPlayerVisibilityEvent","crafttweaker.mc1120.events.handling.MCPlayerWakeUpEvent","crafttweaker.mc1120.events.handling.MCPortalSpawnEvent","crafttweaker.mc1120.events.handling.MCPotionBrewEvent","crafttweaker.mc1120.events.handling.MCPotionBrewPostEvent","crafttweaker.mc1120.events.handling.MCPotionBrewPreEvent","crafttweaker.mc1120.events.handling.MCPotionEffectAddedEvent","crafttweaker.mc1120.events.handling.MCPotionEffectEvent","crafttweaker.mc1120.events.handling.MCProjectileImpactArrowEvent","crafttweaker.mc1120.events.handling.MCProjectileImpactEvent","crafttweaker.mc1120.events.handling.MCProjectileImpactFireballEvent","crafttweaker.mc1120.events.handling.MCProjectileImpactThrowableEvent","crafttweaker.mc1120.events.handling.MCRenderTickEvent","crafttweaker.mc1120.events.handling.MCServerTickEvent","crafttweaker.mc1120.events.handling.MCSleepingLocationCheckEvent","crafttweaker.mc1120.events.handling.MCSleepingTimeCheckEvent","crafttweaker.mc1120.events.handling.MCTickEvent","crafttweaker.mc1120.events.handling.MCWorldTickEvent"};

    public static String getPureClassName (Class c){
    return c.getName().split("\\.")[c.getName().split("\\.").length-1];
}

    public static void genEventCode (){
        Set<Class> classesSet = new HashSet<>();
        for (String str : classes){
            try {
                classesSet.add(Class.forName(str));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (Class clazz:classesSet){
            Constructor constructor = clazz.getConstructors()[0];
            Class parameter = constructor.getParameterTypes()[0];
            String temp =
                    RuntimeUtil.temp
                            .replace(
                                    "import crafttweaker.mc1120.events.handling.MCPlayerAdvancementEvent",
                                    "import "+clazz.getName().replace("$","."))

                            .replace("import net.minecraftforge.event.entity.player.AdvancementEvent"
                            ,"import "+ parameter.getName().replace("$","."))
                            .replace("public class EventSubscriberTemplate"
                            ,"public class G_"+getPureClassName(clazz))
                            .replace("onEvent(AdvancementEvent event)"
                                    , "onEvent("+parameter.getName().replace("$",".")+" event)")
                            .replace("closure.call(new MCPlayerAdvancementEvent(event))"
                                        ,"closure.call(new "+clazz.getName().replace("$",".")+"(event))")
                            .replace("options = \"crafttweaker.mc1120.events.handling.MCPlayerAdvancementEvent\")"
                            ,"options = \""+clazz.getName()+"\")");

            /*
            if (clazz.getName().contains("$")){
                temp.replace("import "+clazz.getName()
                        ,"import "+clazz.getName().replace("$"+clazz.getName().split("\\$")[clazz.getName().split("\\$").length-1],""));

            }
            if (parameter.getName().contains("$")){
                temp.replace("import "+ parameter.getName()
                        ,"import "+parameter.getName().replace("$"+parameter.getName().split("\\$")[parameter.getName().split("\\$").length-1],""));
            }
*/
            try {

                if (!new File("./events").exists()){
                    new File("./events").mkdir();
                }
                FileUtils.writeStringToFile(new File("./events/G_" + getPureClassName(clazz)+".java"), temp, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Generating "+clazz.getName());
        }
    }
}
