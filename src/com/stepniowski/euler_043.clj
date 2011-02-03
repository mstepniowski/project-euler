;; Solution to Project Euler problem 43
;; http://projecteuler.net/index.php?section=problems&id=43
;;
;; The number, 1406357289, is a 0 to 9 pandigital number because it is
;; made up of each of the digits 0 to 9 in some order, but it also has
;; a rather interesting sub-string divisibility property.
;;
;; Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
;;
;; d_2 d_3 d_4 = 406 is divisible by 2
;; d_3 d_4 d_5 = 063 is divisible by 3
;; d_4 d_5 d_6 = 635 is divisible by 5
;; d_5 d_6 d_7 = 357 is divisible by 7
;; d_6 d_7 d_8 = 572 is divisible by 11
;; d_7 d_8 d_9 = 728 is divisible by 13
;; d_8 d_9 d_10 = 289 is divisible by 17
;;
;; Find the sum of all 0 to 9 pandigital numbers with this property.
(ns com.stepniowski.euler-043)

(defn safe-parse-long [s]
  (Long/parseLong (apply str (drop-while #(= \0 %) s))))

(defn curious-pandigital [available r checks]
  "Given a set of available digits, the already built right part of the pandigital, and a list of checks,
   returns a list of curious pandigitals that can be created."
  (let [check (first checks)
	two-digits (+ (* 10 (first r)) (second r))
	combinations (map #(vector % (+ two-digits (* 100 %))) available)
	valid-combinations (filter #(zero? (rem (second %) check)) combinations)]
    (if (or (empty? (rest available)) (empty? (rest checks)))
      (map #(safe-parse-long (apply str (first %) r)) valid-combinations)
      (mapcat #(curious-pandigital (disj available (first %)) (conj r (first %)) (rest checks))
	      valid-combinations))))

(defn solution []
  (let [curious-pandigitals (mapcat #(curious-pandigital
				      (clojure.set/difference #{0 1 2 3 4 5 6 7 8 9} (set %)) % [17 13 11 7 5 3 2 1])
				    (for [x (range 10) y (range 10) :when (not= x y)] (list x y)))]
    (reduce + curious-pandigitals)))