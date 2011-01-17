;; Solution to Project Euler problem 42
;; http://projecteuler.net/index.php?section=problems&id=42
;;
;; The nth term of the sequence of triangle numbers is given by,
;; t_n = (1/2) * n * (n + 1); so the first ten triangle numbers are:
;;
;; 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
;;
;; By converting each letter in a word to a number corresponding to
;; its alphabetical position and adding these values we form a word
;; value. For example, the word value for SKY is 19 + 11 + 25 = 55 =
;; t10. If the word value is a triangle number then we shall call the
;; word a triangle word.
;;
;; Using words.txt (data/euler_042/words.txt), a 16K text file
;; containing nearly two-thousand common English words, how many are
;; triangle words?
(ns com.stepniowski.euler-043)

(defn triangle-numbers []
  "Returns a lazy sequence of all triangle numbers"
  (map #(* 1/2 % (inc %)) (iterate inc 1)))

(def triangle-numbers-coll (triangle-numbers))

(defn read-words [path]
  (let [data (slurp path)]
    (map second (re-seq #"\"([A-Z]+)\"" data))))

(defn word-value [word]
  "Returns the value of word, which is equal to the sum of
   alphabetical positions of its letters"
  (reduce + (map #(- (inc (Character/getNumericValue %)) (Character/getNumericValue \A))
		 word)))

(defn triangle-word? [word]
  (let [v (word-value word)]
    (some #(= v %) (take-while #(<= % v) triangle-numbers-coll))))

(defn solution []
  (count (filter triangle-word? (read-words "data/euler_042/words.txt"))))