;; Solution to Project Euler problem 5
;; http://projecteuler.net/index.php?section=problems&id=5
;;
;; 2520 is the smallest number that can be divided by each
;; of the numbers from 1 to 10 without any remainder.
;;
;; What is the smallest positive number that is evenly divisible
;; by all of the numbers from 1 to 20?
(defn divisible-by? [n candidate]
  (zero? (rem n candidate)))

(defn factors [n]
  "Returns the prime factors of number n."
  (loop [result [] n n candidate 2]
    (cond
     (= n 1) result
     (divisible-by? n candidate) (recur (conj result candidate)
					(/ n candidate)
					candidate)
     (> candidate (* n n)) (conj result n)
     :else (recur result n (inc candidate)))))

(defn counts
  "Given a sorted sequence, returns a map which keys are sequence values
   and values are their counts in the sequence." 
  [sequence]
  (loop [result (hash-map)
	 to-be-processed sequence
	 last-seen nil
	 c 0]
    (cond
     (zero? (count to-be-processed)) (conj result (hash-map last-seen c))
     (= last-seen (first to-be-processed)) (recur result
						  (rest to-be-processed)
						  (first to-be-processed)
						  (+ 1 c))
     (nil? last-seen) (recur result
			     (rest to-be-processed)
			     (first to-be-processed)
			     1)
     :else (recur (conj result (hash-map last-seen c))
		  (rest to-be-processed)
		  (first to-be-processed)
		  1))))

(defn merge-max
  "Merges two maps, prefering greater value in case of conflicts."
  [map1 map2]
  (loop [result map1
	 to-be-processed (seq map2)]
    (cond
     (zero? (count to-be-processed)) result
     :else (let [[key value] (first to-be-processed)]
	     (if (or (nil? (get map1 key)) (< (get map1 key) value))
	       (recur (conj result (hash-map key value))
		      (rest to-be-processed))
	       (recur result
		      (rest to-be-processed)))))))

(defn power [x y]
  (. (. java.math.BigInteger (valueOf x)) (pow y)))

(println
 (let [all-factors (for [x (range 2 20)] (counts (factors x)))
       factor-counts (reduce merge-max all-factors)]
   (reduce * (map #(apply power %) (seq factor-counts)))))


;; A lot more elegant (and faster) solution uses least common denominator directly:
;; (defn gcd [a b] (if (zero? b) a (recur b (mod a b))))
;; (defn lcm [a b] (/ (* a b) (gcd a b)))
;; (reduce #(lcm %1 %2) (range 1 21))
