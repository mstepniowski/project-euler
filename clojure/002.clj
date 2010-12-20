;; Solution to Project Euler problem 2
(defn fibonacci-numbers
  ([]
     (concat [1 2] (fibonacci-numbers 1 2)))
  ([a b]
     (let [n (+ a b)]
       (lazy-seq
	(cons n (fibonacci-numbers b n))))))

(let [fibs (take-while #(< % 4000000)
		       (fibonacci-numbers))]
  (println (reduce + (filter even? fibs))))
