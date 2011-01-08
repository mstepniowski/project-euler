;; Solution to Project Euler problem 22
;; http://projecteuler.net/index.php?section=problems&id=22
;;
;; Using names.txt (data/euler-22/names.txt), a 46K
;; text file containing over five-thousand first names, begin by
;; sorting it into alphabetical order. Then working out the
;; alphabetical value for each name, multiply this value by its
;; alphabetical position in the list to obtain a name score.
;;
;; For example, when the list is sorted into alphabetical order,
;; COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name
;; in the list. So, COLIN would obtain a score of 938 53 = 49714.
;;
;; What is the total of all the name scores in the file?
(ns com.stepniowski.euler-22
  (:require [clojure.contrib.duck-streams :as ds]
	    [clojure.contrib.string :as string]))

(defn char-score [char]
  (inc (- (Character/getNumericValue char) (Character/getNumericValue \A))))

(defn word-score [word number]
  (* number (reduce + (map char-score word))))

(defn solution []
  (let [data (ds/slurp* "data/euler-22/names.txt")
	names (map #(string/drop 1 (string/butlast 1 %)) (string/split #"," data))]
    (reduce + (map word-score (sort names) (iterate inc 1)))))
