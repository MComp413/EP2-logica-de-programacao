(ns ep2.core (:gen-class))

(def P #{{"S" "ab"} {"S" "aSb"}}) ; Production rules
(def G [#{"e" "d" "c"} #{"b" "a"} P "S"]) ; Grammar

(def produceStep
  "Receives a set of strings, produces all possibilities of applying each production rule to each string"
  [stringSet productionRules]
  (map)
  )

(def produce
  "Returns all strings possibly produced from a given string and production rule applied a single time"
  [string rule]
  ()
  )



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
