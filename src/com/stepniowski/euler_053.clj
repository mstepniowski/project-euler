;; Solution to Project Euler problem 53
;; http://projecteuler.net/index.php?section=problems&id=53
;;
;; There are exactly ten ways of selecting three from five, 12345:
;;
;; 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
;;
;; In combinatorics, we use the notation, C_3^5 = 10.
;;
;; In general, C_n^r = n! / (r! * (n - r)!), where r <= n
;;
;; It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
;;
;; How many, not necessarily distinct, values of C_n^r, for 1 <= n <= 100, are
;; greater than one-million?
;;
;; NOTES:
;; The numbers in [Pascal's triangle](http://en.wikipedia.org/wiki/Pascal's_triangle)
;; are the consecutive binomial coefficients C_n^r, where n - row index,
;; r - column index. The algorithm to construct the Pascal's triangle in Clojure
;; is easy and fast.
(ns com.stepniowski.euler-053)

(defn pascal-triangle
  ([] (pascal-triangle [1]))
  ([row] (let [next-row (concat [1] (map #(apply + %) (partition 2 1 row)) [1])]
	   (lazy-seq (cons row (pascal-triangle next-row))))))

(defn solution []
  (count (filter #(> % 1000000) (flatten (take 101 (pascal-triangle))))))
