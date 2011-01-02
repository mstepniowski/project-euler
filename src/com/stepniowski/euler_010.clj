;; Solution to Project Euler problem 10
;; http://projecteuler.net/index.php?section=problems&id=10
;;
;; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;;
;; Find the sum of all the primes below two million.
(ns com.stepniowski.euler-010
  (:use clojure.contrib.lazy-seqs))

(defn solution []
  (let [primes-below-2m (take-while #(< % 2000000) primes)]
    (reduce + primes-below-2m)))
