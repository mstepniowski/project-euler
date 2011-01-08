;; Solution to Project Euler problem 23
;; http://projecteuler.net/index.php?section=problems&id=23
;;
;; A perfect number is a number for which the sum of its proper
;; divisors is exactly equal to the number. For example, the sum of
;; the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which
;; means that 28 is a perfect number.
;;
;; A number n is called deficient if the sum of its proper divisors is
;; less than n and it is called abundant if this sum exceeds n.
;;
;; As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the
;; smallest number that can be written as the sum of two abundant
;; numbers is 24. By mathematical analysis, it can be shown that all
;; integers greater than 28123 can be written as the sum of two
;; abundant numbers. However, this upper limit cannot be reduced any
;; further by analysis even though it is known that the greatest
;; number that cannot be expressed as the sum of two abundant numbers
;; is less than this limit.
;;
;; Find the sum of all the positive integers which cannot be written
;; as the sum of two abundant numbers.
(ns com.stepniowski.euler-023
  (:require [clojure.set :as set]))

(defn divisible-by? [n candidate]
  (zero? (rem n candidate)))

(defn prime-factors [n]
  "Returns the prime factors of number n"
  (let [q (Math/sqrt n)]
    (loop [n n
	   d 2
	   result []]
      (cond
       (> d q) (conj result n)
       (= n d) (conj result n)
       (divisible-by? n d) (recur (/ n d) d (conj result d))
       :else (recur n (inc d) result)))))

(defn sum-of-divisors-prime [[p k]]
  "Returns the sum of divisors of number p^k, where p - prime

  See http://mathschallenge.net/index.php?section=faq&ref=number/sum_of_divisors"
  (/ (dec (int (Math/pow p (inc k))))
     (dec p)))
  
(defn sum-of-divisors [n]
  "Returns the sum of divisors of number n"
  (let [factors (frequencies (prime-factors n))]
    (reduce * (map sum-of-divisors-prime factors))))

(defn abundant-number? [n]
  "Returns true if n is an abundant number, false otherwise"
  (> (sum-of-divisors n) (+ n n)))

(defn solution []
  (let [candidates (set (range 1 28124))
	abundant-numbers (filter abundant-number? (range 12 28124))
	abundant-sums (set (for [x abundant-numbers y abundant-numbers
				 :while (<= y x)]
			     (+ x y)))]
    (reduce + (set/difference candidates abundant-sums))))