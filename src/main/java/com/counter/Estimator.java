package com.counter;

import com.options.Options;

import java.net.MalformedURLException;
import java.net.URL;

class Estimator {
    public static void main(String args[]) throws MalformedURLException {
        Options options = new Options().parse(args);
        String url = options.get("url");
        System.out.println("Url: " + url);

        WordCounter wordCounter = new WordCounter(new URL(url));
        long startTime = System.currentTimeMillis();
        wordCounter.process();

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime) + " ms");
        wordCounter.printResult();
    }
}
