(ns utils)

(defn drop-nth [n coll]
  (concat (take n coll)
          (drop (inc n) coll)))
