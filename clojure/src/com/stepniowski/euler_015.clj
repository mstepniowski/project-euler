;; Solution to Project Euler problem 15
;; http://projecteuler.net/index.php?section=problems&id=15
;;
;; Starting in the top left corner of a 22 grid, there are 6 routes
;; (without backtracking) to the bottom right corner.
;;
;; How many routes are there through a 20x20 grid?
(ns com.stepniowski.euler-015)

(defn factorial [n]
  (loop [n n acc 1]
    (cond
     (<= n 1) acc
     true (recur (dec n) (* acc n)))))

(defn combination-with-repetition [n k]
  "Returns the number of ways to choose k elements from set of size n, with repetitions.
  Order does not matter"
  (/ (factorial (+ (dec n) k))
     (* (factorial k) (factorial (dec n)))))

(defn solution []
  (combination-with-repetition (inc 20) 20))
