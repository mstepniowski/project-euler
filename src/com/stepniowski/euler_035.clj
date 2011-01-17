;; Solution to Project Euler problem 35
;; http://projecteuler.net/index.php?section=problems&id=35
;;
;; The number, 197, is called a circular prime because all rotations
;; of the digits: 197, 971, and 719, are themselves prime.
;;
;; There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17,
;; 31, 37, 71, 73, 79, and 97.
;;
;; How many circular primes are there below one million?
(ns com.stepniowski.euler-035
  (:use clojure.contrib.lazy-seqs))

(defn prime? [n]
  (if (< n 2)
    false
    (not-any? #(zero? (rem n %))
	      (range 2 (min (inc (Math/sqrt n)) n)))))

(defn rotate [n s]
  "Returns seq rotated by n elements.
  Examples:

  (rotate 1 [1 2 3 4]) -> [2 3 4 1]
  (rotate 2 [1 2 3 4]) -> [3 4 1 2]"
  (take (count s) (drop n (cycle s))))

(defn rotations [s]
  "Returns all possible rotations of finite sequence s"
  (map #(rotate % s) (range (count s))))

(defn circular-prime? [n]
  (let [circular-numbers (map #(Integer/parseInt (apply str %))
			      (rotations (str n)))]
    (every? prime? circular-numbers)))

(defn solution []
  (let [primes-below-1000000 (take-while #(< % 1000000) primes)]
    (count (filter circular-prime? primes-below-1000000))))