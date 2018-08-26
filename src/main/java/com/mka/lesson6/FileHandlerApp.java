package com.mka.lesson6;

import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class FileHandlerApp {

    public static final String PATH_FILE = "./src/main/java/com/mka/lesson6/";
    public static final Logger LOGGER = Logger.getLogger(LoggingApp.class.getName());
    public static Handler HANDLER = null;

    static {
        try {
            HANDLER = new FileHandler(PATH_FILE + "error-%u.%g.json");
            HANDLER.setFormatter(new MyFormat());
            LOGGER.addHandler(HANDLER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        LOGGER.log(Level.SEVERE, "Нет данных в таблице 9");
    }

    private static class MyFormat extends Formatter {

        @Override
        public String format(LogRecord record) {
            final StringBuffer buffer = new StringBuffer();
            final Date date = new Date(record.getMillis());
            buffer.append("[");
            buffer.append("{");
            buffer.append("\"date\": \"" + date.toString() + "\",");
            buffer.append("\"message\": \"" + record.getMessage() + "\"");
            buffer.append("}");
            buffer.append("]");
            return buffer.toString();
        }
    }
}
