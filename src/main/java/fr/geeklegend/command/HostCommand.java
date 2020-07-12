package fr.geeklegend.command;

import fr.geeklegend.Main;
import fr.geeklegend.config.ConfigManager;
import fr.geeklegend.server.HostRank;
import fr.geeklegend.server.ServerSettings;
import fr.geeklegend.util.Constant;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HostCommand implements CommandExecutor
{
    private ServerSettings serverSettings;

    public HostCommand()
    {
        this.serverSettings = Main.getPlugin().getServerSettings();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            Player target;
            ConfigManager configManager = Main.getPlugin().getConfigManager();

            if (serverSettings.isHost(player.getName()))
            {
                if (args.length == 0)
                {
                    sendHelp(player);

                    return false;
                }

                if (serverSettings.getHosts().get(player.getName()).equals(HostRank.MAIN))
                {
                    if (args[0].equalsIgnoreCase("op"))
                    {
                        if (args.length < 2)
                        {
                            player.sendMessage("§7• §d/§fh op §d<joueur> §8» §7Ajouter un hôte secondaire.");

                            return false;
                        } else
                        {
                            target = Bukkit.getPlayer(args[1]);

                            if (target != null)
                            {
                                if (target != player)
                                {
                                    if (!serverSettings.isHost(target.getName()))
                                    {
                                        serverSettings.addHost(target.getName(), HostRank.SECONDARY);

                                        target.getInventory().setItem(Constant.JOIN_ITEM_HOST_SETTINGS_SLOT, Constant.JOIN_ITEM_HOST_SETTINGS_ITEM);

                                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                        target.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);

                                        player.sendMessage(Constant.HOST_OP_SENDER_MESSAGE.replace("%playername%", target.getName()));
                                        target.sendMessage(Constant.HOST_OP_TARGET_MESSAGE);
                                    } else
                                    {
                                        player.sendMessage(Constant.HOST_ALREADY_TARGET_MESSAGE);
                                    }
                                } else
                                {
                                    player.sendMessage(Constant.HOST_ALREADY_SENDER_MESSAGE);
                                }
                            } else
                            {
                                player.sendMessage(Constant.DISCONNECTED_MESSAGE);
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("deop"))
                    {
                        if (args.length < 2)
                        {
                            player.sendMessage("§7• §d/§fh deop §d<joueur> §8» §7Retirer un hôte secondaire.");

                            return false;
                        } else
                        {
                            target = Bukkit.getPlayer(args[1]);

                            if (target != null)
                            {
                                if (target != player)
                                {
                                    FileConfiguration hostConfig = configManager.getHostConfig();

                                    for (String hosts : hostConfig.getStringList("hosts"))
                                    {
                                        if (hosts.equalsIgnoreCase(target.getName()))
                                        {
                                            player.sendMessage(Constant.HOST_DEOP_CANT_MESSAGE);

                                            return false;
                                        }
                                    }

                                    if (serverSettings.isHost(target.getName()))
                                    {
                                        serverSettings.removeHost(target.getName());

                                        target.closeInventory();
                                        target.getInventory().removeItem(Constant.JOIN_ITEM_HOST_SETTINGS_ITEM);

                                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                        target.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);

                                        player.sendMessage(Constant.HOST_DEOP_SENDER_MESSAGE.replace("%playername%", target.getName()));
                                        target.sendMessage(Constant.HOST_DEOP_TARGET_MESSAGE);
                                    } else
                                    {
                                        player.sendMessage(Constant.HOST_NOT_MESSAGE);
                                    }
                                } else
                                {
                                    player.sendMessage(Constant.HOST_CANT_MESSAGE);
                                }
                            } else
                            {
                                player.sendMessage(Constant.DISCONNECTED_MESSAGE);
                            }
                        }
                    }
                }

                if (args[0].equalsIgnoreCase("kick"))
                {
                    if (args.length < 2)
                    {
                        player.sendMessage("§7• §d/§fh kick §d<joueur> §8» §7Éjecter un joueur.");

                        return false;
                    } else
                    {
                        target = Bukkit.getPlayer(args[1]);

                        if (target != null)
                        {
                            if (target != player)
                            {
                                target.kickPlayer(Constant.HOST_KICK_TARGET_MESSAGE);

                                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);

                                player.sendMessage(Constant.HOST_KICK_SENDER_MESSAGE.replace("%playername%", target.getName()));
                            } else
                            {
                                player.sendMessage(Constant.HOST_CANT_MESSAGE);
                            }
                        } else
                        {
                            player.sendMessage(Constant.DISCONNECTED_MESSAGE);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("ban"))
                {
                    if (args.length < 2)
                    {
                        player.sendMessage("§7• §d/§fh ban §d<joueur> §8» §7Bannir un joueur.");

                        return false;
                    } else
                    {
                        target = Bukkit.getPlayer(args[1]);

                        if (target != null)
                        {
                            if (target != player)
                            {
                                if (!serverSettings.isBan(target.getName()))
                                {
                                    serverSettings.addBan(target.getName());

                                    target.kickPlayer(Constant.HOST_BAN_TARGET_MESSAGE);

                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);

                                    player.sendMessage(Constant.HOST_BAN_SENDER_MESSAGE.replace("%playername%", target.getName()));
                                } else
                                {
                                    player.sendMessage(Constant.HOST_ALREADY_BANNED_MESSAGE);
                                }
                            } else
                            {
                                player.sendMessage(Constant.HOST_CANT_MESSAGE);
                            }
                        } else
                        {
                            player.sendMessage(Constant.DISCONNECTED_MESSAGE);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("unban"))
                {
                    if (args.length < 2)
                    {
                        player.sendMessage("§7• §d/§fh unban §d<joueur> §8» §7Met fin à l'exclusion d'un joueur.");

                        return false;
                    } else
                    {
                        target = Bukkit.getPlayer(args[1]);

                        if (target != null)
                        {
                            if (target != player)
                            {
                                if (serverSettings.isBan(target.getName()))
                                {
                                    FileConfiguration banConfig = configManager.getBanConfig();

                                    serverSettings.removeBan(target.getName());

                                    banConfig.set("bans." + target, null);

                                    configManager.save(banConfig, "bans");

                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);

                                    player.sendMessage(Constant.HOST_UNBAN_SENDER_MESSAGE.replace("%playername%", target.getName()));
                                } else
                                {
                                    player.sendMessage(Constant.HOST_NOT_BANNED_MESSAGE);
                                }
                            } else
                            {
                                player.sendMessage(Constant.HOST_CANT_MESSAGE);
                            }
                        } else
                        {
                            player.sendMessage(Constant.DISCONNECTED_MESSAGE);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("oplist"))
                {
                    if (serverSettings.getHosts().isEmpty())
                    {
                        player.sendMessage(Constant.HOST_EMPTY_MESSAGE);
                    } else
                    {
                        player.sendMessage("§7• §dListe des hôtes :");

                        serverSettings.getHosts().entrySet().forEach(host ->
                        {
                            String key = host.getKey();
                            HostRank hostRank = serverSettings.getHostRank(key);

                            if (hostRank != null)
                            {
                                player.sendMessage("§7- §f" + key);
                            }
                        });
                    }
                } else if (args[0].equalsIgnoreCase("banlist"))
                {
                    if (serverSettings.getBans().isEmpty())
                    {
                        player.sendMessage(Constant.HOST_BANS_EMPTY_MESSAGE);
                    } else
                    {
                        player.sendMessage("§7• §dListe des bans :");

                        serverSettings.getBans().stream().filter(ban -> ban != null).forEach(ban -> player.sendMessage("§7- §f" + ban));
                    }
                }
            } else
            {
                player.sendMessage(Constant.NO_PERMISSION_MESSAGE);
            }
        }
        return false;
    }

    private void sendHelp(Player player)
    {
        if (serverSettings.getHosts().get(player.getName()).equals(HostRank.MAIN))
        {
            player.sendMessage("§7• §d/§fh op §d<joueur> §8» §7Ajouter un hôte secondaire.");
            player.sendMessage("§7• §d/§fh deop §d<joueur> §8» §7Retirer un hôte secondaire.");
            player.sendMessage("§7• §d/§fh wl on/off §8» §7Activer/désactiver l'accès au serveur.");
            player.sendMessage("§7• §d/§fh wl list §8» §7Afficher la liste  des joueurs autorisés.");
            player.sendMessage("§7• §d/§fh wl clear §8» §7Vider la liste des joueurs autorisés.");
        }

        player.sendMessage("§7• §d/§fsay §d<message> §8» §7Envoyer un message en tant qu'hôte.");
        player.sendMessage("§7• §d/§fh oplist §8» §7Afficher la liste des hôtes secondaires.");
        player.sendMessage("§7• §d/§fh kick §d<joueur> §8» §7Éjecter un joueur.");
        player.sendMessage("§7• §d/§fh ban §d<joueur> §8» §7Bannir un joueur.");
        player.sendMessage("§7• §d/§fh unban §d<joueur> §8» §7Met fin à l'exclusion d'un joueur.");
        player.sendMessage("§7• §d/§fh banlist §8» §7Afficher la liste des bans.");
    }
}