;; Solution to Project Euler problem 25
;; http://projecteuler.net/index.php?section=problems&id=25
;;
;; The Fibonacci sequence is defined by the recurrence relation:
;;
;; F_n = F_{n-1} + F_{n-2}, where F_1 = 1 and F_2 = 1.
;; Hence the first 12 terms will be:
;;
;; F_1 = 1
;; F_2 = 1
;; F_3 = 2
;; F_4 = 3
;; F_5 = 5
;; F_6 = 8
;; F_7 = 13
;; F_8 = 21
;; F_9 = 34
;; F_10 = 55
;; F_11 = 89
;; F_12 = 144
;; The 12th term, F_12, is the first term to contain three digits.
;;
;; What is the first term in the Fibonacci sequence to contain 1000 digits?
(ns com.stepniowski.euler-025)

(defn next-fibonacci-number [[a b]]
  "Given [F_{n-2}, F_{n-1}] returns [F_{n-1}, F_n]."
  [b (+ a b)])

(defn fibonacci-numbers []
  (map first (iterate next-fibonacci-number [1 1])))

(defn solution []
  (let [index-digits (map-indexed #(vector %1 (count (str %2))) (fibonacci-numbers))]
    (inc (first (first (drop-while #(< (second %1) 1000) index-digits))))))
