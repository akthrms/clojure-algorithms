(ns chapter02.selection-sort
  (:require [utils :refer [drop-nth]]))

(defn find-smallest [arr]
  (loop [smallest (first arr)
         smallest-index 0
         i 1]
    (cond
      (= i (count arr)) smallest-index
      (< (nth arr i) smallest) (recur (nth arr i) i (inc i))
      :else (recur smallest smallest-index (inc i)))))

(defn selection-sort [arr]
  (loop [arr arr
         new-arr []
         i 0]
    (if (< i (count arr))
      (let [smallest (find-smallest arr)]
        (recur (drop-nth smallest arr)
               (conj new-arr (nth arr smallest))
               (inc i)))
      new-arr)))

(selection-sort [5 3 6 2 10])                               ; => [2 3 5 6 10]
