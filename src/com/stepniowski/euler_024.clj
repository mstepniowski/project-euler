;; Solution to Project Euler problem 24
;; http://projecteuler.net/index.php?section=problems&id=24
;;
;; A permutation is an ordered arrangement of objects. For example,
;; 3124 is one possible permutation of the digits 1, 2, 3 and 4. If
;; all of the permutations are listed numerically or alphabetically,
;; we call it lexicographic order. The lexicographic permutations of
;; 0, 1 and 2 are:
;;
;; 012, 021, 102, 120, 201, 210
;;
;; What is the millionth lexicographic permutation of the digits
;; 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
(ns com.stepniowski.euler-024
  (:use clojure.contrib.combinatorics))

(defn solution []
  (apply str (nth (permutations (range 10)) (dec 1000000))))