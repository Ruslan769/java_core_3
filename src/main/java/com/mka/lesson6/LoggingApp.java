package com.mka.lesson6;

import java.util.logging.*;

public class LoggingApp {

    public static final Logger LOGGER = Logger.getLogger(LoggingApp.class.getName());
    public static final Handler handler = new ConsoleHandler();
    public static final Formatter FORMATTER = new MyFormat();

    static {
        LOGGER.setFilter(new MyFilter());
        handler.setLevel(Level.FINEST);
        handler.setFormatter(FORMATTER);
        LOGGER.addHandler(handler);
    }

    public static void main(String[] args) {
        /*LOGGER.log(Level.SEVERE, "Системная ошибка!");
        LOGGER.log(Level.INFO, "Все хорошо!");*/
        method1(2);
        method1(2, 0, 8);
    }

    public static void method1(int a) {
        LOGGER.log(Level.SEVERE, "Парамерт {0}", a);
    }

    public static void method1(int a, int b, int c) {
        LOGGER.log(Level.WARNING, "Парамерт {0}, {1}, {2}", new Integer[]{a, b, c});
    }
}

class MyFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        if (record.getLevel().intValue() > Level.WARNING.intValue()) {
            return true;
        }
        return false;
    }
}

class MyFormat extends Formatter {

    @Override
    public String format(LogRecord record) {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("<html><head></head>");
        buffer.append("<body>");
        buffer.append(record.getMessage());
        buffer.append("</body>");
        buffer.append("</html>");
        return buffer.toString();
    }
}

