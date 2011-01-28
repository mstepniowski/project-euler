;; Solution to Project Euler problem 97
;; http://projecteuler.net/index.php?section=problems&id=97
;;
;; The first known prime found to exceed one million digits was
;; discovered in 1999, and is a Mersenne prime of the form 2^6972593 - 1;
;; it contains exactly 2,098,960 digits. Subsequently other Mersenne
;; primes, of the form 2^p - 1, have been found which contain more digits.
;;
;; However, in 2004 there was found a massive non-Mersenne prime which
;; contains 2,357,207 digits: 28433 * 2^7830457 + 1.
;;
;; Find the last ten digits of this prime number.
(ns com.stepniowski.euler-097)

(defn expmod [a b n]
  "Returns (a^b mod n) using the Russian Peasant algorithm."
  (loop [a a b b rest 1]
    (if (= b 1)
      (mod (* rest a) n)
      (recur (mod (* a a) n)
	     (quot b 2)
	     (if (odd? b) (mod (* rest a) n) rest)))))

(defn solution []
  (let [modulus 10000000000]
    (mod (inc (* (expmod 2 7830457 modulus) 28433)) modulus)))