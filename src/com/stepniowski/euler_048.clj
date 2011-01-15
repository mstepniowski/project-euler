;; Solution to Project Euler problem 48
;; http://projecteuler.net/index.php?section=problems&id=48
;;
;; The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
;;
;; Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
(ns com.stepniowski.euler-048
  (:use clojure.contrib.math))

(defn solution []
  (apply str (take-last 10 (str (reduce + (map #(expt % %) (range 1 1001)))))))