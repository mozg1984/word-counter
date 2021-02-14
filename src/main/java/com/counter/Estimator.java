package com.counter;

import com.options.Options;

class Estimator {
    public static void main(String args[]) {
        Options options = new Options().parse(args);
        String url = options.get("url");
        System.out.println("Url: " + url);

        WordCounter wordCounter = new WordCounter(url);
        long startTime = System.currentTimeMillis();
        wordCounter.process();

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime) + " ms");
        wordCounter.printResult();
    }
}
