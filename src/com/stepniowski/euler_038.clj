;; Solution to Project Euler problem 38
;; http://projecteuler.net/index.php?section=problems&id=38
;;
;; Take the number 192 and multiply it by each of 1, 2, and 3:
;;
;; 192 * 1 = 192
;; 192 * 2 = 384
;; 192 * 3 = 576
;;
;; By concatenating each product we get the 1 to 9 pandigital,
;; 192384576. We will call 192384576 the concatenated product of 192
;; and (1,2,3)
;;
;; The same can be achieved by starting with 9 and multiplying by 1,
;; 2, 3, 4, and 5, giving the pandigital, 918273645, which is the
;; concatenated product of 9 and (1,2,3,4,5).
;;
;; What is the largest 1 to 9 pandigital 9-digit number that can be
;; formed as the concatenated product of an integer with (1,2, ... ,n)
;; where n > 1?
(ns com.stepniowski.euler-038
  (:use clojure.contrib.combinatorics))

(defn pandigital-numbers [n]
  "Returns a sequence of pandigital numbers of length n"
  (map #(Integer/parseInt (apply str %)) (permutations (take n "123456789"))))

(defn split-digits [k n]
  "Returns the first k digits and the rest of number n."
  (letfn [(digits->number [digits] (Integer/parseInt (apply str digits)))]
    (map digits->number (split-at k (str n)))))

(defn prodcat?
  "Returns true if nine-digit number n is a concatenated product of
   a k-digit integer with (1,2, ..., n) where n > 1, nil otherwise.

   If k is not provided, tries for all sensible k."
  ([k n]
     (let [[i _] (split-digits k n)
	   prodcat (reductions concat (map #(str (* i %)) (iterate inc 1)))]
       (some #(= (apply str %) (str n)) (take-while #(<= (count %) 9) prodcat))))
  ([n]
     (some identity (map #(prodcat? % n) (range 1 5)))))
  