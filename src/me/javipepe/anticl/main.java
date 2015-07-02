package me.javipepe.anticl;

import me.javipepe.anticl.combatlog.CombatLog;
import me.javipepe.anticl.voidlog.VoidLog;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javipepe on 29/06/15.
 */
public class main extends JavaPlugin implements Listener{

    public void onEnable(){
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new CombatLog(), this);
    }

    public Countdown c = new Countdown();

    public static ArrayList<Player> peopleattacked = new ArrayList<>();

    public static String reason = "Melee";
    @EventHandler
    public static void onPlayerLeave(PlayerQuitEvent e){

        Player s = e.getPlayer();


        if(VoidLog.wasinvoid(s) && !hasbeenattackedrecently(s)){
                /*Bukkit.dispatchCommand(this.getServer().getConsoleSender(), "report " + s.getName() + " Combat logging detected (" + VoidLog.reason + ")");*/
            s.sendMessage(ChatColor.GRAY + "Successfully left the game.");
            for(Player p : Bukkit.getOnlinePlayers()){
                if(p.isOp()){
                    p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Report: " + ChatColor.RESET + ChatColor.AQUA + "Console" + ChatColor.RED + " -> " + s.getDisplayName() + ChatColor.GRAY + "" + ChatColor.ITALIC + ": Combat logging detected (" + VoidLog.reason + ", " + "Disconnecting)" );

                }
            }
            return;
        }


            /*COMBAT LOG*/

        if(hasbeenattackedrecently(s)){
                /*Bukkit.dispatchCommand(this.getServer().getConsoleSender(), "report " + s.getName() + " Combat logging detected (" + VoidLog.reason + ")");*/
            s.sendMessage(ChatColor.GRAY + "Successfully left the game.");
            for(Player p : Bukkit.getOnlinePlayers()){
                if(p.isOp()){
                    p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Report: " + ChatColor.RESET + ChatColor.AQUA + "Console" + ChatColor.RED + " -> " + s.getDisplayName() + ChatColor.GRAY + "" + ChatColor.ITALIC + ": Combat logging detected (" + reason + ", " + "Disconnecting)" );

                }
            }
            return;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String title, String[] args){
        if(cmd.getName().equalsIgnoreCase("leaveit")){
            Player s = (Player) sender;


            /*VOID LOG*/

            if(VoidLog.wasinvoid(s) && !hasbeenattackedrecently(s)){
                /*Bukkit.dispatchCommand(this.getServer().getConsoleSender(), "report " + s.getName() + " Combat logging detected (" + VoidLog.reason + ")");*/
                s.sendMessage(ChatColor.GRAY + "Successfully left the game.");
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.isOp()){
                        p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Report: " + ChatColor.RESET + ChatColor.AQUA + "Console" + ChatColor.RED + " -> " + s.getDisplayName() + ChatColor.GRAY + "" + ChatColor.ITALIC + ": Combat logging detected (" + VoidLog.reason + ", " + "Joining observers)" );

                    }
                }
                return true;
            }


            /*COMBAT LOG*/

            if(hasbeenattackedrecently(s)){
                /*Bukkit.dispatchCommand(this.getServer().getConsoleSender(), "report " + s.getName() + " Combat logging detected (" + VoidLog.reason + ")");*/
                s.sendMessage(ChatColor.GRAY + "Successfully left the game.");
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.isOp()){
                        p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Report: " + ChatColor.RESET + ChatColor.AQUA + "Console" + ChatColor.RED + " -> " + s.getDisplayName() + ChatColor.GRAY + "" + ChatColor.ITALIC + ": Combat logging detected (" + reason + ", " + "Joining observers)" );

                    }
                }
                return true;
            }


        }
        return true;
    }

    @EventHandler
    public void onAttack (EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            peopleattacked.add(((Player)e.getDamager()));
            peopleattacked.add(((Player) e.getEntity()));
            c.setList(peopleattacked);
            c.setPlayer1(((Player) e.getDamager()));
            c.setPlayer2(((Player) e.getEntity()));
            new Thread(c).start();

            return;
        }

    }

    public static boolean hasbeenattackedrecently(Player p){
        return(peopleattacked.contains(p));


    }

    public class Countdown implements Runnable{

        public Player player1 = null;
        public Player player2 = null;
        public List<Player> list1 = new ArrayList<Player>();

        public void setPlayer1(Player p){
            player1 = p;
        }
        public void setPlayer2(Player p1){
            player2 = p1;
        }

        public void setList(List<Player> list){
            list1 = list;
        }
        public void run(){
            try {
                Thread.sleep(6000);
                list1.remove(player1);
                list1.remove(player2);
            }catch (Exception ignored){

            }

        }
    }
}
