;; Solution to Project Euler problem 49
;; http://projecteuler.net/index.php?section=problems&id=49
;;
;; The arithmetic sequence, 1487, 4817, 8147, in which each of the
;; terms increases by 3330, is unusual in two ways: (i) each of the
;; three terms are prime, and, (ii) each of the 4-digit numbers are
;; permutations of one another.
;;
;; There are no arithmetic sequences made up of three 1-, 2-, or
;; 3-digit primes, exhibiting this property, but there is one other
;; 4-digit increasing sequence.
;;
;; What 12-digit number do you form by concatenating the three terms
;; in this sequence?
(ns com.stepniowski.euler-049
  (:use clojure.contrib.lazy-seqs clojure.contrib.combinatorics))

(defn safe-parse-long [s]
  (Long/parseLong (apply str (drop-while #(= \0 %) s))))

(defn long-permutations [n]
  (map #(safe-parse-long (apply str %)) (permutations (str n))))

(defn prime? [n]
  (and (> n 1)
       (let [k (Math/sqrt n)]
	 (not-any? #(zero? (rem n %)) (take-while #(<= % k) primes)))))

(defn candidate-seq [n]
  "Returns a list of candidate sequences for arithmetic-prime-seq, starting with n"
  (map #(vector n % (+ % (- % n))) (filter #(> % n) (long-permutations n))))

(defn arithmetic-prime-seq? [s]
  "Returns true if s is an arithmetic sequence of primes."
  (and (every? prime? s)
       (apply = (map (fn [[x y]] (- y x)) (partition 2 1 s)))
       (apply not= s)
       (apply = (map #(sort (str %)) s))))
			 
(defn solution []
  (let [four-digit-primes (take-while #(< % 10000) (drop-while #(< % 1000) primes))
	arithmetic-prime-seqs (filter arithmetic-prime-seq? (mapcat candidate-seq four-digit-primes))
	;; We know that there is only one solution apart from [1487 4817 8147] from the problem statement
	s (first (remove #(= (first %) 1487) arithmetic-prime-seqs))]
    (safe-parse-long (apply str s))))

