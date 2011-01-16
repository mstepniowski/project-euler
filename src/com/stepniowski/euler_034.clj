;; Solution to Project Euler problem 34
;; http://projecteuler.net/index.php?section=problems&id=34
;;
;; 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
;;
;;; Find the sum of all numbers which are equal to the sum of the factorial of their digits.
;;
;; Note: as 1! = 1 and 2! = 2 are not sums they are not included.
(ns com.stepniowski.euler-034)

(defn factorial [n]
  (reduce * (range 1 (inc n))))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)))

(defn foo-number? [n]
  "Returns true if n is the sum of the factorial of its digits, false
  otherwise"
  (and (> n 2) ;; 1! = 1 and 2! = 2 are not sums
       (= n (reduce + (map factorial (digits n))))))

(defn solution []
  (let [limit (* 7 (factorial 9))]
    (reduce + (filter foo-number? (range 1 (inc limit))))))