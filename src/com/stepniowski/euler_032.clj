;; Solution to Project Euler problem 32
;; http://projecteuler.net/index.php?section=problems&id=32
;;
;; We shall say that an n-digit number is pandigital if it makes use
;; of all the digits 1 to n exactly once; for example, the 5-digit
;; number, 15234, is 1 through 5 pandigital.
;;
;; The product 7254 is unusual, as the identity, 39 * 186 = 7254,
;; containing multiplicand, multiplier, and product is 1 through 9
;; pandigital.
;;
;; Find the sum of all products whose multiplicand/multiplier/product
;; identity can be written as a 1 through 9 pandigital.
;;
;; HINT: Some products can be obtained in more than one way so be sure
;; to only include it once in your sum.
(ns com.stepniowski.euler-032
  (:use clojure.contrib.combinatorics))

(defn pandigital-numbers [n]
  "Returns a sequence of pandigital numbers of length n"
  (map #(Integer/parseInt (apply str %)) (permutations (take n "123456789"))))

(defn product [n]
  "Given a 9-digit number n, tries to divide it into a|b|c so that a * b = c.
   If the number can be divided, returns c. Otherwise returns nil.

   There are only two unique ways to divide the number in this way:
   A * BBBB = CCCC
   AA * BBB = CCCC"
  (cond
   (= (* (quot n 100000000) (rem (quot n 10000) 10000)) (rem n 10000)) (rem n 10000)
   (= (* (quot n 10000000) (rem (quot n 10000) 1000)) (rem n 10000)) (rem n 10000)
   true nil))

(defn solution []
  (let [pandigital-products (into #{} (filter identity
					      (map product (pandigital-numbers 9))))]
    (reduce + pandigital-products)))
