;; Solution to Project Euler problem 21
;; http://projecteuler.net/index.php?section=problems&id=21
;;
;; Let d(n) be defined as the sum of proper divisors of n (numbers
;; less than n which divide evenly into n).  If d(a) = b and d(b) = a,
;; where a b, then a and b are an amicable pair and each of a and b
;; are called amicable numbers.
;;
;; For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20,
;; 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of
;; 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
;;
;; Evaluate the sum of all the amicable numbers under 10000.
(ns com.stepniowski.euler-021)

(defn divisible-by? [n candidate]
  (zero? (rem n candidate)))

(defn proper-divisors [n]
  "Return the proper divisors of number n."
  (loop [result [] candidate 1]
    (cond
     (>= candidate n) result
     (divisible-by? n candidate) (recur (conj result candidate)
					(inc candidate))
     :else (recur result (inc candidate)))))

(defn divisor-sums [n]
  "Returns a map of divisor sum to numbers where numbers are in range (1..n)."
  (let [starting-divisor-sums (group-by #(reduce + (proper-divisors %))
					 (range 1 n))
	other-divisors (filter #(>= % n) (keys starting-divisor-sums))
	other-divisor-sums (group-by #(reduce + (proper-divisors %))
				     other-divisors)]
    (merge-with concat starting-divisor-sums other-divisor-sums)))

(defn amicable-siblings? [x y divisor-map]
  "Returns true if x and y are amicable-siblings of each other, false otherwise."
  (let [possible-siblings-of-x (get divisor-map x)
	possible-siblings-of-y (get divisor-map y)]
    (and (not= x y) ; x and y must be different numbers
	 (some #(= y %) possible-siblings-of-x)
	 (some #(= x %) possible-siblings-of-y))))

(defn amicable-siblings [n divisor-map]
  "Returns the amicable siblings of n"
  (let [possible-siblings (get divisor-map n)]
    (filter #(amicable-siblings? n % divisor-map) possible-siblings)))

(defn solution []
  (let [divisor-map (divisor-sums 10001)]
    (reduce + (filter #(seq (amicable-siblings % divisor-map)) (range 1 10001)))))