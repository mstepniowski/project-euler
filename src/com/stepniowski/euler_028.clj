;; Solution to Project Euler problem 28
;; http://projecteuler.net/index.php?section=problems&id=28
;;
;; Starting with the number 1 and moving to the right in a clockwise
;; direction a 5 by 5 spiral is formed as follows:
;;
;; 21 22 23 24 25
;; 20  7  8  9 10
;; 19  6  1  2 11
;; 18  5  4  3 12
;; 17 16 15 14 13
;;
;; It can be verified that the sum of the numbers on the diagonals is 101.
;;
;; What is the sum of the numbers on the diagonals in a 1001 by 1001
;; spiral formed in the same way?
(ns com.stepniowski.euler-028)

(defn number-sum [n]
  "Returns the sum of sequence 1 + 2 + 3 + ... + n"
  (* 1/2 (inc n) n))

(defn square-sum [n]
  "Returns the sum of sequence 1 + 4 + 9 + ... + n^2"
  (* 1/6 n (inc n) (inc (* 2 n))))

(defn sqr [n]
  (* n n))

(defn solution []
  ;; Magical math formula calculated by me on paper
  ;;
  ;; Basic idea:
  ;; Each circle of the spiral (started every 4 numbers)
  ;; adds another sequence in form 1 + 3 + 5 + ... + n + n = n^2 + n
  ;; to the first sequence 1 + 3 + ... + (2 * n - 1) = (2 * n - 1)^2
  ;; We already know how to calculate 1 + 4 + 9 + ... + n^2
  ;; and 1 + 2 + 3 + ... + n, so the whole equation is easy to solve.
  (let [n 1001
	k (/ (- n 3) 2)]
    (+ (sqr (dec (* 2 n)))
       (* 16 (square-sum k))
       (* 4 (number-sum k)))))