;; Solution to Project Euler problem 20
;; http://projecteuler.net/index.php?section=problems&id=20
;;
;; n! means n * (n + 1) * ... * 3 * 2 * 1
;;
;; Find the sum of the digits in the number 100!
(ns com.stepniowski.euler-020)

(defn fact [n]
  (reduce * (take n (iterate inc 1))))

(defn solution []
  (reduce + (map #(Integer/parseInt (str %))
		 (str (fact 100)))))