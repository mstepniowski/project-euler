;; Tests for solution to Project Euler problem 18
(ns com.stepniowski.test.euler-018
  (:use com.stepniowski.euler-018 clojure.test))

(deftest test-path-sum
  (is (= 1 (path-sum [[1]])))
  (is (= 23 (path-sum [[3]
		       [7 4]
		       [2 4 6]
		       [8 5 9 3]]))))

(deftest test-pairwise-max
  (is (= [2 3 4 5 6] (pairwise-max [1 2 3 4 5 6]))))