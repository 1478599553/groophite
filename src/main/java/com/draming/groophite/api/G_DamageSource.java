package com.draming.groophite.api;

import net.minecraft.util.DamageSource;

public class G_DamageSource {
    public static final G_DamageSource IN_FIRE = new G_DamageSource(DamageSource.IN_FIRE);
    public static final G_DamageSource LIGHTNING_BOLT = new G_DamageSource(DamageSource.LIGHTNING_BOLT);
    public static final G_DamageSource ON_FIRE        = new G_DamageSource((new DamageSource("onFire")).setDamageBypassesArmor()).setFireDamage();
    public static final G_DamageSource LAVA           = new G_DamageSource((new DamageSource("lava")).setFireDamage());
    public static final G_DamageSource HOT_FLOOR      = new G_DamageSource((new DamageSource("hotFloor")).setFireDamage());
    public static final G_DamageSource IN_WALL        = new G_DamageSource((new DamageSource("inWall")).setDamageBypassesArmor());
    public static final G_DamageSource CRAMMING       = new G_DamageSource((new DamageSource("cramming")).setDamageBypassesArmor());
    public static final G_DamageSource DROWN          = new G_DamageSource((new DamageSource("drown")).setDamageBypassesArmor());
    public static final G_DamageSource STARVE         = new G_DamageSource((new DamageSource("starve")).setDamageBypassesArmor().setDamageIsAbsolute());
    public static final G_DamageSource CACTUS         = new G_DamageSource("cactus");
    public static final G_DamageSource FALL           = new G_DamageSource((new DamageSource("fall")).setDamageBypassesArmor());
    public static final G_DamageSource FLY_INTO_WALL  = new G_DamageSource((new DamageSource("flyIntoWall")).setDamageBypassesArmor());
    public static final G_DamageSource OUT_OF_WORLD   = new G_DamageSource((new DamageSource("outOfWorld")).setDamageBypassesArmor().setDamageAllowedInCreativeMode());
    public static final G_DamageSource GENERIC        = new G_DamageSource((new DamageSource("generic")).setDamageBypassesArmor());
    public static final G_DamageSource MAGIC          = new G_DamageSource((new DamageSource("magic")).setDamageBypassesArmor().setMagicDamage());
    public static final G_DamageSource WITHER         = new G_DamageSource((new DamageSource("wither")).setDamageBypassesArmor());
    public static final G_DamageSource ANVIL          = new G_DamageSource("anvil");
    public static final G_DamageSource FALLING_BLOCK  = new G_DamageSource("fallingBlock");
    public static final G_DamageSource DRAGON_BREATH  = new G_DamageSource((new DamageSource("dragonBreath")).setDamageBypassesArmor());
    public static final G_DamageSource FIREWORKS      = new G_DamageSource((new DamageSource("fireworks")).setExplosion());

    DamageSource __innerDamageSource;
    public String damageType;

    public G_DamageSource(String damageType){
        this.__innerDamageSource = new DamageSource(damageType);
    }
    G_DamageSource(DamageSource damageSource){
        this.__innerDamageSource = damageSource;
    }

    public G_DamageSource setFireDamage(){
        this.__innerDamageSource.setFireDamage();
        return this;
    }
    public G_DamageSource setExplosion(){
        this.__innerDamageSource.setExplosion();
        return this;
    }
    public G_DamageSource setDamageBypassesArmor(){
        this.__innerDamageSource.setDamageBypassesArmor();
        return this;
    }
    public G_DamageSource setMagicDamage(){
        this.__innerDamageSource.setMagicDamage();
        return this;
    }
    public G_DamageSource setDamageIsAbsolute(){
        this.__innerDamageSource.setDamageIsAbsolute();
        return this;
    }
    public G_DamageSource setDamageAllowedInCreativeMode(){
        this.__innerDamageSource.setDamageAllowedInCreativeMode();
        return this;
    }
}
