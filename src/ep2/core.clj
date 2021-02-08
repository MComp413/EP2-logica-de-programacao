(ns ep2.core (:gen-class))
(require '[ep2.production :as prod])
(require '[ep2.input-parser :as ip])

(def filename "resources/input.json")

(defn is-word-accepted?
  "Verifies if a given word belongs to the language defined by the grammar"
  [word grammar]
  (let
    [ start (last grammar)
      ruleset (grammar 2)
      generated-sentences (prod/generate-sentences-limited [#{start}] ruleset (count word)) 
    ]
    (contains? (
      reduce #(into %1 %2) #{} generated-sentences
      ) word
    )
  )
)

(defn -main
  "Starting point. Given an input file path of a grammar in json format,
  returns boolean assert over the given W word in file to belong to the defined
  language."
  [& args]
  (let
    [ {grammar :grammar word :word} (ip/parse-input (slurp (first args))) ]
    (println grammar)
    (println (is-word-accepted? word grammar) )
  )  
)
