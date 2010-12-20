;; Solution to Project Euler problem 1
(defn multiple? [number subject]
  (= (mod subject number) 0))

(println
 (reduce + (filter #(or (multiple? 3 %) (multiple? 5 %))
		   (range 1000))))