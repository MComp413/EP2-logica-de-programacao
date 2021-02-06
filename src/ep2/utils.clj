(ns ep2.utils (:gen-class))
(require '[clojure.set :as set])

(defn fits?
    "checks if a substring fits inside a string"
    [substring string]
    (>= (count string) (count substring)))

