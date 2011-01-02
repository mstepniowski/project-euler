;; Solution to Project Euler problem 16
;; http://projecteuler.net/index.php?section=problems&id=16
;;
;; 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
;;
;; What is the sum of the digits of the number 2^1000?
(ns com.stepniowski.euler-016)

(defn power [x y]
  (. (. java.math.BigInteger (valueOf x)) (pow y)))

(defn solution []
  (let [digits (map #(Integer/parseInt (str %)) (str (power 2 1000)))]
    (reduce + digits)))