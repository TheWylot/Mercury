package ir.ciph3r.mercury.modules.impl.privatechat;

import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import ir.ciph3r.mercury.MercuryAPI;
import ir.ciph3r.mercury.modules.CommandModule;
import ir.ciph3r.mercury.modules.impl.privatechat.listener.PrivateChatListener;
import ir.ciph3r.mercury.utility.ArrayUtils;
import ir.ciph3r.mercury.utility.ChatUtils;
import org.bukkit.entity.Player;

@CommandAlias("Tell|T|Whisper|Message|MSG")
public class Tell extends CommandModule {
    public Tell() {
        super("PrivateChat", MercuryAPI.INSTANCE.getConfig().PRIVATE_CHAT_ENABLED, new PrivateChatListener());
        setCommandNameAndSyntax("/Tell", "<player> <message>");
    }

    @Default
    @Syntax("<player> <message>")
    @CommandPermission("mercury.commands.tell")
    @CommandCompletion("@players")
    public void onDefault(Player player, @Conditions("notYourSelf") OnlinePlayer target, String[] args){
        String msg = ArrayUtils.createStringFromArray(args, 0, args.length);
        ChatUtils.sendColorizedMSG(target.getPlayer(), MercuryAPI.INSTANCE.getMessages().PRIVATE_CHAT_MESSAGE
                .replace("{sender}", player.getName())
                .replace("{receiver}", target.getPlayer().getName())
                .replace("{message}", msg));

        ChatUtils.sendColorizedMSG(player, MercuryAPI.INSTANCE.getMessages().PRIVATE_CHAT_MESSAGE
                .replace("{sender}", player.getName())
                .replace("{receiver}", target.getPlayer().getName())
                .replace("{message}", msg));

        PrivateChatListener.replyList.put(target.getPlayer().getName(), player.getName());
    }
}
