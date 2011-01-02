;; Solution to Project Euler problem 3
;; http://projecteuler.net/index.php?section=problems&id=3
;;
;; The prime factors of 13195 are 5, 7, 13 and 29.
;;
;; What is the largest prime factor of the number 600851475143 ?
(ns com.stepniowski.euler-003
  (:use clojure.contrib.lazy-seqs))

(defn prime-factors-of [n]
  (filter #(zero? (rem n %)) (take-while #(> n (* % %)) primes)))

(defn solution []
  (reduce max (prime-factors-of 600851475143)))
