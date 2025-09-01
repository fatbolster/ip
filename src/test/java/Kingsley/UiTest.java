package Kingsley;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {

    @Test
    public void testShowGreeting() {
        Ui ui = new Ui();

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));

        try {
            ui.showGreeting();
        } finally {
            System.setOut(old);
        }

        String out = buf.toString().replace("\r\n", "\n");
        assertTrue(out.contains("Hello! I'm Kingsley."));
        assertTrue(out.contains("What can I do for you?"));

    }

    @Test
    public void testShowBye() {
        Ui ui = new Ui();

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));

        try {
            ui.showBye();
        } finally {
            System.setOut(old);
        }

        String out = buf.toString().replace("\r\n", "\n");
        assertTrue(out.contains(" Bye! Hope to see you again" +
                " soon!"));

    }



};


