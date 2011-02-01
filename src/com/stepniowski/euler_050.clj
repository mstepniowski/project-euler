;; Solution to Project Euler problem 50
;; http://projecteuler.net/index.php?section=problems&id=50
;;
;; The prime 41, can be written as the sum of six consecutive primes:
;;
;; 41 = 2 + 3 + 5 + 7 + 11 + 13
;;
;; This is the longest sum of consecutive primes that adds to a prime
;; below one-hundred.
;;
;; The longest sum of consecutive primes below one-thousand that adds
;; to a prime, contains 21 terms, and is equal to 953.
;;
;; Which prime, below one-million, can be written as the sum of the
;; most consecutive primes?
(ns com.stepniowski.euler-050
  (:use clojure.contrib.lazy-seqs))

(defn prime? [n]
  (and (> n 1)
       (let [k (Math/sqrt n)]
	 (not-any? #(zero? (rem n %)) (take-while #(<= % k) primes)))))

(defn max-range [f a b]
  "Returns range [x y], where a <= x < y <= b, (f a b) is non-nil and y - x is maximized."
  (loop [x a max-length 1 best-result nil]
    (if (>= max-length (- b x))
      best-result
      (let [r (map #(vector x %) (range b (+ x max-length -1) -1))
	    v (some #(if (apply f %) %) r)]
	(recur (inc x) (if v (- (second v) (first v)) max-length) (if v v best-result))))))

(defn solution []
  ;; 1000000 is sadly just an arbitrary limit for a prime sum
  (let [prime-sums (vec (take-while #(< % 1000000) (reductions +  primes)))
	prime-below-million (fn [x y] (let [p (- (get prime-sums y) (get prime-sums x))]
					(and (< p 1000000) (prime? p))))
	[x y] (max-range prime-below-million 0 (dec (count prime-sums)))]
    (- (get prime-sums y) (get prime-sums x))))

