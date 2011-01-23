;; Solution to Project Euler problem 39
;; http://projecteuler.net/index.php?section=problems&id=39
;;
;; If p is the perimeter of a right angle triangle with integral
;; length sides, {a,b,c}, there are exactly three solutions for p = 120.
;;
;; {20,48,52}, {24,45,51}, {30,40,50}
;;
;; For which value of p <= 1000, is the number of solutions maximised?
(ns com.stepniowski.euler-039
  (:use clojure.contrib.greatest-least))

(defn sqr [n] (* n n))

(defn right-angle-triangles [perimeter]
  "Returns a sequence of right angle triangles [a b c] with integral length sides,
   where a, b, c are triangle sides and a <= b < c"
  ;; The formula for b is a solution of equations:
  ;; a + b + c = perimeter
  ;; a^2 + b^2 = c^2
  (for [a (range 2 (/ perimeter 3))
	:let [b (/ (- (sqr perimeter) (* 2 a perimeter))
		   (* 2 (- perimeter a)))]]
    [a b (- perimeter a b)]))

(defn solution []
  (apply greatest-by #(count (right-angle-triangles %)) (range 1 1001)))