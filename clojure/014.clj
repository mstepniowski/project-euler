;; Solution to Project Euler problem 14
;; http://projecteuler.net/index.php?section=problems&id=14
;;
;; The following iterative sequence is defined for the set of positive integers:
;;
;; n = n/2 (n is even)
;; n = 3n + 1 (n is odd)
;;
;; Using the rule above and starting with 13, we generate the following sequence:
;;
;; 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
;;
;; It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
;; Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
;;
;; Which starting number, under one million, produces the longest chain?
;;
;; NOTE: Once the chain starts the terms are allowed to go above one million.
(ns com.stepniowski.project-euler)

(defn max-at [s]
  "Returns [xi x], where xi is the index number of the max element
  (indexed from 1) and x is the max of s."
  (letfn [(find-max [s n x xi]
             (let [f (first s)
                   r (rest s)]
               (if (nil? f)
                 [xi x]
                 (if (> f x)
                   (recur r (inc n) f n)
                   (recur r (inc n) x xi)))))]
    (find-max s 1 0 0)))

(defn chain-length [n]
  "Returns the length of the Collatz sequence starting from n."
  (loop [n n acc 1]
    (cond
     (= n 1) acc
     (even? n) (recur (/ n 2) (inc acc))
     true (recur (inc (* 3 n)) (inc acc)))))

;; Sadly this function requires about 512MB of space and runs slower than the above
(def memoized-chain-length
     (memoize
      (fn [n]
	(cond
	 (= n 1) 1
	 (even? n) (inc (memoized-chain-length (/ n 2)))
	 true (inc (memoized-chain-length (inc (* 3 n))))))))

(defn euler-14 []
  (first (max-at (map chain-length (range 1 1000000)))))
