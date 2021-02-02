(ns ep2.production (:gen-class))
(require '[clojure.string :as str])

(defn fits?
  "checks if a substring fits inside a string"
  [substring string]
  (>= (count string) (count substring)))

(defn replace-first-or-nil
  "if str/replace-first is succesful, returns it's result; else, returns nil"
  [string fromA toB]
  (let
    [replacement (str/replace-first string fromA toB)]
    (if (= replacement string)
      nil
    ;else
      replacement)))

(defn produce
  "Returns a set of all possible applications of a rule, once, onto a string"
  ([string rule splitIndex accum]
    (let
      [ head  (subs string 0 splitIndex)
        tail  (subs string splitIndex)
        [fromA, toB] (first rule)]
      (if (not (fits? fromA tail))
        accum
      ;else
        (recur string rule (+ splitIndex 1) (into accum [(str head (replace-first-or-nil tail fromA toB))]))))
  )
  ([string rule]
  (if (not (fits? (first (first rule)) string))
    #{}
  ;else
    (produce string rule 0 #{}))))

(defn produce-all
  "Returns a set of all possible applications of every rule, once, onto a string"
  [string, ruleset]
  (reduce (fn [acc, rule] (into acc (produce string rule))) #{} ruleset))