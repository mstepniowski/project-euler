;; Solution to Project Euler problem 18
;; http://projecteuler.net/index.php?section=problems&id=18
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
;; Find the maximum total from top to bottom of the triangle below:
(ns com.stepniowski.euler-018)

(def triangle- [[75]
		[95 64]
		[17 47 82]
		[18 35 87 10]
		[20  4 82 47 65]
		[19  1 23 75  3 34]
		[88  2 77 73  7 63 67]
		[99 65  4 28  6 16 70 92]
		[41 41 26 56 83 40 80 70 33]
		[41 48 72 33 47 32 37 16 94 29]
		[53 71 44 65 25 43 91 52 97 51 14]
		[70 11 33 28 77 73 17 78 39 68 17 57]
		[91 71 52 38 17 14 91 43 58 50 27 29 48]
		[63 66  4 68 89 53 67 30 73 16 69 87 40 31]
		[ 4 62 98 27 23  9 70 98 73 93 38 53 60  4 23]])


(defn pairwise-max [coll]
  "Returns the collection of maximums of two successive elements in coll"
  (map #(apply max %) (partition 2 1 coll)))

(defn sum-coll [coll1 coll2]
  "Given the coll (a1 .. an), (b1 .. bn) returns ((+ a1 b1) .. (+ an bn))"
  (map + coll1 coll2))

(defn path-sum [triangle]
  "Returns the maximum total of path elements walking the triangle from top to bottom"
  (first (reduce #(sum-coll (pairwise-max %1) %2) (reverse triangle))))

(defn solution []
  (path-sum triangle-))