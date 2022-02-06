package com.draming.groophite.api.proxier;


import net.minecraft.util.math.BlockPos;

public class G_Pos {

    public double Pos_X;
    public double Pos_Y;
    public double Pos_Z;
    public G_Pos(double X, double Y, double Z){
        this.Pos_X = X;
        this.Pos_Y = Y;
        this.Pos_Z = Z;
    }
    public G_Pos(BlockPos blockPos){
        this.Pos_X = blockPos.getX();
        this.Pos_Y = blockPos.getY();
        this.Pos_Z = blockPos.getZ();
    }

    public double getPos_X(){
        return this.Pos_X;
    }
    public double getPos_Y(){
        return this.Pos_Y;
    }
    public double getPos_Z(){
        return this.Pos_Z;
    }

    @Override
    public String toString (){
        BlockPos blockPos = new BlockPos(this.Pos_X,this.Pos_Y,this.Pos_Z);
        return blockPos.toString();
    }
}
