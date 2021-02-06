(ns ep2.core (:gen-class))
(require '[ep2.production :as prod])
(require '[ep2.input-parser :as ip])

(def filename "resources/input.json")

(defn is-word-accepted?
  [word grammar]
  (let
    [ start (last grammar)
      ruleset (grammar 2)
      generated-sentences (prod/generate-sentences-limited [#{start}] ruleset (count word)) ]
    (contains? (reduce #(into %1 %2) #{} generated-sentences) word)))

(defn -main
  "The test"
  [& args]
  (let
    [ {grammar :grammar word :word} (ip/parse-input (slurp (first args))) ]
    (println grammar)
    (println (is-word-accepted? word grammar))))
