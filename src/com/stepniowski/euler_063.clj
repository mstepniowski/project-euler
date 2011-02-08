;; Solution to Project Euler problem 63
;; http://projecteuler.net/index.php?section=problems&id=63
;;
;; The 5-digit number, 16807 = 7^5, is also a fifth power. Similarly, the
;; 9-digit number, 134217728 = 8^9, is a ninth power.
;;
;; How many n-digit positive integers exist which are also an nth power?
;;
;; SOLUTION:
;; There are two constraints for the solution space a^n we want to search:
;;
;; 1. As 10^n always gives a number with n+1 digits and so do larger
;;    bases. That's why we don't need to check bases greater than 9.
;; 2. 9^n, when n > 21 always gives a number with less than n digits and
;;    so do lesser bases. That's why we don't need to check exponents
;;    greater than 21.
(ns com.stepniowski.euler-063
  (:use clojure.contrib.math))

(defn solution []
  (count (for [a (range 1 10)
	       n (range 1 22)
	       :when (= (count (str (expt a n))) n)]
	   1)))
