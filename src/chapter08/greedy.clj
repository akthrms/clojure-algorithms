(ns chapter08.greedy
  (:require [clojure.set :refer [difference intersection]]))

(def states-needed (atom #{:mt :wa :or :id :nv :ut :ca :az}))

(def stations {:kone   #{:id :nv :ut}
               :ktwo   #{:wa :id :mt}
               :kthree #{:or :nv :ca}
               :kfour  #{:nv :ut}
               :kfive  #{:ca :az}})

(def final-stations (atom #{}))

(defn greedy []
  (loop []
    (if (empty? @states-needed)
      @final-stations
      (do
        (let [best-station (atom nil)
              states-covered (atom #{})]
          (doseq [[station states-for-station] stations]
            (let [covered (intersection @states-needed states-for-station)]
              (if (> (count covered) (count @states-covered))
                (do
                  (reset! best-station station)
                  (reset! states-covered covered)))))
          (swap! states-needed difference @states-covered)
          (swap! final-stations conj @best-station))
        (recur)))))

(greedy)                                                    ; => #{:kone :kthree :ktwo :kfive}
