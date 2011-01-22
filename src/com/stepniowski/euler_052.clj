;; Solution to Project Euler problem 52
;; http://projecteuler.net/index.php?section=problems&id=52
;;
;; It can be seen that the number, 125874, and its double, 251748,
;; contain exactly the same digits, but in a different order.
;;
;; Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x,
;; and 6x, contain the same digits.
(ns com.stepniowski.euler-052)

(defn foo-number? [x]
  "Returns true if x, 2x, 3x, 4x, 5x and 6x contain the same digits"
  (apply = (map #(sort (str (* x %))) (range 1 7))))

(defn solution []
  (first (filter foo-number? (iterate inc 1))))