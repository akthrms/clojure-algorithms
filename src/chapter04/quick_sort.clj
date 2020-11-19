(ns chapter04.quick-sort)

(defn quick-sort [arr]
  (if (< (count arr) 2)
    arr
    (let [pivot (first arr)
          less (for [i (rest arr) :when (<= i pivot)] i)
          greater (for [i (rest arr) :when (> i pivot)] i)]
      (concat (quick-sort less) (list pivot) (quick-sort greater)))))

(quick-sort [10 5 2 3])                                     ; => (2 3 5 10)
