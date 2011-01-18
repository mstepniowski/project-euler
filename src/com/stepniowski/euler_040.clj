;; Solution to Project Euler problem 40
;; http://projecteuler.net/index.php?section=problems&id=40
;;
;; An irrational decimal fraction is created by concatenating the positive integers:
;;
;; 0.123456789101112131415161718192021...
;;
;; It can be seen that the 12th digit of the fractional part is 1.
;;
;; If dn represents the nth digit of the fractional part, find the value of the following expression.
;;
;; d_1 * d_10 * d_100 * d_1000 * d_10000 * d_100000 * d_1000000
(ns com.stepniowski.euler-040)

(defn digits [n]
  "Returns the sequence of digits of number n"
  (map #(Integer/parseInt (str %)) (str n)))

(defn decimal-fraction-digits []
  "Returns the lazy sequence of digits in irrational fraction created
   by concatenating the positive integers"
  (mapcat #(digits (inc %)) (range)))

(defn solution []
  (reduce * (take 7 (map #(nth (decimal-fraction-digits) (dec %)) (iterate #(* 10 %) 1)))))