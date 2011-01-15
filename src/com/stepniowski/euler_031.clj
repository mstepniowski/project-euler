;; Solution to Project Euler problem 31
;; http://projecteuler.net/index.php?section=problems&id=31
;;
;; In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
;;
;; 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
;; It is possible to make £2 in the following way:
;;
;; 1 * £1 + 1 * 50p + 2 * 20p + 1 * 5p + 1 * 2p + 3 * 1p
;;
;; How many different ways can £2 be made using any number of coins?
(ns com.stepniowski.euler-031)

(def coins [200 100 50 20 10 5 2 1])

(defn change [coins v]
  (let [c (first coins)]
    (if (= c 1) 1
	(reduce + (for [n (range 0 (inc (quot v c)))]
		    (change (rest coins) (- v (* n c))))))))

(defn solution []
  (change coins 200))
