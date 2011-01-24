;; Solution to Project Euler problem 33
;; http://projecteuler.net/index.php?section=problems&id=33
;;
;; The fraction 49/98 is a curious fraction, as an inexperienced
;; mathematician in attempting to simplify it may incorrectly believe
;; that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
;;
;; We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
;;
;; There are exactly four non-trivial examples of this type of
;; fraction, less than one in value, and containing two digits in the
;; numerator and denominator.
;;
;; If the product of these four fractions is given in its lowest
;; common terms, find the value of the denominator.
(ns com.stepniowski.euler-033)

(defn gcd [a b]
  (cond
   (zero? b) a
   (> b a) (recur b a)
   true (recur b (- a (* b (quot a b))))))
      
(defn simplify [[a b]]
  (let [g (gcd a b)] [(/ a g) (/ b g)]))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)))

(defn first-default [coll default]
  (if (empty? coll) default (first coll)))

(defn curious-fraction? [[a b]]
  (let [da (into #{} (digits a))
	db (into #{} (digits b))
	common-digits (clojure.set/intersection da db)]
    (if (not= (count common-digits) 1)
      false
      (let [sa (first-default (clojure.set/difference da common-digits) (first common-digits))
	    sb (first-default (clojure.set/difference db common-digits) (first common-digits))]
	(and (= (count common-digits) 1)
	     (= (simplify [sa sb]) (simplify [a b])))))))

(defn solution []
  (let [curious-fractions (filter curious-fraction? (for [b (range 11 100) a (range 11 b)] [a b]))
	nontrivial-curious-fractions (filter #(> (rem (first %) 10) 0) curious-fractions)]
    (println (map #(simplify %) nontrivial-curious-fractions))
    (reduce * (map #(second (simplify %)) nontrivial-curious-fractions))))