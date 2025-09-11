package kingsley;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    class StorageStub extends Storage {
        private boolean saved = false;
        private ArrayList<Task> lastSaved;

        public StorageStub() {
            super("test1.txt");
        }

        @Override
        public ArrayList<Task> load() {
            return new ArrayList<>(List.of(
                    new Todo("finish ippt"),
                    new Deadline("finish watching cs2103 lecture", LocalDateTime.of(2021, 8, 7 , 18, 00)),
                    new Event("NUS Hack N Roll", LocalDateTime.of(2021, 8, 29, 19, 00),
                            LocalDateTime.of(2021, 8, 30, 20, 00))));
        }

        @Override
        public void save(ArrayList<Task> tasks) {
            this.saved = true;
            this.lastSaved = new ArrayList<>(tasks);

        }



    }

    class UiStub extends Ui {
        private boolean markHandlerCalled = false;
        private boolean eventHandlerCalled = false;

        @Override
        public String showMark(Task t) {
            markHandlerCalled = true;
            return super.showMark(t);
        }


        @Override
        public String showEvent(Event e, int taskCount) {
            eventHandlerCalled = true;
            return super.showEvent(e, taskCount);
        }
    }

    @Test
    public void testParseMark() throws KingsleyException {
        StorageStub storageMock = new StorageStub();
        UiStub uiMock = new UiStub();
        TaskList t = new TaskList(storageMock.load());

        Parser.parseMark("2", t, storageMock, uiMock);

        Task taskNumberTwo = t.get(1);
        assertEquals("X", taskNumberTwo.getStatusIcon());

        assertEquals(true , uiMock.markHandlerCalled);

        assertThrows(KingsleyException.class, () -> Parser.parseMark("-1", t, storageMock, uiMock));
        assertThrows(KingsleyException.class, () -> Parser.parseMark("5", t, storageMock, uiMock));

    }

    @Test
    public void testParseEvent() throws KingsleyException {
        StorageStub storageMock = new StorageStub();
        UiStub uiMock = new UiStub();
        TaskList t = new TaskList(storageMock.load());

        int sizeBeforeAddition = t.size();
        Parser.parseEvent("Tiktok TechJam /from 21/02/2024 1800 /to 25/02/2024 1900", t, storageMock, uiMock);

        assertEquals(sizeBeforeAddition + 1, t.size());
        assertEquals(true, uiMock.eventHandlerCalled);
        assertEquals(true, storageMock.saved);

        Task lastTask = t.get(t.size() - 1);

        assertEquals(true, lastTask instanceof Event);
        assertThrows(KingsleyException.class, () -> Parser.parseMark("Tiktok TechJam /from /to 25/02/2024 1900", t, storageMock, uiMock));

        assertThrows(KingsleyException.class, () -> Parser.parseMark("Tiktok TechJam /from 25/02/2024 1900 /to 25/02/2024 1900", t, storageMock, uiMock));


        assertThrows(KingsleyException.class, () -> Parser.parseMark("/from 25/02/2024 1900 /to 25/02/2024 1900", t, storageMock, uiMock));
        assertThrows(KingsleyException.class, () -> Parser.parseMark("/to 25/02/2024 1900", t, storageMock, uiMock));




    }



}