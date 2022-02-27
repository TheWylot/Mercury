package ir.ciph3r.mercury.modules;

import ir.ciph3r.mercury.Mercury;
import ir.ciph3r.mercury.modules.base.ModuleBase;
import ir.ciph3r.mercury.storage.Permissions.Perms;
import ir.ciph3r.mercury.storage.yaml.Config;
import ir.ciph3r.mercury.storage.yaml.Messages;
import ir.ciph3r.mercury.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat extends ModuleBase {
    public Hat(Mercury mercury) {
        super("Hat", "Hat", Config.HAT_ENABLED, mercury);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender.hasPermission(Perms.HAT))) {
            Utils.sendColorizedMessage(sender, Messages.NO_PERMISSION);
            return true;
        }
        if (!(sender instanceof Player)) {
            Utils.sendColorizedMessage(sender, Messages.NO_CONSOLE);
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            ItemStack held = player.getInventory().getItemInMainHand();
            ItemStack helm = player.getInventory().getHelmet();

            player.getInventory().setHelmet(held);
            player.getInventory().setItemInMainHand(helm);

            Utils.sendColorizedMessage(player, Messages.HAT_UPDATED);
            return true;
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                Utils.sendColorizedMessage(player, Messages.PLAYER_NOT_FOUND);
                return true;
            } else {
                ItemStack held = target.getInventory().getItemInMainHand();
                ItemStack helm = target.getInventory().getHelmet();

                target.getInventory().setHelmet(held);
                target.getInventory().setItemInMainHand(helm);

                Utils.sendColorizedMessage(player, Messages.HAT_UPDATED_ADMIN.replace("{player}", target.getName()));
                Utils.sendColorizedMessage(target, Messages.HAT_UPDATED);
                return true;
            }
        }
        return true;
    }
}
