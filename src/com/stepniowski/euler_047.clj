;; Solution to Project Euler problem 47
;; http://projecteuler.net/index.php?section=problems&id=47
;;
;; The first two consecutive numbers to have two distinct prime factors are:
;;
;; 14 = 2 * 7
;; 15 = 3 * 5
;;
;; The first three consecutive numbers to have three distinct prime factors are:
;;
;; 644 = 2^2 * 7 * 23
;; 645 = 3 * 5 * 43
;; 646 = 2 * 17 * 19.
;;
;; Find the first four consecutive integers to have four distinct
;; primes factors. What is the first of these numbers?
(ns com.stepniowski.euler-047
  (:use clojure.contrib.math))

(defn prime-factors
  "Returns the prime factors of number num."
  [num]
  (let [q (Math/sqrt num)]
    (loop [n num
	   d 2
	   result []]
      (cond
       (> d q) (conj result n)
       (= n d) (conj result n)
       (zero? (rem n d)) (recur (/ n d) d (conj result d))
       :else (recur n (inc d) result)))))

(defn factors [n]
  (set (map (fn [[x y]] (expt x y))
	    (frequencies (prime-factors n)))))

(defn non-overlapping [& sets]
  "Returns true if the sets are not overlapping, ie. have no common elements."
  (= (reduce + (map count sets))
     (count (apply clojure.set/union sets))))

(defn solution []
  (let [factors-seq (map factors (iterate inc 644))
	fours (partition 4 1 factors-seq)
	found (some #(if (and (apply = 4 (map count %)) (apply non-overlapping %)) %)
		    fours)]
    (reduce * (first found))))

