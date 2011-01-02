;; Solution to Project Euler problem 4
;; http://projecteuler.net/index.php?section=problems&id=4
;;
;; A palindromic number reads the same both ways. The largest palindrome made
;; from the product of two 2-digit numbers is 9009 = 91 * 99.
;;
;; Find the largest palindrome made from the product of two 3-digit numbers.
(ns com.stepniowski.euler-004
  (:require clojure.string))

(defn products [min max]
  (loop [result []
	 i min]
    (if (< i max)
      (recur (concat result (map #(* i %) (range min max)))
	     (+ 1 i))
      result)))

(defn palindrome? [number]
  (let [number-str (str number)]
	(= number-str (clojure.string/reverse number-str))))

(defn solution []
  (reduce max (filter palindrome? (products 100 999))))
