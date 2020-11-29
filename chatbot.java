package chatbot;

import java.io.File;
import org.alicebot.ab.*;
import org.alicebot.ab.utils.IOUtils;

public class ChatBot {

    private static final boolean TRACE_MODE = false;
    static String botName = "super";
 
    public static void main(String[] args) {
        try {
 
            String resourcesPath = getResourcesPath();
            System.out.println(resourcesPath);
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot("super", resourcesPath);
            bot.writeAIMLFiles();
            Chat chatSession = new Chat(bot);
            String textLine = "";
 
            Runtime.getRuntime().exec("clear");
            while(true) {
                System.out.print("Human : ");
                textLine = IOUtils.readInputTextLine();
                if ((textLine == null) || (textLine.length() < 1))
                    textLine = MagicStrings.null_input;
                else {
                    String request = textLine;
                    String response = chatSession.multisentenceRespond(request);
                    System.out.println("Robot : " + response);
                    if(request.toLowerCase().equals("bye"))
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path;
        return resourcesPath;
    }
}



/*
<?xml version="1.0" encoding="UTF-8"?>
<aiml>
    <category><pattern>WHAT IS JAVA</pattern>
        <template>Java is a programming language.</template>
    </category>
    <category><pattern>WHAT IS CHAT BOT</pattern>
        <template>Chatbot is a computer program designed to simulate
        conversation with human users, especially over the Internet.</template>
    </category>
    <category><pattern>WHAT IS INDIA</pattern>
        <template>wonderful country.</template>
    </category>
</aiml>
*/
