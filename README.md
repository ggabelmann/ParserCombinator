# ParserCombinator

A POC implementation of a parser combinator.
Inspired by https://fsharpforfunandprofit.com/posts/understanding-parser-combinators

From wikipedia: "A parser is a function accepting strings as input and returning some structure as output, typically a parse tree or a set of indices representing locations in the string where parsing stopped successfully.
Parser combinators enable a recursive descent parsing strategy that facilitates modular piecewise construction and testing."

In my words, a ParserCombinator scans a String, left to right, and builds a tree of matches.
At the same time, the tree can be pruned and/or mapped to other domain-specific objects.

This ParserCombinator can work with any input, not just Strings.
So, for example, it could scan an array of bytes and, if successful, return a `Result<Image>` object.

## Example

See MathTest for an example of parsing the input "12 +  21 " or "112 -  221 " and getting back the correct integers.

## Future

Better error handling.
