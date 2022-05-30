(ns speedback.aux
  (:require
   [schema.core :as s]))

(s/defn calculates-number-of-rounds :- s/Int
  [number-of-members :- s/Int]
  (if (even? number-of-members)
    (dec number-of-members)
    number-of-members))
