package edu.moravian.Delivery;

import edu.moravian.APP.GameStatus;
import edu.moravian.DataStorage.Secrets;
import edu.moravian.exceptions.SecretsException;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

public class AnagramBotDriver
{
    public static void main(String[] args) throws SecretsException {

        String secretTokenName = "220_Discord_Token";
        String secretTokenKeyPair = "DISCORD_TOKEN";

        String secretChannelName = "Discord_Channel";
        String secretChannelKeyPair = "CHANNEL_NAME";

        Secrets secrets = new Secrets();
        String token = secrets.getSecret(secretTokenName, secretTokenKeyPair);
        String channelName = secrets.getSecret(secretChannelName, secretChannelKeyPair);

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
