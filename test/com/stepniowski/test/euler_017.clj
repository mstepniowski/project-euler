;; Tests for solution to Project Euler problem 17
(ns com.stepniowski.test.euler-017
  (:use com.stepniowski.euler-017 clojure.test))

(deftest test-verbatim
  (is (= "one" (verbatim 1)))
  (is (= "two" (verbatim 2)))
  (is (= "fifteen" (verbatim 15)))
  (is (= "twenty-one" (verbatim 21)))
  (is (= "three hundred and forty-two" (verbatim 342)))
  (is (= "one hundred and fifteen" (verbatim 115)))
  (is (= "nine hundred and ninety-nine" (verbatim 999)))
  (is (= "one thousand" (verbatim 1000))))

(deftest test-count-alpha
  (is (= 3 (count-alpha "one")))
  (is (= 3 (count-alpha "two")))
  (is (= 7 (count-alpha "fifteen")))
  (is (= 9 (count-alpha "twenty-one")))
  (is (= 23 (count-alpha "three hundred and forty-two")))
  (is (= 20 (count-alpha "one hundred and fifteen")))
  (is (= 24 (count-alpha "nine hundred and ninety-nine"))))