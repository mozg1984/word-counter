# Unique word counter

Console utility for counting unique words by given url.
It supports words with unicode letters.
Expecting word format:

    <unicode-letter>+
    <unicode-letter>+-<unicode-letter>+
    <number>+

### Installation

Console utility requires [Apache Maven](https://maven.apache.org/) (in my case I was using v3.6.3_1) and Java v11 to run.

```sh
$ mvn clean install
```

Once done, run the utility by passing the url through the key `-url`:

```sh
$ java -cp target/word-counter-1.0.jar com.counter.Estimator -url <Your_URL>
```