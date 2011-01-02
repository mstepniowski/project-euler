# Project Euler

Solutions to [Project Euler](http://projecteuler.net/) problem set in Clojure, written by [Marek Stepniowski](http://stepniowski.com/) as a way to learn the language.

## Usage

1. Install [Leiningen](https://github.com/technomancy/leiningen).
2. Run `lein deps` to download the dependencies.
3. Run `lein repl` to run a Read-Eval-Print Loop.
4. Require a proper namespace for the solution (example: `(require 'com.stepniowski.euler-015)` for solution to problem 15).
5. Run `(ns/solution)` where `ns` is the required namespace (example: `(com.stepniowski.euler-015/solution)`). It should print a solution to the chosen problem.

Of course the fun part is writing your own solutions to the Project Euler problems and then comparing them to solutions published here (you can find the sources inside the `src/com/stepniowski` directory), on [Clojure Companion Cube](http://clojure.roboloco.net/?page_id=2) or [clojure-euler wiki](http://clojure-euler.wikispaces.com/).

## License

The MIT License

Copyright (C) 2010 Marek Stepniowski

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

