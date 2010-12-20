;; Solution to Project Euler problem 3
(use 'clojure.contrib.lazy-seqs)

(defn prime-factors-of [n]
  (filter #(zero? (rem n %)) (take-while #(> n (* % %)) primes)))

(println (reduce max (prime-factors-of 600851475143)))