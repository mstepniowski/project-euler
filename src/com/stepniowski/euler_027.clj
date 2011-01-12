;; Solution to Project Euler problem 27
;; http://projecteuler.net/index.php?section=problems&id=27
;;
;; Euler published the remarkable quadratic formula:
;;
;; n^2 + n + 41
;;
;; It turns out that the formula will produce 40 primes for the
;; consecutive values n = 0 to 39. However, when n = 40,
;; 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly
;; when n = 41, 41² + 41 + 41 is clearly divisible by 41.
;;
;; Using computers, the incredible formula n^2 + 79n + 1601 was
;; discovered, which produces 80 primes for the consecutive values
;; n = 0 to 79. The product of the coefficients, 79 and 1601, is 126479.
;;
;; Considering quadratics of the form:
;;
;; n^2 + a * n + b, where |a|  1000 and |b|  1000
;; where |n| is the modulus/absolute value of n, e.g. |11| = 11 and |4| = 4
;;
;; Find the product of the coefficients, a and b, for the quadratic
;; expression that produces the maximum number of primes for
;; consecutive values of n, starting with n = 0.
(ns com.stepniowski.euler-027
  (:use clojure.contrib.lazy-seqs))

(defn prime? [n]
  (let [m (if (< n 0)
	    (Math/sqrt (- n))
	    (Math/sqrt n))]
    (loop [k 2]
      (cond
       (> k m) true
       (zero? (mod n k)) false
       true (recur (inc k))))))

(defn quadratic-seq [a b]
  "Returns the sequence of numbers produced by quadratic formula
  n^2 + a * n + b where n = 0, 1, 2, ..."
  (map #(+ (* % %) (* a %) b) (iterate inc 0)))

(defn max-seq [a b]
  "Returns the greater sequence, lexicographically"
  (loop [rest-a a rest-b b]
    (cond
     (empty? rest-a) (if (empty? rest-b) a b)
     (empty? rest-b) a
     (> (first rest-a) (first rest-b)) a
     (< (first rest-a) (first rest-b)) b
     true (recur (rest rest-a) (rest rest-b)))))

(defn solution []
  "Returns a * b (-1000 < a, b < 1000) for which formula n^2 + a * n +
  b produces the maximum number of primes for consecutive values of n,
  starting with n = 0."
  ;; When n = 0, n^2 + a * n + b = b, so b must be prime
  (let [first-primes (take-while #(< % 1000) primes)
	quadratic-seqs (for [b (concat first-primes (map - first-primes))
			     a (range -999 1000)]
			 [(count (take-while #(prime? %) (quadratic-seq a b))) a b])]
    (apply * (rest (reduce max-seq quadratic-seqs)))))