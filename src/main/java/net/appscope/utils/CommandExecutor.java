package net.appscope.utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by admin on 18/06/15.
 */
public class CommandExecutor {

    public static final Logger log = Logger.getLogger(CommandExecutor.class.getName());

    public static void execute(String command){
        StringBuilder sb = new StringBuilder();
        try {
            Process proc = new ProcessBuilder("/bin/bash", "-c", command).start();
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
            while ((s = stdError.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
        } catch (IOException e) {
            log.error(e);
        }
        log.info(sb.toString());
    }

}
