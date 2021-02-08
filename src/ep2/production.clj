(ns ep2.production (:gen-class))
(require '[clojure.string :as str])
(require '[ep2.utils :as utils])

(defn production-accepted?
  "Verifies if a produced string is smaller or equal than the limit of the given word
  and if the produced string is not equal to the original string from it derived."
  [produced-string original-string limit]
  ( 
    and (not= original-string produced-string) (<= (count produced-string) limit)
  )
)

(defn derive-string-with-rule
  "Returns a set of all possible applications of a rule, once, onto a string, 
  and only the produced strings that are not greater than limit"
  ([string rule limit splitIndex accum]
    (let
      [ head  (subs string 0 splitIndex)
        tail  (subs string splitIndex)
        [fromA, toB] (first rule)]
      (if (not (utils/fits? fromA tail))
        accum
      ;else
        (let
          [ new-string  (str head (str/replace-first tail fromA toB))
            new-accum   (if (production-accepted? new-string string limit)
                          (conj accum new-string)
                        ;else
                          accum)
          ]
          (recur string rule limit (+ splitIndex 1) new-accum)
        )
      )
    )
  )
  ([string rule limit]
  (if (not (utils/fits? (first (keys rule)) string))
    #{}
  ;else
    (derive-string-with-rule string rule limit 0 #{}))))

(defn derive-string
  "Returns a set of all possible applications of every rule, once, onto a string"
  [string ruleset limit]
  (reduce (fn [acc rule] (into acc (derive-string-with-rule string rule limit))) #{} ruleset))

(defn derive-step
  "Applies every production rule to every string once and returns the resulting set of strings, excluding those greater than limit"
  [stringset ruleset limit]
  (reduce (fn [acc string] (into acc (derive-string string ruleset limit))) #{} stringset))

(defn generate-sentences-limited
  "Generates all sentences with length not greater than limit"
  [stringset-list ruleset limit]
  (let
    [ new-stringset   (derive-step (last stringset-list) ruleset limit) ]
    (if (= 0 (count new-stringset))
      stringset-list
    ;else 
      (recur (conj stringset-list new-stringset) ruleset limit)
    )
  )
)
