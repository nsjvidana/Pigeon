package anonymousacid.pigeon.commands.test;

import static anonymousacid.pigeon.McIf.mc;
import static anonymousacid.pigeon.McIf.player;
import static anonymousacid.pigeon.McIf.world;

import java.util.List;

import org.lwjgl.input.Mouse;

import anonymousacid.pigeon.client.fakeentities.EntityPigeon2;
import anonymousacid.pigeon.gui.TestGui;
import anonymousacid.pigeon.gui.config.ConfigGui;
import anonymousacid.pigeon.utils.RenderUtils;
import anonymousacid.pigeon.utils.Utils;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//This command is just to test some code that might be added to future features
public class TestCommand extends CommandBase {
	
	public static TestCommand instance = new TestCommand();
	public static boolean commandOn = false;
	private ScaledResolution res;
	
	private boolean showGui = false;
	
	@Override
	public String getCommandName() {
		return "test";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		
		return "";
	}
	
	@Override
	public int compareTo(ICommand arg0) {
		
		return 0;
	}
	
	@Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

	@Override
	public List<String> addTabCompletionOptions(ICommandSender icommandsender, String[] strings, BlockPos blockpos ) {
		
		return null;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		
		return true;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] args) throws CommandException {
		if (icommandsender instanceof EntityPlayer) {
			commandOn = !commandOn;
			if(commandOn) MinecraftForge.EVENT_BUS.register(this); else MinecraftForge.EVENT_BUS.unregister(this);
			
			EntityPigeon2 pigeon = new EntityPigeon2(world(), 0.7, 0.05);
			Utils.spawnEntity(pigeon, player().posX, player().posY, player().posZ);
		}
	}
	
	@SubscribeEvent
	public void onTick(RenderGameOverlayEvent.Post e) {
		if(e.type !=  RenderGameOverlayEvent.ElementType.ALL)
			return;
	}
}
