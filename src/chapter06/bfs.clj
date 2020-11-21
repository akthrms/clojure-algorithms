(ns chapter06.bfs)

(def graph {:you    [:alice :bob :claire]
            :bob    [:anuj :peggy]
            :alice  [:peggy]
            :claire [:thom :jonny]
            :anuj   []
            :peggy  []
            :thom   []
            :jonny  []})

(defn seller? [person-name]
  (->> person-name
       (name)
       (seq)
       (last)
       (= \m)))

(defn search [person-name]
  (loop [[person & persons :as search-queue] (get graph person-name)
         searched []]
    (and (< 0 (count search-queue))
         (cond
           (.contains searched person) (recur persons searched)
           (seller? person) (do (println (name person) "is a mango seller!")
                                true)
           :else (recur (concat persons (get graph person))
                        (conj searched person))))))

(search :you)
; thom is a mango seller!
; => true
