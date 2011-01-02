;; Solution to Project Euler problem 1
;; http://projecteuler.net/index.php?section=problems&id=1
;;
;; If we list all the natural numbers below 10 that are multiples of 3 or 5
;; we get 3, 5, 6 and 9. The sum of these multiples is 23.
;;
;; Find the sum of all the multiples of 3 or 5 below 1000.
(ns com.stepniowski.euler-001)

(defn multiple? [number subject]
  (= (mod subject number) 0))

(defn solution []
 (reduce + (filter #(or (multiple? 3 %) (multiple? 5 %))
		   (range 1000))))
