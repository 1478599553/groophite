package com.draming.groophite.processor;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;


import com.draming.groophite.groophite;

import crafttweaker.api.data.IData;
import groovy.lang.Binding;



public class GroovyProcessor {



    static File SCRIPT_DIR = new File("./scripts/groophite");
    Binding binding = new Binding();
    /*File[] script_files = SCRIPT_DIR.listFiles
        (
                (dir, name) -> name.endsWith(".groovy")
        );
    */
    Set<File> groovyFiles= new HashSet<>();

    void findGroovyFiles(File dir2find){
        if (dir2find.isDirectory()&&dir2find.listFiles()!=null){
            for(File file : dir2find.listFiles()){
                if(file.isDirectory()){
                    findGroovyFiles(file);
                }else if (file.isFile()&&file.getName().endsWith(".groovy")){
                    groovyFiles.add(file);
                }
            }
        }
    }

    public static String[] eventClassNameArray = {"crafttweaker.mc1120.events.handling.MCAnimalTameEvent","crafttweaker.mc1120.events.handling.MCArrowLooseEvent","crafttweaker.mc1120.events.handling.MCArrowNockEvent","crafttweaker.mc1120.events.handling.MCBlockBreakEvent","crafttweaker.mc1120.events.handling.MCBlockEvent","crafttweaker.mc1120.events.handling.MCBlockFarmlandTrampleEvent","crafttweaker.mc1120.events.handling.MCBlockHarvestDropsEvent","crafttweaker.mc1120.events.handling.MCBlockNeighborNotifyEvent","crafttweaker.mc1120.events.handling.MCBlockPlaceEvent","crafttweaker.mc1120.events.handling.MCClientTickEvent","crafttweaker.mc1120.events.handling.MCCommandEvent","crafttweaker.mc1120.events.handling.MCCriticalHitEvent","crafttweaker.mc1120.events.handling.MCCropGrowPostEvent","crafttweaker.mc1120.events.handling.MCCropGrowPreEvent","crafttweaker.mc1120.events.handling.MCEnchantmentLevelSetEvent","crafttweaker.mc1120.events.handling.MCEnderTeleportEvent","crafttweaker.mc1120.events.handling.MCEntityJoinWorldEvent","crafttweaker.mc1120.events.handling.MCEntityLivingAttackedEvent","crafttweaker.mc1120.events.handling.MCEntityLivingDamageEvent","crafttweaker.mc1120.events.handling.MCEntityLivingDeathDropsEvent","crafttweaker.mc1120.events.handling.MCEntityLivingDeathEvent","crafttweaker.mc1120.events.handling.MCEntityLivingEquipmentChangeEvent","crafttweaker.mc1120.events.handling.MCEntityLivingFallEvent","crafttweaker.mc1120.events.handling.MCEntityLivingHealEvent","crafttweaker.mc1120.events.handling.MCEntityLivingHurtEvent","crafttweaker.mc1120.events.handling.MCEntityLivingJumpEvent","crafttweaker.mc1120.events.handling.MCEntityLivingSpawnEvent$MCEntityLivingExtendedSpawnEvent","crafttweaker.mc1120.events.handling.MCEntityLivingSpawnEvent$MCEntityLivingSpecialSpawnEvent","crafttweaker.mc1120.events.handling.MCEntityLivingSpawnEvent","crafttweaker.mc1120.events.handling.MCEntityLivingUpdateEvent","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent$Finish","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent$Start","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent$Stop","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent$Tick","crafttweaker.mc1120.events.handling.MCEntityLivingUseItemEvent","crafttweaker.mc1120.events.handling.MCEntityMountEvent","crafttweaker.mc1120.events.handling.MCEntityStruckByLightningEvent","crafttweaker.mc1120.events.handling.MCEntityTravelToDimensionEvent","crafttweaker.mc1120.events.handling.MCExplosionDetonateEvent","crafttweaker.mc1120.events.handling.MCExplosionEvent","crafttweaker.mc1120.events.handling.MCExplosionStartEvent","crafttweaker.mc1120.events.handling.MCItemExpireEvent","crafttweaker.mc1120.events.handling.MCItemFishedEvent","crafttweaker.mc1120.events.handling.MCItemTossEvent","crafttweaker.mc1120.events.handling.MCLivingDestroyBlockEvent","crafttweaker.mc1120.events.handling.MCLivingExperienceDropEvent","crafttweaker.mc1120.events.handling.MCLivingKnockBackEvent","crafttweaker.mc1120.events.handling.MCLootingLevelEvent","crafttweaker.mc1120.events.handling.MCMinecartCollisionEvent","crafttweaker.mc1120.events.handling.MCMinecartInteractEvent","crafttweaker.mc1120.events.handling.MCMinecartUpdateEvent","crafttweaker.mc1120.events.handling.MCMobGriefingEvent","crafttweaker.mc1120.events.handling.MCNoteBlockChangeEvent","crafttweaker.mc1120.events.handling.MCNoteBlockEvent","crafttweaker.mc1120.events.handling.MCNoteBlockPlayEvent","crafttweaker.mc1120.events.handling.MCPlayerAdvancementEvent","crafttweaker.mc1120.events.handling.MCPlayerAnvilRepairEvent","crafttweaker.mc1120.events.handling.MCPlayerAnvilUpdateEvent","crafttweaker.mc1120.events.handling.MCPlayerAttackEntityEvent","crafttweaker.mc1120.events.handling.MCPlayerBonemealEvent","crafttweaker.mc1120.events.handling.MCPlayerBreakSpeedEvent","crafttweaker.mc1120.events.handling.MCPlayerBrewedPotionEvent","crafttweaker.mc1120.events.handling.MCPlayerChangedDimensionEvent","crafttweaker.mc1120.events.handling.MCPlayerCloneEvent","crafttweaker.mc1120.events.handling.MCPlayerCloseContainerEvent","crafttweaker.mc1120.events.handling.MCPlayerCraftedEvent","crafttweaker.mc1120.events.handling.MCPlayerDeathDropsEvent","crafttweaker.mc1120.events.handling.MCPlayerDestroyItemEvent","crafttweaker.mc1120.events.handling.MCPlayerFillBucketEvent","crafttweaker.mc1120.events.handling.MCPlayerInteractEntityEvent","crafttweaker.mc1120.events.handling.MCPlayerInteractEvent","crafttweaker.mc1120.events.handling.MCPlayerItemPickupEvent","crafttweaker.mc1120.events.handling.MCPlayerLeftClickBlockEvent","crafttweaker.mc1120.events.handling.MCPlayerLoggedInEvent","crafttweaker.mc1120.events.handling.MCPlayerLoggedOutEvent","crafttweaker.mc1120.events.handling.MCPlayerOpenContainerEvent","crafttweaker.mc1120.events.handling.MCPlayerPickupItemEvent","crafttweaker.mc1120.events.handling.MCPlayerPickupXpEvent","crafttweaker.mc1120.events.handling.MCPlayerRespawnEvent","crafttweaker.mc1120.events.handling.MCPlayerRightClickBlockEvent","crafttweaker.mc1120.events.handling.MCPlayerRightClickItemEvent","crafttweaker.mc1120.events.handling.MCPlayerSetSpawnEvent","crafttweaker.mc1120.events.handling.MCPlayerSleepInBedEvent","crafttweaker.mc1120.events.handling.MCPlayerTickEvent","crafttweaker.mc1120.events.handling.MCPlayerUseHoeEvent","crafttweaker.mc1120.events.handling.MCPlayerVisibilityEvent","crafttweaker.mc1120.events.handling.MCPlayerWakeUpEvent","crafttweaker.mc1120.events.handling.MCPortalSpawnEvent","crafttweaker.mc1120.events.handling.MCPotionBrewEvent","crafttweaker.mc1120.events.handling.MCPotionBrewPostEvent","crafttweaker.mc1120.events.handling.MCPotionBrewPreEvent","crafttweaker.mc1120.events.handling.MCPotionEffectAddedEvent","crafttweaker.mc1120.events.handling.MCPotionEffectEvent","crafttweaker.mc1120.events.handling.MCProjectileImpactArrowEvent","crafttweaker.mc1120.events.handling.MCProjectileImpactEvent","crafttweaker.mc1120.events.handling.MCProjectileImpactFireballEvent","crafttweaker.mc1120.events.handling.MCProjectileImpactThrowableEvent","crafttweaker.mc1120.events.handling.MCRenderTickEvent","crafttweaker.mc1120.events.handling.MCServerTickEvent","crafttweaker.mc1120.events.handling.MCSleepingLocationCheckEvent","crafttweaker.mc1120.events.handling.MCSleepingTimeCheckEvent","crafttweaker.mc1120.events.handling.MCTickEvent","crafttweaker.mc1120.events.handling.MCWorldTickEvent"};

    public static List<Class> eventClassesList = new ArrayList<>();

    public static void findEventClasses(){
        for (String c: eventClassNameArray) {
            try {
                Class eventClass = Class.forName("com.draming.groophite.api.events.G_"+getPureClassName(c));
                eventClassesList.add(eventClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cleanClosures(){
        eventClassesList.forEach((Class eventClass)->{
            try {
                Field callList =  eventClass.getDeclaredField("callList");
                callList.setAccessible(true);
                callList.set(null,new HashSet<>());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public GroovyProcessor() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, IOException {
        cleanClosures();

        findGroovyFiles(SCRIPT_DIR);

        if (groovyFiles  != null)
        {

        for (File key : groovyFiles)
            {

                groophite.logger.info("loading script : " + key.getName());
                long timeBefore = System.currentTimeMillis();
                try {
                    GroovyScriptFactory.getInstance().scriptGetAndRun(key, binding);
                }catch (Exception e){
                    e.printStackTrace();
                }
                long timeAfter = System.currentTimeMillis();
                groophite.logger.info("loaded script : " + key.getName());
                groophite.logger.info("It took: " + (timeAfter - timeBefore) + " ms.");

            }
        }
    }

    public static String getPureClassName (String c){
        return c.split("\\.")[c.split("\\.").length-1];
    }
}
