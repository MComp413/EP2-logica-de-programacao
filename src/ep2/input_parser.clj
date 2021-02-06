(ns ep2.input-parser (:gen-class))
(require '[clojure.data.json :as json])

(defn parse-input
  [raw-input]
  (let
    [ [N T P S W] (vals (json/read-str raw-input)) ]
    {:grammar [ (into #{} N)
                (into #{} T)
                (reduce (fn [acc rule] (conj acc {(first rule) (last rule)})) #{} P)
                S ]
     :word W}))

