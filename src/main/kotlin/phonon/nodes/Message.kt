/**
 * Ingame player message printing manager
 * 
 */

package phonon.nodes

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

public object Message {

    public val PREFIX = "[Nodes]"
    public val COL_MSG_LEGACY = ChatColor.AQUA
    val COL_MSG = NamedTextColor.AQUA
    public val COL_ERROR = ChatColor.RED

    // print generic message to chat
    public fun print(sender: Any?, s: String) {
		if ( sender === null ) {
            System.out.println("${PREFIX} Message called with null sender: ${s}")
            return
		}

        val msg = "${COL_MSG_LEGACY}${s}"
        if ( sender is Player ) {
            (sender as Player).sendMessage(msg)
        }
        else {
            (sender as CommandSender).sendMessage(msg)
        }
    }

    // more elegant adventure api printing
    fun print(audience: Audience?, c: Component) {
        if (audience == null) {
            Nodes.logger?.info("Message called with null audience: $c")
            return
        }

        audience.sendMessage(c.color(COL_MSG))
    }

    // print error message to chat
    public fun error(sender: Any?, s: String) {
		if ( sender === null ) {
            System.out.println("${PREFIX} Message called with null sender: ${s}")
            return
		}

        val msg = "${COL_ERROR}${s}"
        if ( sender is Player ) {
            (sender as Player).sendMessage(msg)
        }
        else {
            (sender as CommandSender).sendMessage(msg)
        }
    }

    // wrapper around Bukkit.broadcast to send
    // messages to all players
    public fun broadcast(s: String) {
        val msg = "${COL_MSG_LEGACY}${s}"
        Bukkit.broadcast(Component.text(msg))
    }

    // print text to the player action bar
    public fun announcement(player: Player, s: String) {
        player.sendActionBar(Component.text(s));
    }
}