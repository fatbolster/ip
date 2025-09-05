package Kingsley;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {

    @Test
    public void testShowGreeting() {
        Ui ui = new Ui();

        String out = ui.showGreeting().replace("\r\n", "\n");
        assertTrue(out.contains("Hello! I'm Kingsley."));
        assertTrue(out.contains("What can I do for you?"));

    }

    @Test
    public void testShowBye() {
        Ui ui = new Ui();
        String out = ui.showBye().replace("\r\n", "\n");

        assertTrue(out.contains("Bye! Hope to see you again soon!"));

    }



};