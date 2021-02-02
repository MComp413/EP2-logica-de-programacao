(ns ep2.production (:gen-class))
(require '[clojure.string :as str])

(defn fits?
  "checks if a substring fits inside a string"
  [substring string]
  (>= (count string) (count substring)))

(defn production-accepted?
  [produced-string original-string limit]
  (and (not= original-string produced-string) (<= (count produced-string) limit)))

(defn produce-limited
  "Returns a set of all possible applications of a rule, once, onto a string, and only the produced strings that are not greater than limit"
  ([string rule limit splitIndex accum]
    (let
      [ head  (subs string 0 splitIndex)
        tail  (subs string splitIndex)
        [fromA, toB] (first rule)]
      (if (not (fits? fromA tail))
        accum
      ;else
        (let
          [ new-string  (str head (str/replace-first tail fromA toB))
            new-accum   (if (production-accepted? new-string string limit)
                          (into accum [new-string])
                        ;else
                          accum)]
          (recur string rule limit (+ splitIndex 1) new-accum)))))
  ([string rule limit]
  (if (not (fits? (first (first rule)) string))
    #{}
  ;else
    (produce-limited string rule limit 0 #{}))))

(defn produce-all-limited
  "Returns a set of all possible applications of every rule, once, onto a string"
  [string ruleset limit]
  (reduce (fn [acc rule] (into acc (produce-limited string rule limit))) #{} ruleset))

(defn apply-production-step
  "Applies every production law to every string once and returns the resulting set of strings, excluding those greater than limit"
  [stringset ruleset limit]
  (reduce (fn [acc string] (into acc (produce-all-limited string ruleset limit))) #{} stringset))

(defn produce-language-limited
  ""
  [stringset ruleset limit]
  (let
    [ new-stringset (into stringset (apply-production-step stringset ruleset limit)) ]
    (if (= (count stringset) (count new-stringset))
      new-stringset
    ;else 
      (recur new-stringset ruleset limit))))