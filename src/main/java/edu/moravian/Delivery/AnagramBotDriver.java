package edu.moravian.Delivery;

import edu.moravian.APP.GameStatus;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

public class AnagramBotDriver
{
    public static void main(String[] args)
    {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");
	String channelName = Dotenv.load().get("CHANNEL_NAME");
        JDA api = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
        CommandManager commandManager = new CommandManager();

        api.addEventListener(new ListenerAdapter()
        {
            @Override
            public void onMessageReceived(@NotNull MessageReceivedEvent event)
            {
                if (event.getAuthor().isBot() || !event.getChannel().getName().equals(channelName))
                    return;

                String sender = event.getAuthor().getName();
                String message = event.getMessage().getContentRaw();

                if (!CommandFormatter.isPrefix(message, "!"))
                {
                    return;
                }

                String cmd = CommandFormatter.getCommandSignature(message);

                try {
                    String response = commandManager.executeCommand(cmd.toLowerCase(), sender);
                    event.getChannel().sendMessage(response).queue();
                } catch (Exception e) {
                    String anagram = "";
                    if (commandManager.status() == GameStatus.IN_PROGRESS)
                    {
                        anagram = "\nAnagram: " + commandManager.getAnagram();
                    }
                    event.getChannel().sendMessage(e.getMessage() + anagram).queue();
                }
            }
        });
    }
}
