;; Solution to Project Euler problem 56
;; http://projecteuler.net/index.php?section=problems&id=56
;;
;; A googol (10^100) is a massive number: one followed by one-hundred
;; zeros; 100^100 is almost unimaginably large: one followed by
;; two-hundred zeros. Despite their size, the sum of the digits in
;; each number is only 1.
;;
;; Considering natural numbers of the form, ab, where a, b < 100, what
;; is the maximum digital sum?
(ns com.stepniowski.euler-037)

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)))

(defn digital-sum [n]
  "Returns a sum of digits in number n"
  (reduce + (digits n)))

(defn geometric-sequence [a]
  "Returns a lazy sequence of 1, a, a^2, a^3, ..., a^n, ..."
  (iterate #(* a %) 1))


  
(defn solution []
  (
  
