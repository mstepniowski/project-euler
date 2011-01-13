;; Solution to Project Euler problem 30
;; http://projecteuler.net/index.php?section=problems&id=30
;;
;; Surprisingly there are only three numbers that can be written as
;; the sum of fourth powers of their digits:
;;
;; 1634 = 1^4 + 6^4 + 3^4 + 4^4
;; 8208 = 8^4 + 2^4 + 0^4 + 8^4
;; 9474 = 9^4 + 4^4 + 7^4 + 4^4
;; As 1 = 1^4 is not a sum it is not included.
;;
;; The sum of these numbers is 1634 + 8208 + 9474 = 19316.
;;
;; Find the sum of all the numbers that can be written as the sum of
;; fifth powers of their digits.
(ns com.stepniowski.euler-030
  (:use clojure.contrib.math))

(defn foo-number? [n]
  "Returns true if the number n is the sum of the fifth power of its digits"
  (let [digits (map #(Integer/parseInt (str %)) (str n))
	digit-sum (reduce + (map #(expt % 5) digits))]
    (and (not= n 1) ;; 1^5 is not a *sum*
	 (= digit-sum n))))
  
(defn solution []
  (let [max-number (* 6 (expt 9 5))]
    (reduce + (filter foo-number? (range 1 (inc max-number))))))