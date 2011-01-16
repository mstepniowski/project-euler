;; Solution to Project Euler problem 96
;; http://projecteuler.net/index.php?section=problems&id=96
;;
;; Su Doku (Japanese meaning number place) is the name given to a
;; popular puzzle concept. Its origin is unclear, but credit must be
;; attributed to Leonhard Euler who invented a similar, and much more
;; difficult, puzzle idea called Latin Squares. The objective of Su
;; Doku puzzles, however, is to replace the blanks (or zeros) in a 9
;; by 9 grid in such that each row, column, and 3 by 3 box contains
;; each of the digits 1 to 9. Below is an example of a typical
;; starting puzzle grid and its solution grid.
;;
;; 0 0 3   0 2 0  6 0 0
;; 9 0 0   3 0 5  0 0 1
;; 0 0 1   8 0 6  4 0 0
;;
;; 0 0 8   1 0 2  9 0 0
;; 7 0 0   0 0 0  0 0 8
;; 0 0 6   7 0 8  2 0 0
;;
;; 0 0 2   6 0 9  5 0 0
;; 8 0 0   2 0 3  0 0 9
;; 0 0 5   0 1 0  3 0 0
;;
;; A well constructed Su Doku puzzle has a unique solution and can be
;; solved by logic, although it may be necessary to employ "guess and
;; test" methods in order to eliminate options (there is much
;; contested opinion over this). The complexity of the search
;; determines the difficulty of the puzzle; the example above is
;; considered easy because it can be solved by straight forward direct
;; deduction.
;;
;; The 6K text file, sudoku.txt (right click and 'Save Link/Target
;; As...'), contains fifty different Su Doku puzzles ranging in
;; difficulty, but all with unique solutions (the first puzzle in the
;; file is the example above).
;;
;; By solving all fifty puzzles find the sum of the 3-digit numbers
;; found in the top left corner of each solution grid; for example,
;; 483 is the 3-digit number found in the top left corner of the
;; solution grid above.
(ns com.stepniowski.euler-096
  (:import [java.io BufferedReader FileReader]))

(def sudoku-board [0 0 3   0 2 0  6 0 0
		   9 0 0   3 0 5  0 0 1
		   0 0 1   8 0 6  4 0 0
		   
		   0 0 8   1 0 2  9 0 0
		   7 0 0   0 0 0  0 0 8
		   0 0 6   7 0 8  2 0 0
		   
		   0 0 2   6 0 9  5 0 0
		   8 0 0   2 0 3  0 0 9
		   0 0 5   0 1 0  3 0 0])

(def domain #{1 2 3 4 5 6 7 8 9})

(defn convert-board [board]
  (map #(if (= % 0) domain #{%}) board))

(defn apply-constraint [board indices constraint]
  "Returns the board with indicated elements changed according to constraint"
  (let [indices-s (set indices)]
    (map-indexed #(if (indices-s %1) (clojure.set/intersection %2 (constraint %1)) %2) board)))

(defn replace-idx [smap coll]
  "Given a mapping of indices to replacements, returns a sequence with elements from smap replaced"
  (map-indexed #(let [replacement (smap %1)] (if replacement replacement %2)) coll))

(defn all-different [f board]
  "Ensures that all the elements pointed by f are different"
  (let [indexed (map-indexed vector board)
	constrained (filter f indexed)
	set-values (filter #(= (count (second %)) 1) constrained)
	constraint (fn [idx]
		     (let [constraints (map second (filter #(not= (first %) idx) set-values))]
		       (clojure.set/difference domain (apply clojure.set/union constraints))))]
    (apply-constraint board (map first constrained) constraint)))

(defn all-different-indices [board indices]
  (let [indices-s (set indices)]
    (all-different #(contains? indices-s (first %)) board)))

(defn at-least-one [f board]
  "Ensures that at least one element has a value from domain"
  (let [indexed (map-indexed vector board)
	constrained (filter f indexed)
	constraints (filter identity (for [x domain] (if (= (count (filter #(= x (second %)) constrained)) 1)
						       (some #(if (= x (second %)) [(first %) #{x}]) constrained))))]
    (replace-idx (apply hash-map (concat constraints)) board)))
  
(defn at-least-one-indices [board indices]
  (let [indices-s (set indices)]
    (at-least-one #(contains? indices-s (first %)) board)))

(defn sudoku-row [n]
  "Return the indices of nth board row"
  (range (* n 9) (* (inc n) 9)))

(defn sudoku-column [n]
  "Returns the indices of nth board column"
  (range n 81 9))

(defn sudoku-square [n]
  "Returns the indices of nth board square"
  (let [top-left-corner (+ (* 27 (quot n 3)) (* 3 (rem n 3)))]
    (concat (range top-left-corner (+ 3 top-left-corner))
	    (range (+ 9 top-left-corner) (+ 12 top-left-corner))
	    (range (+ 18 top-left-corner) (+ 21 top-left-corner)))))

(defn apply-constraints [board]
  (let [areas (concat (map sudoku-row (range 9))
		      (map sudoku-column (range 9))
		      (map sudoku-square (range 9)))]
    (loop [areas areas board board]
      (cond
       (empty? areas) board
       true (recur (rest areas) (at-least-one-indices (all-different-indices board (first areas)) (first areas)))))))

(defn apply-constraints-until-fixed [board]
  (loop [previous-board board board (apply-constraints board)]
    (if (= previous-board board)
      board
      (recur board (apply-constraints board)))))

(defn solved? [board]
  (empty? (filter #(> (count %) 1) board)))

(defn most-constrained [board]
  (let [unknowns (filter #(> (second %) 1) (map-indexed #(vector %1 (count %2)) board))]
    (if (empty? unknowns)
      nil
      (first (reduce #(if (< 1 (second %2) (second %1)) %2 %1) unknowns)))))

(defn correct? [board]
  (if (> (count (filter #(zero? (count %)) board)) 0)
    false
    (let [areas (concat (map sudoku-row (range 9))
			(map sudoku-column (range 9))
			(map sudoku-square (range 9)))]
      (loop [areas areas]
	(cond
	 (empty? areas) true
	 (let [values (filter #(= (count %) 1) (map #(nth board %) (first areas)))]
	   (not= (count (set values)) (count values))) false
	   true (recur (rest areas)))))))
  
(defn solve [board]
  (let [board (apply-constraints-until-fixed board)]
    (if (not (correct? board))
      nil
      (if (solved? board)
	board
	(let [next (most-constrained board)]
	  (if next
	    (some identity (for [x (nth board next)] (solve (replace-idx (hash-map next #{x}) board))))
	    nil))))))

(defn print-board [board]
  (doseq [x (partition 9 board)]
    (println (apply str (interpose \space (for [i x] (cond
						      (= (count i) 1) (first i)
						      (= (count i) 0) \X
						      true \0)))))))

(defn read-board [s]
  (apply vector (map #(Integer/parseInt (str %)) s)))

(defn read-boards [path]
  (with-open [rdr (BufferedReader. (FileReader. path))]
    (doall (map #(read-board (apply str %)) (partition 9 (filter #(re-matches #"\d+" %) (line-seq rdr)))))))

(defn solved-sudoku-number [board]
  "Returns the number created from the first 3 digits in Sudoku board"
  (let [digits (map first (take 3 board))]
    (+ (* 100 (first digits)) (* 10 (second digits)) (nth digits 2))))

(defn solution []
  (let [solved-sudoku-boards (map #(solve (convert-board %)) (read-boards "data/euler_096/sudoku.txt"))
	numbers (map solved-sudoku-number solved-sudoku-boards)]
    (reduce + numbers)))
