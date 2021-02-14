package com.counter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordCounterTest {
    private final String url = "http://localhost";
    private final WordCounter wordCounter = new WordCounter(getMockUrl(url));

    public WordCounterTest() throws IOException {
    }

    @DisplayName("When before processing it contains 0 total words")
    @Test
    void testWordCounterBeforeProcessing() {
        assertEquals(wordCounter.getTotalCounter(), 0L);
    }

    @DisplayName("When after processing it contains 9 total words")
    @Test
    void testWordCounterAfterProcessing() throws IOException {
        wordCounter.process();
        assertEquals(wordCounter.getTotalCounter(), 9L);
    }

    public static URL getMockUrl(final String filename) throws IOException {
        final String testWords = "Один two три four.\n Be open-minded! ВОВ 1945 year";
        final URLConnection mockUrlConnection = mock(URLConnection.class);
        final InputStream inputStream = new ByteArrayInputStream(testWords.getBytes());

        when(mockUrlConnection.getInputStream()).thenReturn(inputStream);

        final URLStreamHandler stubUrlHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(final URL u) throws IOException {
                return mockUrlConnection;
            }
        };

        final URL url = new URL("http://localhost", "localhost", 80, "", stubUrlHandler);

        return url;
    }
}
