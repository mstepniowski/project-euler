;; Solution to Project Euler problem 17
;; http://projecteuler.net/index.php?section=problems&id=17
;;
;; If the numbers 1 to 5 are written out in words: one, two, three, four, five
;; then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
;;
;; If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
;;
;; NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters
;; and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers
;; is in compliance with British usage.
(ns com.stepniowski.euler-017
  (:use clojure.test))

(def numbers- {1 "one"
	       2 "two"
	       3 "three"
	       4 "four"
	       5 "five"
	       6 "six"
	       7 "seven"
	       8 "eight"
	       9 "nine"
	       10 "ten"
	       11 "eleven"
	       12 "twelve"
	       13 "thirteen"
	       14 "fourteen"
	       15 "fifteen"
	       16 "sixteen"
	       17 "seventeen"
	       18 "eighteen"
	       19 "nineteen"
	       20 "twenty"
	       30 "thirty"
	       40 "forty"
	       50 "fifty"
	       60 "sixty"
	       70 "seventy"
	       80 "eighty"
	       90 "ninety"})

(defn verbatim [n]
  "Returns the verbatim representation of the number n"
  (cond
   (< n 20) (get numbers- n)
   (zero? (rem n 1000)) "one thousand"
   (zero? (rem n 100)) (str (verbatim (quot n 100)) " hundred")
   (>= n 100) (str (verbatim (quot n 100)) " hundred and " (verbatim (rem n 100)))
   (zero? (rem n 10)) (get numbers- n)
   :default (str (verbatim (* (quot n 10) 10)) "-" (verbatim (rem n 10)))))

(defn count-alpha [s]
  (count (filter #(Character/isLetter %) s)))

(defn solution []
  (reduce + (map #(count-alpha (verbatim %)) (range 1 1001))))