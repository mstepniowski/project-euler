;; Solution to Project Euler problem 37
;; http://projecteuler.net/index.php?section=problems&id=37
;;
;; The number 3797 has an interesting property. Being prime itself, it
;; is possible to continuously remove digits from left to right, and
;; remain prime at each stage: 3797, 797, 97, and 7. Similarly we can
;; work from right to left: 3797, 379, 37, and 3.
;;
;; Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
;;
;; NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
(ns com.stepniowski.euler-037)

(defn prime? [n]
  (if (< n 2)
    false
    (not-any? #(zero? (rem n %))
	      (range 2 (min (inc (Math/sqrt n)) n)))))

(defn right-truncatable-prime? [n]
  (every? prime? (take-while #(> % 0) (iterate #(quot % 10) n))))

(defn left-truncatable-primes-helper [primes]
  "Given a list of primes, returns a sequence of left truncatable
   primes that can be constructed by adding a new digit to them."
   (filter prime? (for [x (range 1 10) y primes] (BigInteger. (str x y)))))

(defn left-truncatable-primes
  ([] (left-truncatable-primes [2 3 5 7]))
  ([primes]
     (lazy-seq
      (concat primes
	      (let [new-truncatable-primes (left-truncatable-primes-helper primes)]
		(if (empty? new-truncatable-primes)
		  []
		  (left-truncatable-primes new-truncatable-primes)))))))

(defn solution []
  ;; We know that there are exactly 11 truncatable primes (except 2, 3, 5 and 7)
  (take 11 (filter #(> % 10) (filter right-truncatable-prime?
				     (left-truncatable-primes)))))