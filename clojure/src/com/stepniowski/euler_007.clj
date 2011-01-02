;; Solution to Project Euler problem 7
;; http://projecteuler.net/index.php?section=problems&id=7
;;
;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13
;; we can see that the 6th prime is 13.
;;
;; What is the 10001st prime number?
(ns com.stepniowski.euler-007
  (:use clojure.contrib.lazy-seqs))

(defn solution []
  (nth primes 10000))
