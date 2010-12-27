;; Solution to Project Euler problem 9
;; http://projecteuler.net/index.php?section=problems&id=9
;;
;; A Pythagorean triplet is a set of three natural numbers, a  b  c, for which
;; a^2 + b^2 = c^2
;; For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
;;
;; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
;; Find the product abc.
(ns com.stepniowski.project-euler)

(defn triplets
  "Returns triplets [a b c], where a, b and c are integers and a + b + c = sum."
  [sum]
  (for [a (range (+ 1 sum)) b (range (- (+ 1 sum) a))]
    [a b (- sum a b)]))

(defn pythagorean-triplet?
  "Returns a triplet if a < b < c and a^2 + b^2 = c^2, false otherwise."
  [[a b c :as triplet]]
  (if (and (< a b c)
	   (= (+ (* a a) (* b b)) (* c c)))
    triplet))

(defn euler-9 []
  (let [found-triplet (some pythagorean-triplet? (triplets 1000))]
    (apply * found-triplet)))
