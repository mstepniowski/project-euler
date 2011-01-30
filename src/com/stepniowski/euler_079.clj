;; Solution to Project Euler problem 79
;; http://projecteuler.net/index.php?section=problems&id=79
;;
;; A common security method used for online banking is to ask the user
;; for three random characters from a passcode. For example, if the
;; passcode was 531278, they may ask for the 2nd, 3rd, and 5th
;; characters; the expected reply would be: 317.
;;
;; The text file, keylog.txt, contains fifty successful login attempts.
;;
;; Given that the three characters are always asked for in order,
;; analyse the file so as to determine the shortest possible secret
;; passcode of unknown length.
(ns com.stepniowski.euler-079)

(def *login-attempts* ["319" "680" "180" "690" "129" "620" "762" "689" "762"
		       "318" "368" "710" "720" "710" "629" "168" "160" "689"
		       "716" "731" "736" "729" "316" "729" "729" "710" "769"
		       "290" "719" "680" "318" "389" "162" "289" "162" "718"
		       "729" "319" "790" "680" "890" "362" "319" "760" "316"
		       "729" "380" "319" "728" "716"])

(defn trinity-dependencies [[a b c]]
  "Returns a hash of dependencies, ie. the numbers that must come
   before the other numbers, based on the passed trinity."
  (hash-map a #{} b #{a} c #{a b}))

(defn remove-vertice [graph v]
  "Returns a graph with vertice v and all edges leading to it removed."
  (dissoc (apply hash-map (mapcat (fn [[w edges]] [w (disj edges v)]) graph))
	  v))

(defn topological-sort [graph]
  "Returns a topological sort of vertices in dependency DAG graph."
  (loop [result []
	 graph graph
	 s (map first (filter #(zero? (count (second %1))) graph))]
    (if (empty? s)
      result
      (let [v (first s)
	    graph (remove-vertice graph v)]
	(recur (conj result v)
	       graph
	       (map first (filter #(zero? (count (second %1))) graph)))))))
		      
(defn solution []
  (let [dependency-graph (apply merge-with clojure.set/union
				  (map trinity-dependencies *login-attempts*))]
    (apply str (topological-sort dependency-graph))))
