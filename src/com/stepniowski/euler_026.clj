;; Solution to Project Euler problem 26
;; http://projecteuler.net/index.php?section=problems&id=26
;;
;; A unit fraction contains 1 in the numerator. The decimal
;; representation of the unit fractions with denominators 2 to 10 are
;; given:
;;
;; 1/2	= 	0.5
;; 1/3	= 	0.(3)
;; 1/4	= 	0.25
;; 1/5	= 	0.2
;; 1/6	= 	0.1(6)
;; 1/7	= 	0.(142857)
;; 1/8	= 	0.125
;; 1/9	= 	0.(1)
;; 1/10	= 	0.1
;;
;; Where 0.1(6) means 0.166666..., and has a 1-digit recurring
;; cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
;;
;; Find the value of d 1000 for which 1/d contains the longest
;; recurring cycle in its decimal fraction part.
;;
;; NOTE:
;; Let n = p * 2^n * 5^m, where k is coprime to 2 and 5.
;; The length of recussing cycle of n in base 10 is the
;; multiplicative order of 10 (mod p).
;;
;; See http://mathworld.wolfram.com/DecimalExpansion.html for more.
(ns com.stepniowski.euler-026)

(defn multiplicative-order [k n]
  "Returns the multiplicative order of k modulo n, defined as the
  smallest integer m such that k^m === 1 (mod n).
  k and n must be coprime."
  (if (or (= n 1) (= k 1))
    0
    (loop [m 1 pow k]
      (cond
       (= (mod pow n) 1) m
       true (recur (inc m) (* pow k))))))

(defn remove-factors [n factors]
  "Returns the number n with each factor from factors removed"
  (cond
   (empty? factors) n
   (zero? (rem n (first factors))) (recur (quot n (first factors)) factors)
   true (recur n (rest factors))))

(defn recurring-expansion-length [n]
  "Returns the length of recurring decimal expansion of number n"
  (multiplicative-order 10 (remove-factors n [2 5])))

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
  (second (reduce max-seq (map #(vector (recurring-expansion-length %) %)
			       (range 1 1000)))))