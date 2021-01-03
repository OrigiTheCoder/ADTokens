package testpl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class main extends JavaPlugin implements Listener{
    public static Economy economy;
    int adToken = 0;

    @Override
    public void onEnable() {
        System.out.println("Plugin sucessfully enabled");
        if (!setupEconomy() ) {
       
            getServer().getPluginManager().disablePlugin(this);
            
            return;
           
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin sucessfully disabled");
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    public static Economy getEconomy() {
        return economy;
    }

    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd,String label,String[] args) {

        Player player = (Player) sender;

        if(cmd.getName().equals("buyad")){
            if(sender instanceof Player) {
				double balance = economy.getBalance(player);
                if(balance >= 1500) {

                    player.sendMessage(ChatColor.GRAY + "Sucessfully bought 1 AD Token");
                    economy.bankWithdraw(player.getName(), 1000);
                    adToken++;
                }
            }else {
                System.out.println("This can be only used by players");
            }
        }
        if(cmd.getName().equals("ad")) {
            String msg= "";
            for (int i=0; i<args.length; i++){
                msg += args[i] + " ";

            }

            Bukkit.broadcastMessage("§b*"+"§3*"+"§b" + "§3Player" + "§b§nAdvertisement" + "§b*"+"§3*"+"§b");
            Bukkit.broadcastMessage("§r");
            Bukkit.broadcastMessage("§b*" + "§3Message: " + "§f" + msg);
            Bukkit.broadcastMessage("§b*" + "§3Sent by: " + "§f" + sender.getName());
            Bukkit.broadcastMessage("§r");
            Bukkit.broadcastMessage("§7(( Purchase AD Tokens to Advertise In Chat");

            adToken--;

        }


        return false;
    }
}