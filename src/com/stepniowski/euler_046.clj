;; Solution to Project Euler problem 46
;; http://projecteuler.net/index.php?section=problems&id=46
;;
;; It was proposed by Christian Goldbach that every odd composite
;; number can be written as the sum of a prime and twice a square.
;;
;; 9 = 7 + 2 * 1^2
;; 15 = 7 + 2 * 2^2
;; 21 = 3 + 2 * 3^2
;; 25 = 7 + 2 * 3^2
;; 27 = 19 + 2 * 2^2
;; 33 = 31 + 2 * 1^2
;;
;; It turns out that the conjecture was false.
;;
;; What is the smallest odd composite that cannot be written as the
;; sum of a prime and twice a square?
(ns com.stepniowski.euler-046
  (:use clojure.contrib.lazy-seqs))

(defn goldbach-numbers-upto [n]
  "Returns a set of composite numbers that can be written as the
   sum of a prime and twice a square up to n."
  (let [primes-upto-n (take-while #(<= % n) primes)
	twice-squares-upto-n (take-while #(<= % n) (map #(* 2 % %) (iterate inc 1)))
	sums (set (for [x twice-squares-upto-n 
			y (take-while #(<= (+ % x) n) primes-upto-n)]
		    (+ x y)))]
    (clojure.set/difference sums (set primes-upto-n))))
    
(defn solution []
  ;; Yeah, the 10000 boundary is totally arbitrary
  (let [primes-and-goldbach-numbers (clojure.set/union
				     (take-while #(<= % 10000) primes)
				     (goldbach-numbers-upto 10000))]
    (some #(if (not (primes-and-goldbach-numbers %)) %) (iterate #(+ 2 %) 3))))