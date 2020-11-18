(ns chapter01.binary-search)

(defn binary-search [list item]
  (loop [low 0
         high (dec (count list))]
    (let [mid (quot (+ low high) 2)
          guess (nth list mid)]
      (cond
        (> low high) nil
        (= guess item) mid
        (> guess item) (recur low (dec mid))
        :else (recur (inc mid) high)))))

(binary-search [1 3 5 7 9] 3)                               ; => 1
(binary-search [1 3 5 7 9] -1)                              ; => nil
