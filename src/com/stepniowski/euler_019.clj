;; Solution to Project Euler problem 19
;; http://projecteuler.net/index.php?section=problems&id=19
;;
;; You are given the following information, but you may prefer to do
;; some research for yourself.
;;
;; * 1 Jan 1900 was a Monday.
;; * Thirty days has September,
;;   April, June and November.
;;   All the rest have thirty-one,
;;   Saving February alone,
;;   Which has twenty-eight, rain or shine.
;;   And on leap years, twenty-nine.
;;
;; * A leap year occurs on any year evenly divisible by 4, but not on
;;   a century unless it is divisible by 400.
;;
;; How many Sundays fell on the first of the month during the
;; twentieth century (1 Jan 1901 to 31 Dec 2000)?
(ns com.stepniowski.euler-019)

(def months '(:january :february :march :april :may :june :july :august :september :october :november :december))
(def next-month (apply hash-map (apply concat (partition 2 1 (concat months '(:january))))))

(defn sunday? [n]
  "Returns true if the n-th day counting from the start of 1900 is a
  Sunday, false otherwise."
  (zero? (rem n 7)))

(defn leap-year? [year]
  "Returns true if a year is a leap year, false otherwise."
  (and (zero? (rem year 4))
       (or (not (zero? (rem year 100))) (zero? (rem year 400)))))

(defn number-of-days [month year]
  "Returns the number of days of the specific month in the year (used for calculating leap years)."
  (cond
   (#{:september :april :june :november} month) 30
   (#{:january :march :may :july :august :october :december} month) 31
   (leap-year? year) 29
   true 28))

(defn start-of-next-month [[n month year]]
  "Given a triple representing the start of some month:
  n - days since the start of Jan 1900,
  month - name of the month,
  year - year number,
  returns a triple representing the start of next month."
  [(+ n (number-of-days month year))
   (next-month month)
   (if (= month :december)
     (inc year)
     year)]
)

(defn year [[n month year]]
  year)

(defn day [[n month year]]
  n)

(defn solution []
  (let [month-starts (->> (iterate start-of-next-month [1 :january 1900])
			 (drop-while #(< (year %) 1901))
			 (take-while #(<= (year %) 2000)))]
    (count (filter #(sunday? (day %)) month-starts))))
