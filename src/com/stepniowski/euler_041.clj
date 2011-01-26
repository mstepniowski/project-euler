;; Solution to Project Euler problem 41
;; http://projecteuler.net/index.php?section=problems&id=41
;;
;; We shall say that an n-digit number is pandigital if it makes use
;; of all the digits 1 to n exactly once. For example, 2143 is a
;; 4-digit pandigital and is also prime.
;;
;; What is the largest n-digit pandigital prime that exists?
(ns com.stepniowski.euler-041
  (:use clojure.contrib.lazy-seqs clojure.contrib.combinatorics))

(defn pandigital-numbers [n]
  "Returns a sequence of pandigital numbers of length n"
  (map #(Integer/parseInt (apply str %)) (permutations (take n "123456789"))))

(defn prime? [n]
  (and (> n 1)
       (let [k (Math/sqrt n)]
	 (not-any? #(zero? (rem n %)) (take-while #(<= % k) primes)))))

(defn solution []
  (let [pandigital-primes (mapcat #(filter prime? (pandigital-numbers %)) (range 1 10))]
    (apply max pandigital-primes)))
