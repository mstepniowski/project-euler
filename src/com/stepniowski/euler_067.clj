;; Solution to Project Euler problem 67
;; http://projecteuler.net/index.php?section=problems&id=67
;;
;; By starting at the top of the triangle below and moving to adjacent
;; numbers on the row below, the maximum total from top to bottom is 23.
;;
;; 3
;; 7 4
;; 2 4 6
;; 8 5 9 3
;;
;; That is, 3 + 7 + 4 + 9 = 23.
;;
;; Find the maximum total from top to bottom in triangle.txt
;; (data/euler_067/triangle.txt), a 15K text file containing a
;; triangle with one-hundred rows.
;;
;; NOTE:
;; The solution is the same as in problem euler-018.
(ns com.stepniowski.euler-067
  (:import [java.io BufferedReader FileReader]))

(defn pairwise-max [coll]
  "Returns the collection of maximums of two successive elements in coll"
  (map #(apply max %) (partition 2 1 coll)))

(defn sum-coll [coll1 coll2]
  "Given the coll (a1 .. an), (b1 .. bn) returns ((+ a1 b1) .. (+ an bn))"
  (map + coll1 coll2))

(defn path-sum [triangle]
  "Returns the maximum total of path elements walking the triangle from top to bottom"
  (first (reduce #(sum-coll (pairwise-max %1) %2) (reverse triangle))))

(defn read-triangle-line [line]
  (let [tokens (map #(apply str %) (partition-by #{\space} line))
	numbers (map #(Integer/parseInt %) (filter #(not= " " %) tokens))]
    numbers))
	
(defn read-triangle [path]
  (with-open [rdr (BufferedReader. (FileReader. path))]
    (doall (map read-triangle-line (line-seq rdr)))))

(defn solution []
  (path-sum (read-triangle "data/euler_067/triangle.txt")))