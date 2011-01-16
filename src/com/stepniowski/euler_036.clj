;; Solution to Project Euler problem 36
;; http://projecteuler.net/index.php?section=problems&id=36
;;
;; The decimal number, 585 = 10010010012 (binary), is palindromic in
;; both bases.
;;
;; Find the sum of all numbers, less than one million, which are
;; palindromic in base 10 and base 2.
;;
;; (Please note that the palindromic number, in either base, may not
;; include leading zeros.)
(ns com.stepniowski.euler-036)

(defn radix [n r]
  "Converts number n to string representation using radix"
  (loop [x n curr r result '()]
    (cond
     (= x 0) (apply str result)
     true (recur (quot x r) (* curr r) (conj result (rem x r))))))

(defn palindromic? [s]
  (= s (apply str (reverse s))))

(defn solution []
  (reduce + (filter (fn [n] (and (palindromic? (str n)) (palindromic? (radix n 2))))
		    (range 1 1000001))))