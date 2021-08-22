Since Java 12

https://howtodoinjava.com/java12/collectors-teeing-example/

Returns a Collector that is a composite of two downstream collectors. 
Every element passed to the resulting collector is processed by both downstream collectors, then their results are merged using the specified merge function into the final result.

The resulting collector functions do the following:

    supplier: creates a result container that contains result containers obtained by calling each collector's supplier
    accumulator: calls each collector's accumulator with its result container and the input element
    combiner: calls each collector's combiner with two result containers
    finisher: calls each collector's finisher with its result container, then calls the supplied merger and returns its result.
