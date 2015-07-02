package me.javipepe.anticl.voidlog;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * Created by javipepe on 29/06/15.
 */
public class VoidLog {

    public static String reason = "Void";

    public static boolean wasinvoid(Player player){
        if(player.getGameMode() == GameMode.SURVIVAL){
            return (player.getLocation().getBlockY() <= 0);

        }
        return false;
    }
}
