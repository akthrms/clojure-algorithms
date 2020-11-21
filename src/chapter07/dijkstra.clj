(ns chapter07.dijkstra)

(def graph {:start  {:a 6
                     :b 2}
            :a      {:finish 1}
            :b      {:a      3
                     :finish 5}
            :finish {}})

(def costs (atom {:a      6
                  :b      2
                  :finish Integer/MAX_VALUE}))

(def routes (atom {:a      :start
                   :b      :start
                   :finish nil}))

(def processed (atom []))

(defn find-lowest-cost-node [costs]
  (let [lowest-cost (atom Integer/MAX_VALUE)
        lowest-cost-node (atom nil)]
    (doseq [[node cost] costs]
      (if (and (< cost @lowest-cost) (not-any? #(= % node) @processed))
        (do
          (reset! lowest-cost cost)
          (reset! lowest-cost-node node))))
    @lowest-cost-node))

(defn find-lowest-cost-route []
  (loop [from (get @routes :finish)
         result ["finish"]]
    (if (= from :start)
      (apply str (reverse (concat result [" -> " "start"])))
      (recur (get @routes from) (concat result [" -> " (name from)])))))

(defn dijkstra []
  (loop [node (find-lowest-cost-node @costs)]
    (if (nil? node)
      (find-lowest-cost-route)
      (do
        (doseq [[neighbor-node neighbor-cost] (get graph node)]
          (let [new-cost (+ (get @costs node) neighbor-cost)]
            (if (> (get @costs neighbor-node) new-cost)
              (do
                (swap! costs assoc neighbor-node new-cost)
                (swap! routes assoc neighbor-node node)))))
        (swap! processed conj node)
        (recur (find-lowest-cost-node @costs))))))

(dijkstra)                                                  ; => "start -> b -> a -> finish"
