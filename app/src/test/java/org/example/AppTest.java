
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void appHasAGreeting() {
        App app = new App();

        assertNotNull(
            app.getGreeting(),
            "App should have a greeting"
        );
    }

    @Test
    void greetingIsCorrect() {
        App app = new App();

        assertEquals(
            "Hello World!",
            app.getGreeting()
        );
    }
}