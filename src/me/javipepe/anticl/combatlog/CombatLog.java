package me.javipepe.anticl.combatlog;

import me.javipepe.anticl.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;

/**
 * Created by javipepe on 29/06/15.
 */
public class CombatLog implements Listener{

    /*public static String reason = "Melee";

    public static ArrayList<Player> peopleattacked = new ArrayList<>();

    public main.Countdown c1 = new main.Countdown();

    @EventHandler
    public static void onAttack (EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            peopleattacked.add(((Player)e.getDamager()));
            peopleattacked.add(((Player)e.getEntity()));

            return;
        }

    }

    public static boolean hasbeenattackedrecently(Player p){
        return(peopleattacked.contains(p));


    }*/
}
